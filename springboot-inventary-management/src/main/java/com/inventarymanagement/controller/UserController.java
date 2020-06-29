package com.inventarymanagement.controller;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.inventarymanagement.entity.User;
import com.inventarymanagement.exception.ResourecNotFoundException;
import com.inventarymanagement.repositary.UserRepository;




@RestController
@RequestMapping("/api")
public class UserController {
	
	private static final Logger log = LoggerFactory.getLogger(UserController.class);


	@Autowired
	private UserRepository userRepository;

	// get all users
	@GetMapping("/users")
	public List<User> getAllUsers() {
		log.trace("getall users metod accessed");
		log.info("getall users method");
		return this.userRepository.findAll();
	}

	// get user by id
	@GetMapping("/users/{id}")
	public User getUserById(@PathVariable (value = "id") Integer userId) {
		return this.userRepository.findById(userId)
				.orElseThrow(() -> new ResourecNotFoundException("User not found with id :" + userId));
	}

	// create user
	@PostMapping("/users")
	public User createUser(@RequestBody User user) {
		
		return this.userRepository.save(user);
	}
	
	// update user
	@PutMapping("/users/{id}")
	public User updateUser(@RequestBody User user, @PathVariable ("id") Integer userId) {
		 User existingUser = this.userRepository.findById(userId)
			.orElseThrow(()-> new ResourecNotFoundException("User not found with id :" + userId));
		  existingUser.setFirst_name(user.getFirst_name());
		 existingUser.setLast_name(user.getLast_name());
		 existingUser.setEmail(user.getEmail());
		 return this.userRepository.save(existingUser);
	}
	
	// delete user by id
	@DeleteMapping("/users/{id}")
	public ResponseEntity<User> deleteUser(@PathVariable ("id") Integer userId){
		 User existingUser = this.userRepository.findById(userId)
					.orElseThrow(()-> new ResourecNotFoundException("User not found with id :" + userId));
		 this.userRepository.delete(existingUser);
		 return ResponseEntity.ok().build();
	}
}

 
