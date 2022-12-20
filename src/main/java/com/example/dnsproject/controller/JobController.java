package com.example.dnsproject.controller;

import com.example.dnsproject.model.Job;
import com.example.dnsproject.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
public class JobController {


    @Autowired
    private RestTemplate restTemplate;

    private final String JOB_URI = "http://dev3.dansmultipro.co.id/api/recruitment/positions.json";
    private final String JOB_URI_BY_ID = "http://dev3.dansmultipro.co.id/api/recruitment/positions/";

    @GetMapping("/job")//api for handle request to get all job
    public List<Job> getAllJob(){
        ResponseEntity<List<Job>> responseEntity=restTemplate.exchange(JOB_URI, HttpMethod.GET, null, new ParameterizedTypeReference<List<Job>>() {
        });
        List <Job> listJob=responseEntity.getBody();
        return listJob;
    }

    @GetMapping("/job/{id}")//api fro handle request to get spesific job
    public Job getJob(@PathVariable("id") String id){
        Job job=restTemplate.getForObject(JOB_URI_BY_ID+id, Job.class);
        if(job.getId()==null){
            throw new NotFoundException("Job with id "+id+ " Not Found");
        }
        return job;
    }
}
