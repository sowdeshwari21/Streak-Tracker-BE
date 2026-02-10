package intern.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import intern.example.demo.Entity.Streak;
import intern.example.demo.Service.StreakService;
import intern.example.demo.dto.ResponseStructure;

@RestController
@RequestMapping("/api/streaks")
public class StreakController {
	
	@Autowired
	public final StreakService streakservice;
	
	 public StreakController(StreakService streakservice) {
		super();
		this.streakservice = streakservice;
	}



	 @GetMapping("/dashboard/{userId}")
	    public ResponseEntity<ResponseStructure<Streak>> getDashboard(
	            @PathVariable Integer userId) {
	        return streakservice.getDashboard(userId);
	    }
	 
	 

	    @GetMapping()
	    public ResponseEntity<ResponseStructure<List<Streak>>> getAllUsersStreaks() {
	        return streakservice.getAllUsersStreak();
	    }

	

}
