package com.lsalmeida.course.service;

import com.lsalmeida.course.model.ModuleModel;
import com.lsalmeida.course.model.dto.ModuleDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;
import java.util.UUID;

public interface ModuleService {
    ModuleDto findById(UUID id);

    ModuleDto save(UUID courseId, ModuleDto dto) throws ClassNotFoundException;

    ModuleDto update(UUID moduleId, ModuleDto dto);

    void delete(ModuleModel moduleModel);

    Page<ModuleModel> findAllByCourse(Specification<ModuleModel> and, Pageable pageable);

    List<ModuleDto> findAllByCourse(UUID courseId);

    ModuleDto findModuleIntoCourse(UUID courseId, UUID moduleId);
}
