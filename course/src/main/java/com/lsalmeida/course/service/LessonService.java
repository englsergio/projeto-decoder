package com.lsalmeida.course.service;

import com.lsalmeida.course.model.LessonModel;
import com.lsalmeida.course.model.dto.LessonDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;
import java.util.UUID;

public interface LessonService {
    LessonDto save(UUID moduleId, LessonDto lessonDto);

    LessonDto update(UUID moduleId, LessonDto lessonDto);

    void deleteLessonOfModule(UUID lessonId, UUID moduleId);

    LessonDto findLessonIntoModule(UUID moduleId, UUID lessonId);

    List<LessonModel> findAllByModule(UUID moduleId);

    Page<LessonModel> findAllByModule(Specification<LessonModel> and, Pageable pageable);
}
