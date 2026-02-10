package intern.example.demo.dao;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import intern.example.demo.Entity.StudyLogEntity;
import intern.example.demo.Repository.StudyLog;

@Repository
public class StudyDAO {

    @Autowired
    private StudyLog studyLogRepository;

    // Line 18
    public StudyLogEntity saveStudyLog(StudyLogEntity study) {
        return studyLogRepository.save(study);
    }

    // Line 23 ✅ FIXED
    public List<StudyLogEntity> findByUserIdAndStudyDate(Integer userId, LocalDate date) {
        return studyLogRepository.findByUser_IdAndStudyDate(userId, date);
    }

    // Line 28 ✅ FIXED
    public List<StudyLogEntity> findByUserId(Integer userId) {
        return studyLogRepository.findByUser_Id(userId);
    }

    // Line 33
    public Optional<StudyLogEntity> findById(Integer id) {
        return studyLogRepository.findById(id);
    }

    // Line 38 ✅ FIXED
    public Integer countByUserId(Integer userId) {
        return studyLogRepository.countByUser_Id(userId);
    }

    // Line 43 ✅ FIXED
    public Optional<StudyLogEntity> findTopByUserIdOrderByStudyDateDesc(Integer userId) {
        return studyLogRepository.findTopByUser_IdOrderByStudyDateDesc(userId);
    }

    // Line 48
    public void deleteStudyLog(Integer logId) {
        studyLogRepository.deleteById(logId);
    }

    // Line 53
    public Integer sumStudyHoursByUserId(Integer userId) {
        return studyLogRepository.sumStudyHoursByUserId(userId);
    }
}
