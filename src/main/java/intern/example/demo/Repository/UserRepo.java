package intern.example.demo.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

//import intern.example.demo.Entity.user;
import intern.example.demo.Entity.userdetails;

public interface UserRepo extends JpaRepository<userdetails,Integer>{
	Optional<userdetails> findByEmail(String email);

}
