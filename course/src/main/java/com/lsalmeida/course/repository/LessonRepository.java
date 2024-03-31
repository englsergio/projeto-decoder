package com.lsalmeida.course.repository;

import com.lsalmeida.course.model.LessonModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

public interface LessonRepository extends JpaRepository<LessonModel, UUID>, JpaSpecificationExecutor<LessonModel> {
    @Query(value = "select * from tb_lessons where module_id = :moduleId", nativeQuery = true)
    List<LessonModel> findAllLessonsIntoModule(@Param("moduleId") UUID moduleId);

    @Query(value = "select * from tb_lessons where module_module_id = :moduleId and lesson_id = :lessonId", nativeQuery = true)
    Optional<LessonModel> findLessonIntoModule(@Param("moduleId") UUID moduleId, @Param("lessonId") UUID lessonId);

    @Modifying
    @Query(value = "delete from tb_lessons where module_id in (:modulesIds)", nativeQuery = true)
    List<LessonModel> deleteAllLessonsIntoModules(@Param("modulesIds") Set<UUID> modulesIds);

    @Modifying
    @Query(value = "delete from tb_lessons where module_id = :moduleId and lesson_id = :lessonId", nativeQuery = true)
    List<LessonModel> deleteLessonOfModule(@Param("moduleId") UUID moduleId, @Param("lessonId") UUID lessonId);

}