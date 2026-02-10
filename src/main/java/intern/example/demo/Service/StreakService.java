package intern.example.demo.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import intern.example.demo.Entity.Streak;
import intern.example.demo.Entity.userdetails;
import intern.example.demo.dao.StreakDAO;
import intern.example.demo.dao.StudyDAO;
import intern.example.demo.dao.userRegDAO;
import intern.example.demo.dto.ResponseStructure;

@Service
public class StreakService {
    @Autowired
	private StreakDAO streakdao;
    @Autowired
	private StudyDAO studydao;
    @Autowired
	private userRegDAO userdao;
	
	
	public void updateStreak(userdetails user,LocalDate studyDate) {
		
		LocalDate yesterday=studyDate.minusDays(1);
		
		Optional<Streak> optionalstreak=streakdao.findByUserId(user.getId());
		
		Streak streak;
		
		if(optionalstreak.isEmpty()) {
			streak=new Streak();
			streak.setUserdetails(user);
			streak.setCurrentStreak(1);
			streak.setLongestStreak(1);
		}else {
			streak=optionalstreak.get();
			LocalDate lastdate=streak.getLastStudyDate();
			if(lastdate!=null&&lastdate.equals(yesterday)) {
				streak.setCurrentStreak(streak.getCurrentStreak()+1);
			}else {
				streak.setCurrentStreak(1);
			}
			if(streak.getCurrentStreak()>streak.getLongestStreak()) {
				streak.setLongestStreak(streak.getCurrentStreak());
			}
		}
		streak.setLastStudyDate(studyDate);
        streakdao.saveStreak(streak);		
	}
	
	
	 public ResponseEntity<ResponseStructure<Streak>> getDashboard(Integer userId){

	        Optional<userdetails> user = userdao.findById(userId);
	        if(user.isEmpty()) {
	            throw new RuntimeException("User not found with id: " + userId);
	        }

	        Optional<Streak> streak = streakdao.findByUserId(userId);

	        if(streak.isEmpty()){
	            Streak newStreak = new Streak();
	            newStreak.setUserdetails(user.get());
	            newStreak.setCurrentStreak(0);
	            newStreak.setLongestStreak(0);
	            newStreak.setTotalStudyDays(0);
	            streakdao.saveStreak(newStreak);
	        }
	        Integer totalDays =studydao.countByUserId(userId);
	        streak.get().setTotalStudyDays(totalDays);
	        streakdao.saveStreak(streak.get());

	        ResponseStructure<Streak> structure = new ResponseStructure<>();
	        structure.setStatucode(HttpStatus.OK.value());
	        structure.setMessage("Dashboard retrieved successfully");
	        structure.setData(streak.get());

	        return new ResponseEntity<>(structure, HttpStatus.OK);
}
	 
//	 public ResponseEntity<ResponseStructure<Streak>> getStreakByUserId(Integer userId) {
//	        Optional<userdetails> user = userdao.findById(userId);
//	        if (user.isEmpty()) {
//	            throw new RuntimeException("User not found with id: " + userId);
//	        }
//
//	        Optional<Streak> streak = streakdao.findByUserId(userId);
//	        if (streak.isEmpty()) {
//	            Streak newStreak = new Streak();
//	            newStreak.setUserdetails(user.get());
//	            newStreak.setCurrentStreak(0);
//	            newStreak.setLongestStreak(0);
//	            newStreak.setTotalStudyDays(0);
//	            streakdao.saveStreak(newStreak);
//	        }
//	        ResponseStructure<Streak> structure = new ResponseStructure<>();
//	        structure.setStatucode(HttpStatus.OK.value());
//	        structure.setMessage("Streak retrieved successfully");
//	        structure.setData(streak.get());
//
//	        return new ResponseEntity<>(structure, HttpStatus.OK);
//	 }
	 
	 public ResponseEntity<ResponseStructure<List<Streak>>> getAllUsersStreak(){

	      List<Streak> streaks=streakdao.getAllUserStreak();

	        ResponseStructure<List<Streak>> structure = new ResponseStructure<>();
	        structure.setStatucode(HttpStatus.OK.value());
	        structure.setMessage("Streak retrieved successfully");
	        structure.setData(streaks);

	        return new ResponseEntity<>(structure, HttpStatus.OK);
}
	 
}
