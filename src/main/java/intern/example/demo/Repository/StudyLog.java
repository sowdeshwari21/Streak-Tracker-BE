package intern.example.demo.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import intern.example.demo.Entity.StudyLogEntity;

public interface StudyLog extends JpaRepository<StudyLogEntity, Integer> {

    // ✅ Correct: navigate through user -> id
    List<StudyLogEntity> findByUser_IdAndStudyDate(Integer userId, LocalDate studyDate);

    // ✅ Correct
    List<StudyLogEntity> findByUser_Id(Integer userId);

    // ✅ Correct
    Integer countByUser_Id(Integer userId);

    // ✅ Correct JPQL (fixed Id → id)
    @Query("SELECT SUM(s.hoursStudied) FROM StudyLogEntity s WHERE s.user.id = :userId")
    Integer sumStudyHoursByUserId(@Param("userId") Integer userId);

    // ✅ Correct
    Optional<StudyLogEntity> findTopByUser_IdOrderByStudyDateDesc(Integer userId);

	//List<StudyLogEntity> findByUserId(Integer userId);
}
