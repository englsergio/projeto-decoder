package com.lsalmeida.course.service;

import com.lsalmeida.course.model.CourseModel;
import com.lsalmeida.course.model.dto.CourseDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;
import java.util.UUID;

public interface CourseService {

    CourseDto findById(UUID id);
    Page<CourseModel> findAll(Specification<CourseModel> spec,
                              Pageable pageable);
    CourseDto save(CourseDto dto);
    CourseDto update(UUID id, CourseDto dto);
    void deleteById(UUID id);
    void delete(CourseModel courseModel);
    List<CourseModel> findAll();

}
