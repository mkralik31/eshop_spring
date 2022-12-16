package lamas.brights.eshop.cart;

import lamas.brights.eshop.dto.AddToCartDto;
import lamas.brights.eshop.dto.CartDto;
import lamas.brights.eshop.product.Product;
import lamas.brights.eshop.user.User;

public interface CartService {

    void addToCart(AddToCartDto addToCartDto, User user, Product product);
    CartDto listCartItems(User user);

}
