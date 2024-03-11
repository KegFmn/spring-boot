package com.likc.templateboot;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootTest
public class PassWordTest {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Test
    public void password() {
        String password = passwordEncoder.encode("lkc8658759@!");
        System.out.println(password);
    }
}
