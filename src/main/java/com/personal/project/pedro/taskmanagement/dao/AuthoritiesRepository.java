package com.personal.project.pedro.taskmanagement.dao;

import com.personal.project.pedro.taskmanagement.entities.Authorities;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthoritiesRepository extends JpaRepository<Authorities, Long> {
}
