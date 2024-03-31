package com.lsalmeida.course.service.impl;

import com.lsalmeida.course.exception.LessonNotFoundException;
import com.lsalmeida.course.exception.ModuleNotFoundException;
import com.lsalmeida.course.mapper.CourseMapper;
import com.lsalmeida.course.model.LessonModel;
import com.lsalmeida.course.model.ModuleModel;
import com.lsalmeida.course.model.dto.LessonDto;
import com.lsalmeida.course.repository.LessonRepository;
import com.lsalmeida.course.repository.ModuleRepository;
import com.lsalmeida.course.service.LessonService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class LessonServiceImpl implements LessonService {

    private final LessonRepository lessonRepository;
    private final ModuleRepository moduleRepository;
    private final CourseMapper mapper;

    @Override
    public LessonDto findLessonIntoModule(UUID moduleId, UUID lessonId) {
        return lessonRepository.findLessonIntoModule(moduleId, lessonId)
                .map(mapper::toDto)
                .orElseThrow(LessonNotFoundException::new);
    }

    @Override
    public List<LessonModel> findAllByModule(UUID moduleId) {
        return lessonRepository.findAllLessonsIntoModule(moduleId);
    }

    @Override
    public Page<LessonModel> findAllByModule(Specification<LessonModel> spec, Pageable pageable) {
        return lessonRepository.findAll(spec, pageable);
    }

    @Override
    public LessonDto save(UUID moduleId, LessonDto lessonDto) {
        ModuleModel moduleModel = moduleRepository.findById(moduleId).orElseThrow(ModuleNotFoundException::new);
        LessonModel lessonModel = mapper.fromDto(moduleModel, lessonDto);
        LessonModel saved = lessonRepository.save(lessonModel);
        return mapper.toDto(saved);
    }

    @Override
    public LessonDto update(UUID moduleId, LessonDto lessonDto) {
        LessonModel lessonModel = lessonRepository.findById(moduleId)
                .map(lesson -> mapper.updateLesson(lesson, lessonDto))
                .orElseThrow(LessonNotFoundException::new);
        LessonModel updated = lessonRepository.save(lessonModel);
        return mapper.toDto(updated);
    }

    @Override
    public void deleteLessonOfModule(UUID lessonId, UUID moduleId) {
        if (!moduleRepository.existsById(moduleId)) throw new ModuleNotFoundException();
        if (!lessonRepository.existsById(lessonId)) throw new LessonNotFoundException();
        lessonRepository.deleteLessonOfModule(moduleId, lessonId);
    }

}
