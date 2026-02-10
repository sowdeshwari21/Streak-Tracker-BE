package intern.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import intern.example.demo.Entity.userdetails;
import intern.example.demo.Service.UserService;
import intern.example.demo.dto.ResponseStructure;

@RestController
@RequestMapping("/api/users")
public class usercontroller {
	@Autowired
	private UserService userservice;
	
	
	@PostMapping("/reg")
	public ResponseEntity<ResponseStructure<userdetails>>saveUser(@RequestBody userdetails userdetail){
		return userservice.saveUser(userdetail);
	}
	
	@PostMapping("/login")
	public ResponseEntity<ResponseStructure<userdetails>>loginUser(@RequestParam String email,@RequestParam String password){
		return userservice.loginUser(email, password);
	}
	
	
	@GetMapping("/{id}")
	public ResponseEntity<ResponseStructure<userdetails>>getUserById(@PathVariable Integer id){
		return userservice.getUserById(id);
	}
	
	@GetMapping
	public ResponseEntity<ResponseStructure<List<userdetails>>>getAllUsers(){
		return userservice.getAllUser();
	}
	

}
