package webdev.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.*;

import webdev.models.User;
import webdev.repositories.UserRepository;

@RestController
public class UserService {
	@Autowired
	UserRepository userRepository;
	
	@PostMapping("/api/user")
	public User createUser(@RequestBody User user) {
		return userRepository.save(user);
	}
	
	@GetMapping("/api/user")
	public List<User> findAllUsers() {
		return (List<User>) userRepository.findAll();
	}
	
	@GetMapping("/api/user/{userId}")
	public User findUserById(@PathVariable("userId") int id) {
		Optional<User> data = userRepository.findById(id);
		if(data.isPresent()) {
			return data.get();
		}
		return null;
	}
	
	@PutMapping("/api/user/{userId}")
	public User updateUser(@RequestBody User newUser, @PathVariable("userId") int id) {
		Optional<User> data = userRepository.findById(id);
		if(data.isPresent()) {
			newUser.setId(id);
			userRepository.save(newUser);
		}
		return null;
	}
	
	@DeleteMapping("/api/user/{userId}")
	public void deleteUser(@PathVariable("userId") int id) {
		userRepository.deleteById(id);
	}
	
	@DeleteMapping("/api/allusers")
	public void deleteAllUsers() {
		userRepository.deleteAll();
	}
	
	@PostMapping("/api/register")
	public User register(@RequestBody User user) {
		List<User> listOfRegistrations = (List<User>) userRepository.findUserByUserName(user.getUsername());
		System.out.println(user.getFirstName() + "ssssssssssssssss");
		if (listOfRegistrations.isEmpty())
			return userRepository.save(user);
		else
			return null;
	}
	
	@PostMapping("/api/login")
	public List<User> login(@RequestBody User user) {
		return (List<User>) userRepository.findUserByUsernameAndPassword(user.getUsername(), user.getPassword());
	}
}