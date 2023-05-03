package org.ecommerce.domain;

import org.ecommerce.repository.CartRepository;
import org.ecommerce.repository.OrderRepository;
import org.ecommerce.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@SpringBootTest
public class OrderTest {
    @Autowired
    OrderRepository orderRepository;
    @Autowired
    CartRepository cartRepository;
    @Autowired
    UserRepository userRepository;


    @Test
    public void testAddOrder() {

        //Given
        User user1 = new User("Tomasz","Tomasz@gmail.com","test");
        Cart cart1 = new Cart(user1);
        Order order1 = new Order( cart1);
        Order order2 = new Order( cart1);

        user1.getCarts().add(cart1);

        userRepository.save(user1);
        cartRepository.save(cart1);
        orderRepository.save(order1);
        orderRepository.save(order2);

        //When
        long orderRepositorySize = orderRepository.count();

        //Then
        assertEquals(2,orderRepositorySize);

        //ClenUp
        orderRepository.deleteAll();
        cartRepository.deleteAll();
        userRepository.deleteAll();
    }
    @Test
    public void testGetOrders(){

        //Given
        User user1 = new User("Tomasz","Tomasz@gmail.com","test");
        Cart cart1 = new Cart(user1);
        Order order1 = new Order( cart1);
        Order order2 = new Order( cart1);
        Order order3 = new Order( cart1);

        user1.getCarts().add(cart1);

        userRepository.save(user1);
        cartRepository.save(cart1);
        orderRepository.save(order1);
        orderRepository.save(order2);
        orderRepository.save(order3);

        //When
        List<Order> testOrders = orderRepository.findAll();

        //Then
        assertEquals(3,testOrders.size());

        //CleanUp
        orderRepository.deleteAll();
        cartRepository.deleteAll();

    }
    @Test
    public void testOrderGetById(){
        //Given
        User user1 = new User("Tomasz","Tomasz@gmail.com","test");
        Cart cart1 = new Cart(user1);
        Order order1= new Order(cart1);

        user1.getCarts().add(cart1);

        userRepository.save(user1);
        cartRepository.save(cart1);
        orderRepository.save(order1);

        //When
        Order getOrder = orderRepository.findById(order1.getId()).get();

        //Then
        assertNotNull(getOrder.getId());

        //CleanUp
        orderRepository.deleteAll();
        cartRepository.deleteAll();
        userRepository.deleteAll();
    }
    @Test
    public void testEditOrder(){
        //Given
        User user1 = new User("Tomasz","Tomasz@gmail.com","test");
        Cart cart1 = new Cart(user1);
        Order order1 = new Order(cart1);
        Order order2 = new Order(cart1);

        user1.getCarts().add(cart1);

        userRepository.save(user1);
        cartRepository.save(cart1);
        orderRepository.save(order1);
        orderRepository.save(order2);

        //When
        Order editOrder = orderRepository.findById(order1.getId()).get();

        //CleanUp
        orderRepository.deleteAll();
        cartRepository.deleteAll();
        userRepository.deleteAll();
    }
    @Test
    public void testOrderDeleteById(){
        //Given
        User user1 = new User("Tomasz","Tomasz@gmail.com","test");
        Cart cart1 = new Cart(user1);
        final Order order1 = new Order(cart1);
        final Order order2 = new Order(cart1);
        final Order order3 = new Order(cart1);

        user1.getCarts().add(cart1);

        userRepository.save(user1);
        cartRepository.save(cart1);
        orderRepository.save(order1);
        orderRepository.save(order2);
        orderRepository.save(order3);

        //When
        long orderSizeBeforeDelete = orderRepository.count();
        orderRepository.deleteById(order1.getId());
        long orderSizeAfterDelete = orderRepository.count();

        //Then
        assertEquals(Optional.empty(),orderRepository.findById(order1.getId()));
        assertEquals(3,orderSizeBeforeDelete);
        assertEquals(2,orderSizeAfterDelete);

        //CleanUp
        orderRepository.deleteAll();
        cartRepository.deleteAll();
        userRepository.deleteAll();
    }
}
