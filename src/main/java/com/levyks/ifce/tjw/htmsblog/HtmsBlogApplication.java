package com.levyks.ifce.tjw.htmsblog;

import com.levyks.ifce.tjw.htmsblog.users.entities.UserEntity;
import com.levyks.ifce.tjw.htmsblog.users.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
@RequiredArgsConstructor
public class HtmsBlogApplication implements ApplicationRunner {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public static void main(String[] args) {
        SpringApplication.run(HtmsBlogApplication.class, args);
    }


    @Bean
    public boolean isDebug(Environment environment) {
        String debug = environment.getProperty("debug");
        return null != debug && !debug.equals("false");
    }

    @Override
    public void run(ApplicationArguments args) {
        if (userRepository.count() > 0) return;

        var user = new UserEntity();
        user.setEmail("admin@localhost");
        user.setName("Admin");
        user.setPassword(passwordEncoder.encode("12345678"));
        user.setAdmin(true);
        user.setEnabled(true);

        userRepository.save(user);
    }
}
