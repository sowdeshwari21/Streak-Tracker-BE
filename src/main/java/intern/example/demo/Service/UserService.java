package intern.example.demo.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import intern.example.demo.Entity.userdetails;
import intern.example.demo.dao.userRegDAO;
import intern.example.demo.dto.ResponseStructure;

@Service
public class UserService {
	@Autowired
	private userRegDAO userdao;

	
	
	//1.saveuser
	
	public ResponseEntity<ResponseStructure<userdetails>> saveUser(userdetails use){
		userdetails savedUser = userdao.saveUser(use);
		ResponseStructure<userdetails> structure=new ResponseStructure<>();
		structure.setStatucode(HttpStatus.CREATED.value());
		structure.setMessage("User created successfully");
		structure.setData(savedUser);
		
		return new ResponseEntity<>(structure,HttpStatus.CREATED);
	}

	
	//2.login user
	public ResponseEntity<ResponseStructure<userdetails>> loginUser(String email,String password){
		Optional<userdetails> user=userdao.findByEmail(email);
		if(user.isEmpty()||!user.get().getPassword().equals(password)) {
			throw new RuntimeException("Invalid email or password");
		}
		
		ResponseStructure<userdetails> structure=new ResponseStructure<>();
		structure.setStatucode(HttpStatus.CREATED.value());
		structure.setMessage("Login successfull");
		structure.setData(user.get());
		return new ResponseEntity<>(structure,HttpStatus.OK);
	}
		
	//3.get user by id
	public ResponseEntity<ResponseStructure<userdetails>> getUserById(Integer id){
		Optional<userdetails> user=userdao.findById(id);
		if(user.isEmpty()) {
			throw new RuntimeException("User not found with id "+id);	
		}
		ResponseStructure<userdetails> structure=new ResponseStructure<>();
		structure.setStatucode(HttpStatus.CREATED.value());
		structure.setMessage("User Retrieved successfull");
		structure.setData(user.get());
		return new ResponseEntity<>(structure,HttpStatus.OK);
	}
	
	//4.getalluser
	public ResponseEntity<ResponseStructure<List<userdetails>>> getAllUser(){
		List<userdetails> users=userdao.findAllUsers();
		ResponseStructure<List<userdetails>> structure=new ResponseStructure<>();
		structure.setStatucode(HttpStatus.OK.value());
		structure.setMessage("User Retrieved successfull");
		structure.setData(users);
		return new ResponseEntity<>(structure,HttpStatus.OK);
		
	}
}
