package com.simo333.spring.projects.ordersmanager.resources;

import com.simo333.spring.projects.ordersmanager.model.JobPosition;
import com.simo333.spring.projects.ordersmanager.service.JobPositionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/job-positions")
public class JobPositionController {

    private final JobPositionService service;

    @Autowired
    public JobPositionController(JobPositionService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<JobPosition>> findAllJobPositions() {
        List<JobPosition> jobPositionList = service.findAllJobPositions();
        return new ResponseEntity<>(jobPositionList, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<JobPosition> findJobPositionById(@PathVariable("id") Long id) {
        JobPosition actualJobPosition = service.findJobPositionById(id);
        return new ResponseEntity<>(actualJobPosition, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<JobPosition> newJobPosition(@RequestBody JobPosition jobPosition) {
        JobPosition newJobPosition = service.addJobPosition(jobPosition);
        return new ResponseEntity<>(newJobPosition, HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<JobPosition> updateJobPosition(@RequestBody JobPosition jobPosition) {
        JobPosition actualJobPosition = service.updateJobPosition(jobPosition);
        return new ResponseEntity<>(actualJobPosition, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteJobPosition(@PathVariable("id") Long id) {
        JobPosition actualJobPosition = service.findJobPositionById(id);
        if(actualJobPosition == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        service.deleteJobPositionById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
