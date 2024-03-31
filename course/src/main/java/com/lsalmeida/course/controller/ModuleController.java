package com.lsalmeida.course.controller;

import com.lsalmeida.course.model.ModuleModel;
import com.lsalmeida.course.model.dto.CourseDto;
import com.lsalmeida.course.model.dto.ModuleDto;
import com.lsalmeida.course.service.CourseService;
import com.lsalmeida.course.service.ModuleService;
import com.lsalmeida.course.specification.SpecificationTemplate;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@RestController
@RequestMapping
@CrossOrigin(origins = "*", maxAge = 3600)
public class ModuleController {

    private final ModuleService moduleService;
    private final CourseService courseService;

    @GetMapping("/courses/{courseId}/modules")
    public ResponseEntity<Page<ModuleModel>> getAll(@PathVariable("courseId") UUID id,
                                                    SpecificationTemplate.ModuleSpec spec,
                                                    @PageableDefault(sort = "moduleId", direction = Sort.Direction.ASC) Pageable pageable) {
        return ResponseEntity.ok(moduleService.findAllByCourse(SpecificationTemplate.moduleCourseId(id).and(spec), pageable));
    }

    @GetMapping("/courses/{courseId}/modules/{moduleId}")
    public ResponseEntity<ModuleDto> getById(@PathVariable UUID courseId,
                                             @PathVariable UUID moduleId) {
        return ResponseEntity.ok(moduleService.findModuleIntoCourse(courseId, moduleId));
    }

    @GetMapping("{courseId}")
    public List<ModuleDto> getAll(@PathVariable UUID courseId) {
        return moduleService.findAllByCourse(courseId);
    }

    @PostMapping
    public ResponseEntity<CourseDto> save(@RequestBody @Valid CourseDto dto) {
        CourseDto saved = courseService.save(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    @PutMapping("/{moduleId}")
    public ResponseEntity<CourseDto> update(@PathVariable UUID moduleId, @RequestBody @Valid CourseDto dto) {
        return ResponseEntity.ok(courseService.update(moduleId, dto));
    }

    @DeleteMapping("/{moduleId}")
    public ResponseEntity<Void> delete(@PathVariable UUID moduleId) {
        courseService.deleteById(moduleId);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

}