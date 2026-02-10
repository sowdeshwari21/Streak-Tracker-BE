package intern.example.demo.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import intern.example.demo.Entity.userdetails;
import intern.example.demo.Repository.UserRepo;

@Repository
public class userRegDAO {
	@Autowired
	private UserRepo userrepo;

	//1.save user
	public userdetails saveUser(userdetails use) {
		return userrepo.save(use);
	}
	
	//2.findbyid
	public Optional<userdetails> findById(Integer id){
		return userrepo.findById(id);
	}
	
	//3.findbyemail
	public Optional<userdetails> findByEmail(String email){
		return userrepo.findByEmail(email);
	}
	
	public userRegDAO(UserRepo userrepo) {
		super();
		this.userrepo = userrepo;
	}

	//4.findAlluser
	public List<userdetails> findAllUsers(){
		return userrepo.findAll();
	}
}
