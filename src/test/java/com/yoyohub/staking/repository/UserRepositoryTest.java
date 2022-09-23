package com.yoyohub.staking.repository;

import com.yoyohub.staking.entity.User;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@SpringBootTest("spring.profiles.active:dev")
public class UserRepositoryTest {

    @Mock
    private UserRepository userRepository;

    private static String id;
    private static  User user;

    @BeforeAll
    static void init() {
        id = "test_user";
        user = User.builder()
                .id(id)
                .password("abc")
                .password2("def")
                .name("홍길동")
                .phone("1234")
                .email("kkk@kjlkj")
                .ci("ci")
                .di("di")
                .registeredDate(LocalDateTime.now())
                .build();
    }

    @Test
    void save() {
        userRepository.save(user);

        when(userRepository.findById(id)).thenReturn(Optional.of(user));
        User check = userRepository.findById(id).get();

        assertEquals(user.getId(), check.getId());
    }
}
