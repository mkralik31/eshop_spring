package lamas.brights.eshop.cart;

import lamas.brights.eshop.authorization.AuthenticationService;
import lamas.brights.eshop.product.Product;
import lamas.brights.eshop.product.ProductService;
import lamas.brights.eshop.dto.AddToCartDto;
import lamas.brights.eshop.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/cart")
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

    @PostMapping("/add")
    public ResponseEntity<Cart> addToCart(@RequestParam("token") String token,
                                          @RequestBody AddToCartDto addToCartDto) throws Exception {
        // validate token (if TOKEN and USER is present)
        if (token != null) {
            authenticationService.authenticate(token);

            // retrieve user after validation
            User user = authenticationService.getUser(token);
            //retrieve product with product_id
            System.err.println("\n" + "productId " + addToCartDto.toString() + "\n");
            Product product = productService.getProductById(addToCartDto.getProductId());
            // console log to check the product
            System.err.println(
                    "\n" + "Adding to CART" + "\n" +
                            product.getName() + "\n" );
            cartService.addToCart(addToCartDto, user, product);


            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    }

}
