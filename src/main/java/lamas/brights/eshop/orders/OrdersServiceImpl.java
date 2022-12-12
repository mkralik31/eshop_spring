package lamas.brights.eshop.orders;


import lamas.brights.eshop.product.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrdersServiceImpl  implements OrdersService {

    @Autowired
    private OrdersRepository ordersRepository;

    @Override
    public List<Orders> getAllOrders() {
        return ordersRepository.findAll();;
    }

    @Override
    public Orders getOrdersById(long id) {
        Optional<Orders> optional = ordersRepository.findById(id);
        Orders orders = null;

        if(optional.isPresent()){
            orders = optional.get();
        }else{
            throw new RuntimeException("Orders not found for id: " + id);
        }

        return orders;
    }
}
