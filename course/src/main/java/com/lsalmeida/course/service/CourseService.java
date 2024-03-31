package com.lsalmeida.course.service;

import com.lsalmeida.course.model.CourseModel;
import com.lsalmeida.course.model.dto.CourseDto;

import java.util.List;
import java.util.UUID;

public interface CourseService {

    CourseDto findById(UUID id);
    CourseDto save(CourseDto dto);
    CourseDto update(UUID id, CourseDto dto);
    void deleteById(UUID id);
    void delete(CourseModel courseModel);
    List<CourseModel> findAll();

}
