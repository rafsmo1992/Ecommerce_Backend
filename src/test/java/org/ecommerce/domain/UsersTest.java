package org.ecommerce.domain;

import org.ecommerce.repository.CartRepository;
import org.ecommerce.repository.UserRepository;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;

@SpringBootTest
public class UsersTest {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CartRepository cartRepository;

    @Test
    public void testUsersRepositoryPost() {
        //Given
        User user1 = new User("Tomasz", "Tomasz@gmail.com", "test");
        User user2 = new User("Romek", "Romek@gmail.com", "test");
        User user3 = new User("Tomasz", "Tomasz@gmail.com", "test");
        User user4 = new User("Romek", "Romek@gmail.com", "test");
        User user5 = new User("Romek", "Romek@gmail.com", "test");
        userRepository.save(user1);
        userRepository.save(user2);
        userRepository.save(user3);
        userRepository.save(user4);
        userRepository.save(user5);

        //When
        long usersRepositorySize = userRepository.count();

        //  Then
        assertEquals(5, usersRepositorySize);

        //CleanUp
        userRepository.deleteAll();
    }

    @Test
    public void testUsersRepositoryGet() {
        //Given
        User user1 = new User("Tomasz", "Tomasz@gmail.com", "test");
        User user2 = new User("Romek", "Romek@gmail.com", "test");
        User user3 = new User("Atomek", "Atomek@gmail.com", "test");
        User user4 = new User("Atomek", "Atomek@gmail.com", "test");
        userRepository.save(user1);
        userRepository.save(user2);
        userRepository.save(user3);
        userRepository.save(user4);

        //When
        List<User> testUser = userRepository.findAll();

        //Then
        assertEquals(4, testUser.size());

        //CleanUp
        userRepository.deleteAll();
    }

    @Test
    public void testUsersRepositoryGetById() {
        //Given
        User user1 = new User("Tomasz", "Tomasz@gmail.com", "test");
        User user2 = new User("Romek", "Romek@gmail.com", "test");
        User user3 = new User("Atomek", "Atomek@gmail.com", "test");
        User user4 = new User("Atomek", "Atomek@gmail.com", "test");
        userRepository.save(user1);
        userRepository.save(user2);
        userRepository.save(user3);

        //When
        Long userId = user1.getId();

        //Then
        assertEquals(user1.getId(), userId);

        //CleanUp
        userRepository.deleteAll();
    }

    @Test
    public void testUsersRepositoryPut() {
        //Given
        User user1 = new User("Tomasz", "Tomasz@gmail.com", "test");
        User user2 = new User("Romek", "Romek@gmail.com", "test");
        userRepository.save(user1);
        userRepository.save(user2);

        //When
        User editedUser = userRepository.findById(user2.getId()).get();
        editedUser.setUserLogin("Matolek");
        userRepository.save(editedUser);
        String userNAmeAfterEdit = userRepository.findById(user2.getId()).get().getUserLogin();

        //Then
        assertEquals("Matolek", userNAmeAfterEdit);
        assertEquals(2, userRepository.count());

        //CleanUp
        userRepository.deleteAll();
    }

    @Test
    public void testUsersRepositoryDeleteById() {
        //Given
        User user1 = new User("Tomasz", "Tomasz@gmail.com", "test");
        User user2 = new User("Romek", "Romek@gmail.com", "test");
        userRepository.save(user1);
        userRepository.save(user2);

        //When
        userRepository.deleteById(user1.getId());
        long userRepositorySize = userRepository.count();

        //Then
        assertEquals(1, userRepositorySize);
        assertEquals(Optional.empty(), userRepository.findById(user1.getId()));

        //CleanUp
        userRepository.deleteAll();
    }

    @Test
    public void testUserCascadeWhenRemoveCart() {
        //Given
        User user1 = new User("Tomasz", "Tomasz@gmail.com", "test");
        Cart cart1 = new Cart(user1);
        Cart cart2 = new Cart(user1);
        user1.getCarts().add(cart1);
        user1.getCarts().add(cart2);

        userRepository.save(user1);
        cartRepository.save(cart1);
        cartRepository.save(cart2);

        //When
        long userSizeBeforeDelete = userRepository.count();
        cartRepository.deleteAll();
        long usersSizeAfterDelete = userRepository.count();

        // Then
        Assert.assertEquals(1, usersSizeAfterDelete);
        Assert.assertEquals(1, userSizeBeforeDelete);

        //CleanUp
        userRepository.deleteAll();
    }
}
