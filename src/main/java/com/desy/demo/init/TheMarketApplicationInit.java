package com.desy.demo.init;

import com.desy.demo.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class TheMarketApplicationInit implements CommandLineRunner {
    private  final UserService userService;

    public TheMarketApplicationInit(UserService userService) {
        this.userService = userService;
    }

    @Override
    public void run(String... args) throws Exception {
        userService.seedUsers();

    }
}
