package com.lsalmeida.course.service.impl;

import com.lsalmeida.course.exception.CourseNotFoundException;
import com.lsalmeida.course.mapper.CourseMapper;
import com.lsalmeida.course.model.CourseModel;
import com.lsalmeida.course.model.ModuleModel;
import com.lsalmeida.course.model.dto.CourseDto;
import com.lsalmeida.course.repository.CourseRepository;
import com.lsalmeida.course.repository.LessonRepository;
import com.lsalmeida.course.repository.ModuleRepository;
import com.lsalmeida.course.service.CourseService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class CourseServiceImpl implements CourseService {

    private final CourseRepository courseRepository;
    private final ModuleRepository moduleRepository;
    private final LessonRepository lessonRepository;
    private final CourseMapper mapper;

    @Override
    public CourseDto findById(UUID id) {
        return courseRepository.findById(id)
                .map(mapper::toDto)
                .orElseThrow(CourseNotFoundException::new);
    }

    @Override
    public List<CourseModel> findAll() {
        return courseRepository.findAll();
    }

    @Override
    public CourseDto save(CourseDto dto) {
        CourseModel courseModel = mapper.fromDto(dto);
        CourseModel saved = courseRepository.save(courseModel);
        return mapper.toDto(saved);
    }

    @Override
    public CourseDto update(UUID id, CourseDto dto) {
        CourseModel course = courseRepository.findById(id)
                .map(c -> mapper.updateCourse(c, dto))
                .orElseThrow(CourseNotFoundException::new);
        CourseModel updated = courseRepository.save(course);
        return mapper.toDto(updated);
    }

    @Override
    public void deleteById(UUID id) {
        courseRepository.findById(id).ifPresentOrElse(
                courseRepository::delete,
                () -> {throw new CourseNotFoundException();});
    }

    @Transactional
    @Override
    public void delete(CourseModel courseModel) {
        Set<UUID> collectedModulesIds = moduleRepository.findAllModulesIntoCourse(courseModel.getCourseId()).stream()
                .map(ModuleModel::getModuleId)
                .collect(Collectors.toSet());
        lessonRepository.deleteAllLessonsIntoModules(collectedModulesIds);
        moduleRepository.deleteAllById(collectedModulesIds);
        courseRepository.delete(courseModel);
    }

}
