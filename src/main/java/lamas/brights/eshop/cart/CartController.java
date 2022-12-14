package lamas.brights.eshop.cart;

import lamas.brights.eshop.authorization.AuthenticationService;
import lamas.brights.eshop.dto.CartDto;
import lamas.brights.eshop.product.Product;
import lamas.brights.eshop.product.ProductService;
import lamas.brights.eshop.dto.AddToCartDto;
import lamas.brights.eshop.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@Transactional
@RestController
@RequestMapping("/api")
public class CartController {

    private final CartService cartService;
    private final ProductService productService;
    private final AuthenticationService authenticationService;

    @Autowired
    public CartController(CartService cartService,
                          ProductService productService,
                          AuthenticationService authenticationService) {
        this.cartService = cartService;
        this.productService = productService;
        this.authenticationService = authenticationService;
    }

    @PutMapping("/cart/update/{cartItemId}")
    public ResponseEntity<String> updateItemCount(@PathVariable Long cartItemId,
                                                  @RequestParam("token") String token,
                                                  @RequestBody Cart cart) throws Exception {
        Integer cartItemCount = cart.getQuantity();
        System.err.println(cartItemCount);
        if (token != null) {
            authenticationService.authenticate(token);
//            User user = authenticationService.getUser(token);
            Integer currentCount = cartService.getCurrentCount(cartItemId);
            //if current count is higher than new count user is decreasing item count
            if (currentCount < cartItemCount) {
                if (cartService.isAvailable(cartItemId)) {
                    //first take count from product than add count to cartItem
                    cartService.takeProduct(cartItemId);
                    cartService.updateCartItem(cartItemId, cartItemCount);
                    return new ResponseEntity<>("Increment", HttpStatus.ACCEPTED);
                }
            }
            if (currentCount > cartItemCount) {
                //first add count to product than remove count from cartItem
                cartService.returnProduct(cartItemId);
                cartService.updateCartItem(cartItemId, cartItemCount);
                return new ResponseEntity<>("Decrement", HttpStatus.ACCEPTED);
            }
        }

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PostMapping("/cart/add")
    public ResponseEntity<Cart> addToCart(@RequestParam("token") String token,
                                          @RequestBody AddToCartDto addToCartDto) throws Exception {
        // validate token (if TOKEN and USER is present)
        if (token != null) {
            authenticationService.authenticate(token);

            // retrieve user after validation
            User user = authenticationService.getUser(token);
            //retrieve product with product_id
            System.err.println("\n" + "productId " + addToCartDto.toString() + "\n");
            if (cartService.isAvailableProduct(addToCartDto.getProductId())) {
                Product product = productService.getProductById(addToCartDto.getProductId());
                // console log to check the product
                System.err.println(
                        "\n" + "Adding to CART" + "\n" +
                                product.getName() + "\n");
                cartService.addToCart(addToCartDto, user, product);

                return new ResponseEntity<>(HttpStatus.OK);
            }
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    }

    @GetMapping("/cart")
    public ResponseEntity<CartDto> listCartItems(@RequestParam("token") String token) throws Exception {
        // validate token (if TOKEN and USER is present)
        if (token != null) {
            authenticationService.authenticate(token);
            // retrieve user after validation
            User user = authenticationService.getUser(token);
            //retrieve products for user
            CartDto cartDto = cartService.listCartItems(user);

            return new ResponseEntity<>(cartDto, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/cart/delete/{cartItemId}")
    public ResponseEntity<String> deleteItemFromCart(@PathVariable("cartItemId") Long cartItemId,
                                                     @RequestParam("token") String token) throws Exception {
        // validate token (if TOKEN and USER is present)
        if (token != null) {
            authenticationService.authenticate(token);
            //delete item from user's cart by id
            cartService.deleteCartItem(cartItemId);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

}
