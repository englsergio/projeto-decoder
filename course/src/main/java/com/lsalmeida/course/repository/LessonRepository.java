package com.lsalmeida.course.repository;

import com.lsalmeida.course.model.LessonModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface LessonRepository extends JpaRepository<LessonModel, UUID> {
}