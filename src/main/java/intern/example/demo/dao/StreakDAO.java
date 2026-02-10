package intern.example.demo.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import intern.example.demo.Entity.Streak;
import intern.example.demo.Repository.StreakRepo;

@Repository
public class StreakDAO {
	@Autowired

	private StreakRepo streakrepo;
	
	
	//1.save
	public Streak saveStreak(Streak streak) {
		return streakrepo.save(streak);
	}
	
	//2.findbyid
	public Optional<Streak> findById(Integer id){
		return streakrepo.findById(id);
	}
	
	//3.findbyuserid
	public Optional<Streak> findByUserId(Integer userid){
		return streakrepo.findByUserId(userid);
	}
	
	//4.getalluserstreak
	public List<Streak> getAllUserStreak(){
		return streakrepo.findAll();
	}
	
}
