
package intern.example.demo.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import intern.example.demo.Entity.StudyLogEntity;
import intern.example.demo.Entity.userdetails;
import intern.example.demo.dao.StreakDAO;
import intern.example.demo.dao.StudyDAO;
import intern.example.demo.dao.userRegDAO;
import intern.example.demo.dto.ResponseStructure;

@Service
public class StudyService {

    @Autowired
    private StudyDAO studydao;

    @Autowired
    private userRegDAO userdao;

    @Autowired
    private StreakService streakservice;

    // ================= ADD STUDY LOG =================
    public ResponseEntity<ResponseStructure<StudyLogEntity>> addStudyLog(StudyLogEntity studyLog) {

        LocalDate today = studyLog.getStudyDate();
        Integer userId = studyLog.getUser().getId();

        Optional<userdetails> user = userdao.findById(userId);
        if (user.isEmpty()) {
            throw new RuntimeException("User not found with id: " + userId);
        }

        List<StudyLogEntity> existingLog =
                studydao.findByUserIdAndStudyDate(userId, today);

        if (!existingLog.isEmpty()) {
            throw new RuntimeException("Study log already exists for this date");
        }

        studyLog.setUser(user.get());
        studyLog.setStudyDate(today);

        StudyLogEntity savedLog = studydao.saveStudyLog(studyLog);

        streakservice.updateStreak(user.get(), today);

        ResponseStructure<StudyLogEntity> structure = new ResponseStructure<>();
        structure.setStatucode(HttpStatus.CREATED.value());
        structure.setMessage("Study log added successfully");
        structure.setData(savedLog);

        return new ResponseEntity<>(structure, HttpStatus.CREATED);
    }

    // ================= UPDATE STUDY LOG =================
    public ResponseEntity<ResponseStructure<StudyLogEntity>> updateStudyLog(
            Integer logId, StudyLogEntity updatedLog) {

        Optional<StudyLogEntity> existingLog = studydao.findById(logId);
        if (existingLog.isEmpty()) {
            throw new RuntimeException("Study log not found with id: " + logId);
        }

        StudyLogEntity log = existingLog.get();

        if (updatedLog.getSubject() != null && !updatedLog.getSubject().isBlank()) {
            log.setSubject(updatedLog.getSubject());
        }

        if (updatedLog.getStudyDate() != null) {
            log.setStudyDate(updatedLog.getStudyDate());
        }

        if (updatedLog.getHoursStudied() != null && updatedLog.getHoursStudied() > 0) {
            log.setHoursStudied(updatedLog.getHoursStudied());
        }

        StudyLogEntity savedLog = studydao.saveStudyLog(log);

        ResponseStructure<StudyLogEntity> structure = new ResponseStructure<>();
        structure.setStatucode(HttpStatus.OK.value());
        structure.setMessage("Study log updated successfully");
        structure.setData(savedLog);

        return new ResponseEntity<>(structure, HttpStatus.OK);
    }

    // ================= GET STUDY HISTORY =================
    public ResponseEntity<ResponseStructure<List<StudyLogEntity>>> getStudyHistory(Integer userId) {

        Optional<userdetails> user = userdao.findById(userId);
        if (user.isEmpty()) {
            throw new RuntimeException("User not found with id: " + userId);
        }

        List<StudyLogEntity> studyLogs = studydao.findByUserId(userId);

        ResponseStructure<List<StudyLogEntity>> structure = new ResponseStructure<>();
        structure.setStatucode(HttpStatus.OK.value());
        structure.setMessage("Study history retrieved successfully");
        structure.setData(studyLogs);

        return new ResponseEntity<>(structure, HttpStatus.OK);
    }

    // ================= TOTAL STUDY HOURS =================
    public ResponseEntity<ResponseStructure<Integer>> getTotalStudyHours(Integer userId) {

        Optional<userdetails> user = userdao.findById(userId);
        if (user.isEmpty()) {
            throw new RuntimeException("User not found with id: " + userId);
        }

        Integer totalHours = studydao.sumStudyHoursByUserId(userId);

        ResponseStructure<Integer> structure = new ResponseStructure<>();
        structure.setStatucode(HttpStatus.OK.value());
        structure.setMessage("Total study hours retrieved successfully");
        structure.setData(totalHours);

        return new ResponseEntity<>(structure, HttpStatus.OK);
    }

    // ================= DELETE STUDY LOG =================
    public ResponseEntity<ResponseStructure<String>> deleteStudyLog(Integer logId) {

        Optional<StudyLogEntity> existingLog = studydao.findById(logId);
        if (existingLog.isEmpty()) {
            throw new RuntimeException("Study log not found with id: " + logId);
        }

        studydao.deleteStudyLog(logId);

        ResponseStructure<String> structure = new ResponseStructure<>();
        structure.setStatucode(HttpStatus.OK.value());
        structure.setMessage("Study log deleted successfully");
        structure.setData("Deleted log id: " + logId);

        return new ResponseEntity<>(structure, HttpStatus.OK);
    }
}

