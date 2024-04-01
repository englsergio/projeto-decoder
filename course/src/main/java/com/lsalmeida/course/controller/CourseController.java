package com.lsalmeida.course.controller;

import com.lsalmeida.course.model.CourseModel;
import com.lsalmeida.course.model.dto.CourseDto;
import com.lsalmeida.course.service.CourseService;
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
@RequestMapping("/courses")
@CrossOrigin(origins = "*", maxAge = 3600)
public class CourseController {

    private final CourseService courseService;

    @GetMapping("/{id}")
    public ResponseEntity<CourseDto> getById(@PathVariable UUID id) {
        return ResponseEntity.ok(courseService.findById(id));
    }

    @GetMapping
    public ResponseEntity<Page<CourseModel>> getAllCourses(SpecificationTemplate.CourseSpec spec,
                                                           @PageableDefault(direction = Sort.Direction.ASC) Pageable pageable,
                                                           @RequestParam(required = false) UUID userId) {
        Page<CourseModel> courseResponse;
        if (userId == null) courseResponse = courseService.findAll(spec, pageable);
        else courseResponse = courseService.findAll(SpecificationTemplate.courseUserId(userId).and(spec), pageable);
        return ResponseEntity.ok(courseResponse);
    }

    @GetMapping
    public ResponseEntity<List<CourseModel>> getAll() {
        return ResponseEntity.ok(courseService.findAll());
    }

    @PostMapping
    public ResponseEntity<CourseDto> save(@RequestBody @Valid CourseDto dto) {
        CourseDto saved = courseService.save(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CourseDto> update(@PathVariable UUID id, @RequestBody @Valid CourseDto dto) {
        return ResponseEntity.ok(courseService.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        courseService.deleteById(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

}
