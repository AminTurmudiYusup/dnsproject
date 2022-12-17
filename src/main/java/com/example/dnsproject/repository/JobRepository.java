package com.example.dnsproject.repository;

import com.example.dnsproject.entity.Job;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JobRepository extends JpaRepository<Job, Long> {
}
