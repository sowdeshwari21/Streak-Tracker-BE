package intern.example.demo.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import intern.example.demo.Entity.Streak;

public interface StreakRepo extends JpaRepository<Streak,Integer>{
	Optional<Streak>  findByUserId(Integer userid);
		
	

}
