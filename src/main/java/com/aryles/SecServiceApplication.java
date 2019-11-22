package com.aryles;

import com.aryles.entities.AppRole;
import com.aryles.service.AccountService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.stream.Stream;

@SpringBootApplication
public class SecServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(SecServiceApplication.class, args);
	}
@Bean
	CommandLineRunner start(AccountService accountService){
	return args -> {
		accountService.save(new AppRole(null,"USER"));
		accountService.save(new AppRole(null,"ADMIN"));
		Stream.of("User 1", "User2", "User3","admin").forEach(un ->{
			accountService.saveUser(un,"1234","1234");
		});
		accountService.addRoleToUser("admin","ADMIN");
	}	;
	}
	@Bean
	BCryptPasswordEncoder getBCEP(){
		return new BCryptPasswordEncoder();
	}
}
