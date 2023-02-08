//package com.niit.kanbanboardapp.repository;
//
//import com.niit.kanbanboardapp.domain.User;
//import com.niit.kanbanboardapp.exception.DocumentNotFoundException;
//import org.junit.jupiter.api.AfterEach;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
//import org.springframework.dao.DuplicateKeyException;
//import org.springframework.test.context.junit.jupiter.SpringExtension;
//
//import java.util.List;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//@DataMongoTest
//@ExtendWith(SpringExtension.class)
//public class UserRepositoryLayerTest {
//
//    @Autowired
//    private UserRepository userRepository;
//
//    User user;
//
//    @BeforeEach
//    public void setUp()
//    {
//        user=new User("jh1vb3h1j2v3","jeet","7061349526","jeetkrejha@gmail.com","USER_ROLE");
//    }
//    @AfterEach
//    public void tearDown()
//    {
//        user=null;
//        userRepository.deleteAll();
//    }
//
//    @Test
//    public void addUserTest()  //success Case
//    {
//        User user1=userRepository.insert(user);
//        assertEquals(user,user1);
//    }
//    @Test
//    public void addTrackTestFail()
//    {
//        User user1=userRepository.insert(user);
//        assertThrows(DuplicateKeyException.class,()->userRepository.insert(user));
//    }
//    @Test
//    public void getAllUser()
//    {
//        userRepository.insert(user);
//        user.setUserId("23123131");
//        userRepository.insert(user);
//       List<User> userList= userRepository.findAll();
//        assertEquals(2,userList.size());
//    }
//
//    @Test
//    public void deleteTest()    //delete user success test
//     {
//        userRepository.insert(user);
//        userRepository.deleteById(user.getUserId());
//        assertEquals(0,userRepository.findAll().size());
//    }
//
//    @Test
//    public void updateUserTest()
//    {
//        User user1=userRepository.insert(user);
//        user1.setEmail("ram@gmail.com");
//        user1.setUserName("ram");
//        user1.setRole("ROLE_USER");
//        user1.setPhoneNumber("88");
//        userRepository.save(user1);
//        assertEquals(user.getEmail(),user1.getEmail());
//    }
//
//
//
//
//
//
//}
