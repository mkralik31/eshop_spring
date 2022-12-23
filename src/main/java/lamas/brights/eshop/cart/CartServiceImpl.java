package lamas.brights.eshop.cart;

import lamas.brights.eshop.dto.AddToCartDto;
import lamas.brights.eshop.dto.CartDto;
import lamas.brights.eshop.dto.CartItemDto;
import lamas.brights.eshop.product.Product;
import lamas.brights.eshop.product.ProductService;
import lamas.brights.eshop.user.User;
import org.apache.commons.math3.util.Precision;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class CartServiceImpl implements CartService {

    private final CartRepository cartRepository;
    private final ProductService productService;

    @Autowired
    public CartServiceImpl(CartRepository cartRepository,
                           ProductService productService) {
        this.cartRepository = cartRepository;
        this.productService = productService;
    }

    @Override
    public void addToCart(AddToCartDto addToCartDto, User user, Product product) {
        Cart cart = new Cart(product, addToCartDto.getQuantity(), user);
        cart.setCreateDate(new Date());
        cartRepository.save(cart);
    }

    @Transactional
    @Override
    public CartDto listCartItems(User user) {
        //find all carts for user
        List<Cart> cartList = cartRepository.findAllByUserOrderByCreateDateDesc(user);
        //create new arraylist for cartItems
        List<CartItemDto> cartItemsList = new ArrayList<>();

        // retrieve items(products) from list of carts
        for (Cart cart : cartList) {
            CartItemDto cartItemDto = getDtoFromCart(cart);
            cartItemsList.add(cartItemDto);
        }

        //set initial price in cart to 0
        double _totalPrice = 0;
        // for every item from carts retrieve price and calculate value according to quantity
        for (CartItemDto cartItemDto : cartItemsList) {
            _totalPrice += (cartItemDto.getProduct().getPrice() * cartItemDto.getQuantity());
        }
        double totalPrice = Precision.round(_totalPrice, 2);
        //return list of items and total price
        return new CartDto(cartItemsList, totalPrice);
    }

    @Override
    public void updateCartItem(Long cartItemId, Integer cartItemCount) {
        Cart cart = getCart(cartItemId);
        double productPrice = cart.getProduct().getPrice();
        cart.setQuantity(cartItemCount);
        cart.setPrice(productPrice * cartItemCount);
    }

    @Override
    public void takeProduct(Long cartItemId) {
        Cart cart = getCart(cartItemId);
        Product product = cart.getProduct();
        product.setCount(product.getCount() - 1 );
    }

    @Override
    public void returnProduct(Long cartItemId) {
        Cart cart = getCart(cartItemId);
        Product product = cart.getProduct();
        product.setCount(product.getCount() + 1 );
    }


    @Override
    public void deleteCartItem(Long cartItemId) {
        cartRepository.deleteById(cartItemId);
    }

    @Override
    public void deleteUserCartItems(User user) {
        cartRepository.deleteAllByUser(user);
    }

    @Override
    public boolean isAvailable(Long cartItemId) {
        Cart cart = getCart(cartItemId);
        Product product = cart.getProduct();
        return product.getCount() != 0;
    }

    @Override
    public boolean isAvailableProduct(Long productId) {
        Product product = productService.getProductById(productId);
        return product.getCount() != 0;
    }

    public Cart getCart(Long cartItemId) {
        Optional<Cart> cart = cartRepository.findById(cartItemId);
        if (cart.isPresent()) {
            return cart.get();
        }
        return null;
    }

    @Override
    public Integer getCurrentCount(Long cartItemId) {
        Cart cart = getCart(cartItemId);
        return cart.getQuantity();
    }

    //helper method for retrieving items from carts
    public CartItemDto getDtoFromCart(Cart cart) {
        return new CartItemDto(cart);
    }
}
