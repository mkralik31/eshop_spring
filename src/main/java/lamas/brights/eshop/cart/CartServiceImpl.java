package lamas.brights.eshop.cart;

import lamas.brights.eshop.dto.AddToCartDto;
import lamas.brights.eshop.dto.CartDto;
import lamas.brights.eshop.dto.CartItemDto;
import lamas.brights.eshop.product.Product;
import lamas.brights.eshop.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class CartServiceImpl implements CartService{

    private final CartRepository cartRepository;

    @Autowired
    public CartServiceImpl(CartRepository cartRepository) {
        this.cartRepository = cartRepository;
    }

    @Override
    public void addToCart(AddToCartDto addToCartDto, User user, Product product) {
        Cart cart = new Cart(product, addToCartDto.getQuantity(), user);
        cart.setCreateDate(new Date());
        cartRepository.save(cart);
    }

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
        double totalPrice = 0;
        // for every item from carts retrieve price and calculate value according to quantity
        for(CartItemDto cartItemDto : cartItemsList) {
            totalPrice += (cartItemDto.getProduct().getPrice() * cartItemDto.getQuantity());
        }
        //return list of items and total price
        return new CartDto(cartItemsList, totalPrice);
    }

    //helper method for retrieving items from carts
    public CartItemDto getDtoFromCart(Cart cart) {
        return new CartItemDto(cart);
    }
}
