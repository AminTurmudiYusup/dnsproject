package com.example.dnsproject.controller;

import com.example.dnsproject.entity.Job;
import com.example.dnsproject.exception.NotFoundException;
import com.example.dnsproject.repository.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class JobController {

    @Autowired
    private JobRepository jobRepository;

    @GetMapping("/job")//api for handle request to get all job
    public List<Job> getAllJob(){
        return jobRepository.findAll();
    }

    @GetMapping("/job/{id}")//api fro handle request to get spesific job
    public Optional<Job> getJob(@PathVariable("id") Long id){
        return Optional.ofNullable(jobRepository.findById(id).orElseThrow(() -> new NotFoundException("Job with id " + id + " NotFound")));
    }

    @PostMapping("/job")//api for handle request for save product
    public Job addProduct(@RequestBody Job job){
        return jobRepository.save(job);
    }

    @PutMapping("/product")//api for handle request to update product
    public Job editProduct(@RequestBody Job newJob) throws Exception {
        String s = "Not FOund";
        return jobRepository.findById(newJob.getId())
                .map(job -> {
                    if (job!=null){
                        job.setJobName(newJob.getJobName());
                        job.setJobDescription(newJob.getJobDescription());
                        return jobRepository.save(job);
                    }
                    return newJob;
                }).orElseThrow(()-> new  Exception("Not Found"));
    }

    @DeleteMapping("/job/{id}")//api for handle request to delete product
    public void deleteJob(@PathVariable("id")Long id){
        jobRepository.deleteById(id);
    }
}
