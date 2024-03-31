package com.lsalmeida.course.controller;

import com.lsalmeida.course.model.LessonModel;
import com.lsalmeida.course.model.dto.LessonDto;
import com.lsalmeida.course.service.LessonService;
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

import java.util.Optional;
import java.util.UUID;

@RequiredArgsConstructor
@RestController
@RequestMapping("/lessons")
@CrossOrigin(origins = "*", maxAge = 3600)
public class LessonController {

    private final LessonService lessonService;

    @GetMapping("/modules/{moduleId}/lessons")
    public ResponseEntity<Page<LessonModel>> getAll(@PathVariable(value="moduleId") UUID moduleId,
                                                    SpecificationTemplate.LessonSpec spec,
                                                    @PageableDefault(direction = Sort.Direction.ASC) Pageable pageable) {
        return ResponseEntity.status(HttpStatus.OK).body(lessonService.findAllByModule(SpecificationTemplate.lessonModuleId(moduleId).and(spec), pageable));
    }

    @GetMapping("/modules/{moduleId}/lessons/{lessonId}")
    public ResponseEntity<Object> findOne(@PathVariable(value="moduleId") UUID moduleId,
                                          @PathVariable(value="lessonId") UUID lessonId){
        return ResponseEntity.ok(lessonService.findLessonIntoModule(moduleId, lessonId));
    }

    @PostMapping("/modules/{moduleId}")
    public ResponseEntity<LessonDto> save(@PathVariable(value="moduleId") UUID moduleId,
                                          @RequestBody @Valid LessonDto lessonDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(lessonService.save(moduleId, lessonDto));
    }

    @PutMapping("/{lessonId}")
    public ResponseEntity<LessonDto> update(@PathVariable(value="lessonId") UUID lessonId,
                                            @RequestBody @Valid LessonDto lessonDto) {
        return ResponseEntity.ok(lessonService.update(lessonId, lessonDto));
    }

    @DeleteMapping("/{lessonId}/modules/{moduleId}")
    public ResponseEntity<Void> deleteLessonOfModule(@PathVariable(value="lessonId") UUID lessonId,
                                                     @PathVariable(value="moduleId") UUID moduleId) {
        lessonService.deleteLessonOfModule(lessonId, moduleId);
        return ResponseEntity.ok().build();
    }

}
