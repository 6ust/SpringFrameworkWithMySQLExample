package gust.io.projeto1.config;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import gust.io.projeto1.entity.User;
import gust.io.projeto1.repository.UserRepository;

@Component
public class DataInitializer implements ApplicationListener<ContextRefreshedEvent> {
	
	@Autowired
	UserRepository userRepository;
	
	@Override
	public void onApplicationEvent(ContextRefreshedEvent arg0) {
	
		List<User> users = userRepository.findAll();
		
		if(users.isEmpty()) {			
			User user = new User();
			
			//Teste Criação de Usuario
			createUser("Gus F", "mrgus@gmail.com");
			createUser("Walter W", "waltw@gmail.com");
			createUser("Rebecca T", "rect@gmail.com");
		}
		
		createUser("James P", "jamp@gmail.com");
		User user = userRepository.getOne(2L);
		
		user.setName("Heisenberg");
		userRepository.save(user);
		
		System.out.println(userRepository.findByName("Gus F").getEmail());
		System.out.println(userRepository.findByNameQualquerCoisa("Hei").getName());
		
		System.out.println(userRepository.findByEmail("rect@gmail.com").getName());
		
	}
	
	//Construtor de Criação de usuario 
	public void createUser(String name, String email) {
		User user = new User(name, email);
		
		userRepository.save(user);
		
	}
	
	
}
