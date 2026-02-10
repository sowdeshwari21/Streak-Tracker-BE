package intern.example.demo.Entity;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;

@Entity
public class Streak {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer userId;

    private Integer currentStreak;
    private Integer longestStreak;
    private LocalDate lastStudyDate;
    private Integer totalStudyDays;
	public Integer getTotalStudyDays() {
		return totalStudyDays;
	}
	public void setTotalStudyDays(Integer totalStudyDays) {
		this.totalStudyDays = totalStudyDays;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public Integer getCurrentStreak() {
		return currentStreak;
	}
	public void setCurrentStreak(Integer currentStreak) {
		this.currentStreak = currentStreak;
	}
	public Integer getLongestStreak() {
		return longestStreak;
	}
	public void setLongestStreak(Integer longestStreak) {
		this.longestStreak = longestStreak;
	}
	public LocalDate getLastStudyDate() {
		return lastStudyDate;
	}
	public void setLastStudyDate(LocalDate lastStudyDate) {
		this.lastStudyDate = lastStudyDate;
	}
	
	@OneToOne
	@JoinColumn(name="user_id")
	private userdetails userdetails;
	public userdetails getUserdetails() {
		return userdetails;
	}
	public void setUserdetails(userdetails userdetails) {
		this.userdetails = userdetails;
	}
	

}
