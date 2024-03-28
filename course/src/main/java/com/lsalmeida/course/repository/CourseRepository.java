package com.lsalmeida.course.repository;

import com.lsalmeida.course.model.CourseModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CourseRepository extends JpaRepository<CourseModel, UUID> {
}