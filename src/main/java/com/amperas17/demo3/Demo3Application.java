package com.amperas17.demo3;

import com.amperas17.demo3.users.data.UserEntity;
import com.amperas17.demo3.users.data.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Demo3Application implements CommandLineRunner {

	@Autowired
	UserRepository userRepository;

	public static void main(String[] args) {
		SpringApplication.run(Demo3Application.class, args);
	}


	@Override
	public void run(String... args) throws Exception {
		saveUsers();
	}

	private void saveUsers() {
		if (userRepository.count() <= 0) {
			userRepository.save(new UserEntity("User1", "email1@mail.ru", "+7(916)111-22-33"));
			userRepository.save(new UserEntity("User2", "email2@mail.ru", "+7(916)112-22-33"));
			userRepository.save(new UserEntity("User3", "email3@mail.ru", "+7(916)113-22-33"));
		}
	}
}
