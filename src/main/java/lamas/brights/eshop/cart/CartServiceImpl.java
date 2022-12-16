package lamas.brights.eshop.cart;

import lamas.brights.eshop.dto.AddToCartDto;
import lamas.brights.eshop.product.Product;
import lamas.brights.eshop.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Date;

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
}
