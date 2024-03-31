package com.lsalmeida.course.mapper;

import com.lsalmeida.course.model.CourseModel;
import com.lsalmeida.course.model.LessonModel;
import com.lsalmeida.course.model.ModuleModel;
import com.lsalmeida.course.model.dto.CourseDto;
import com.lsalmeida.course.model.dto.LessonDto;
import com.lsalmeida.course.model.dto.ModuleDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;

@Mapper(componentModel = "spring",
        imports = {LocalDateTime.class, ZoneId.class},
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface CourseMapper {

    @Mapping(target = "creationDate", expression = "java(LocalDateTime.now(ZoneId.of(\"UTC\")))")
    @Mapping(target = "lastUpdateDate", expression = "java(LocalDateTime.now(ZoneId.of(\"UTC\")))")
    CourseModel fromDto(CourseDto dto);

    CourseDto toDto(CourseModel saved);

    @Mapping(target = "lastUpdateDate", expression = "java(LocalDateTime.now(ZoneId.of(\"UTC\")))")
    CourseModel updateCourse(@MappingTarget CourseModel course, CourseDto dto);

    ModuleDto toDto(ModuleModel moduleModel);

    List<ModuleDto> toDto(List<ModuleModel> modulesList);

    @Mapping(target = "lastUpdateDate", expression = "java(LocalDateTime.now(ZoneId.of(\"UTC\")))")
    @Mapping(target = "creationDate", expression = "java(LocalDateTime.now(ZoneId.of(\"UTC\")))")
    @Mapping(target = "course", source = "courseModel")
    @Mapping(target = "description", source = "dto.description")
    ModuleModel fromDto(CourseModel courseModel, ModuleDto dto);

    @Mapping(target = "lastUpdateDate", expression = "java(LocalDateTime.now(ZoneId.of(\"UTC\")))")
    ModuleModel updateModule(@MappingTarget ModuleModel module, ModuleDto dto);

    @Mapping(target = "lastUpdateDate", expression = "java(LocalDateTime.now(ZoneId.of(\"UTC\")))")
    @Mapping(target = "creationDate", expression = "java(LocalDateTime.now(ZoneId.of(\"UTC\")))")
    @Mapping(target = "module", source = "moduleModel")
    @Mapping(target = "description", source = "lessonDto.description")
    @Mapping(target = "title", source = "lessonDto.title")
    LessonModel fromDto(ModuleModel moduleModel, LessonDto lessonDto);

    LessonDto toDto(LessonModel saved);

    @Mapping(target = "lastUpdateDate", expression = "java(LocalDateTime.now(ZoneId.of(\"UTC\")))")
    LessonModel updateLesson(@MappingTarget LessonModel lessonModel, LessonDto lessonDto);

    default LocalDateTime localDataTimeNow() {
        return LocalDateTime.now(ZoneId.of("UTC"));
    }

}
