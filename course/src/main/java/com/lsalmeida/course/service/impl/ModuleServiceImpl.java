package com.lsalmeida.course.service.impl;

import com.lsalmeida.course.exception.ModuleNotFoundException;
import com.lsalmeida.course.mapper.CourseMapper;
import com.lsalmeida.course.model.CourseModel;
import com.lsalmeida.course.model.ModuleModel;
import com.lsalmeida.course.model.dto.ModuleDto;
import com.lsalmeida.course.repository.CourseRepository;
import com.lsalmeida.course.repository.LessonRepository;
import com.lsalmeida.course.repository.ModuleRepository;
import com.lsalmeida.course.service.ModuleService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.*;

@RequiredArgsConstructor
@Service
public class ModuleServiceImpl implements ModuleService {

    private final ModuleRepository moduleRepository;
    private final LessonRepository lessonRepository;
    private final CourseRepository courseRepository;
    private final CourseMapper mapper;

    @Override
    public Page<ModuleModel> findAllByCourse(Specification<ModuleModel> spec, Pageable pageable) {
        return moduleRepository.findAll(spec, pageable);
    }

    @Override
    public List<ModuleDto> findAllByCourse(UUID courseId) {
        List<ModuleModel> modulesList = moduleRepository.findAllModulesIntoCourse(courseId);
        return mapper.toDto(modulesList);
    }

    @Override
    public ModuleDto findModuleIntoCourse(UUID courseId, UUID moduleId) {
        ModuleModel moduleModel = moduleRepository.findModuleIntoCourse(courseId, moduleId);
        return mapper.toDto(moduleModel);
    }

    @Override
    public ModuleDto findById(UUID id) {
        ModuleModel moduleModel = moduleRepository.findById(id).orElseThrow(ModuleNotFoundException::new);
        return mapper.toDto(moduleModel);
    }

    @Override
    public ModuleDto save(UUID courseId, ModuleDto dto) throws ClassNotFoundException {
        CourseModel courseModel = courseRepository.findById(courseId).orElseThrow(ClassNotFoundException::new);
        ModuleModel moduleModel = mapper.fromDto(courseModel, dto);
        ModuleModel saved = moduleRepository.save(moduleModel);
        return mapper.toDto(saved);
    }

    @Override
    public ModuleDto update(UUID moduleId, ModuleDto dto) {
        ModuleModel moduleModel = moduleRepository.findById(moduleId)
                .map(module -> mapper.updateModule(module, dto))
                .orElseThrow(ModuleNotFoundException::new);
        ModuleModel updated = moduleRepository.save(moduleModel);
        return mapper.toDto(updated);
    }

    @Transactional
    @Override
    public void delete(ModuleModel moduleModel) {
        UUID moduleId = moduleModel.getModuleId();
        lessonRepository.deleteAllLessonsIntoModules(new HashSet<>(Collections.singletonList(moduleId)));
        moduleRepository.deleteById(moduleId);
    }
}
