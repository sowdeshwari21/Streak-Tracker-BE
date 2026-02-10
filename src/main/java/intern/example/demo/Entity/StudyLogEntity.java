package intern.example.demo.Entity;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class StudyLogEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String subject;
	private Integer hoursStudied;
	private LocalDate studyDate;
	
	public Integer getHoursStudied() {
		return hoursStudied;
	}
	public void setHoursStudied(Integer hoursStudied) {
		this.hoursStudied = hoursStudied;
	}
	public LocalDate getStudyDate() {
		return studyDate;
	}
	public void setStudyDate(LocalDate studyDates) {
		this.studyDate = studyDates;
	}
	public userdetails getUsers() {
		return user;
	}
	public void setUsers(userdetails users) {
		this.user = users;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	

	//private LocalDate studyDate;
	
	public userdetails getUser() {
		return user;
	}
	public void setUser(userdetails user) {
		this.user = user;
	}





	@ManyToOne
	@JoinColumn(name="user_id")
	private userdetails user;

}
