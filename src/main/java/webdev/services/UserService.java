package webdev.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
	
	@PutMapping("/api/user")
	public User findUserById(@RequestBody User user) {
		System.out.println("sssssssssss");
		List<User> listOfRegistrations = (List<User>) userRepository.findUserByUserName(user.getUsername());

		if (!listOfRegistrations.isEmpty()) {
			User oldUser = listOfRegistrations.get(0);
			user.setId(oldUser.getId());
			return userRepository.save(user);
		}	
		else
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
	public ResponseEntity register(@RequestBody User user) {
		List<User> listOfRegistrations = (List<User>) userRepository.findUserByUserName(user.getUsername());
		try {
			if(listOfRegistrations.isEmpty()) {
				userRepository.save(user);
				return new ResponseEntity(HttpStatus.OK);
			}
			else
				return new ResponseEntity(HttpStatus.CONFLICT);
		}
		catch(Exception e) {
			return new ResponseEntity(HttpStatus.NOT_FOUND);
		}
	}

	@PostMapping("/api/login")
	public ResponseEntity login(@RequestBody User user) {
		List<User> listOfRegistrations = (List<User>) userRepository.findUserByUsernameAndPassword(user.getUsername(), user.getPassword());
		try {
			User oldUser = listOfRegistrations.get(0);
			user.setId(oldUser.getId());
			user.setPassword(oldUser.getPassword());
			user.setFirstName(oldUser.getFirstName());
			user.setLastName(oldUser.getLastName());
			userRepository.save(user);
			return new ResponseEntity(HttpStatus.OK);
		}
		catch(Exception e) {
			return new ResponseEntity(HttpStatus.NOT_FOUND);
		}
	}

	@PutMapping("/api/profile")
	public ResponseEntity profile(@RequestBody User user) {
		List<User> listOfRegistrations = (List<User>) userRepository.findUserByUserName(user.getUsername());
		if (!listOfRegistrations.isEmpty()) {
			User oldUser = listOfRegistrations.get(0);
			user.setId(oldUser.getId());
			user.setPassword(oldUser.getPassword());
			user.setFirstName(oldUser.getFirstName());
			user.setLastName(oldUser.getLastName());
			userRepository.save(user);
			return new ResponseEntity(HttpStatus.OK);
		}
		else {
			return new ResponseEntity(HttpStatus.NOT_FOUND);
		}
	}
}