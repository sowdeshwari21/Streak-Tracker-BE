package intern.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
//import org.hibernate.mapping.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import intern.example.demo.Entity.StudyLogEntity;
//import intern.example.demo.Repository.StudyLog;
import intern.example.demo.Service.StudyService;
import intern.example.demo.dto.ResponseStructure;

@RestController
@RequestMapping("/api/studies")
public class studycontroller {

	
	@Autowired
	private  StudyService studyService;

    @PostMapping("/log")
    public ResponseEntity<ResponseStructure<StudyLogEntity>> addStudyLog(@RequestBody StudyLogEntity studyLog) {
        return studyService.addStudyLog(studyLog);
    }

    @GetMapping("/history/{userId}")
    public ResponseEntity<ResponseStructure<List<StudyLogEntity>>> getStudyHistory(@PathVariable Integer userId) {
        return studyService.getStudyHistory(userId);
    }

    @GetMapping("/total-hours/{userId}")
    public ResponseEntity<ResponseStructure<Integer>> getTotalStudyHours(@PathVariable Integer userId) {
        return studyService.getTotalStudyHours(userId);
    }

    @PatchMapping("/log/{logId}")
    public ResponseEntity<ResponseStructure<StudyLogEntity>> updateStudyLog(@PathVariable Integer logId, @RequestBody StudyLogEntity updatedLog) {
        return studyService.updateStudyLog(logId, updatedLog);
    }

    @DeleteMapping("/log/{logId}")
    public ResponseEntity<ResponseStructure<String>> deleteStudyLog(@PathVariable Integer logId) {
        return studyService.deleteStudyLog(logId);
    }

}
