package com.jtt.javatachteam_hakaton.entity;

import com.jtt.javatachteam_hakaton.entity.enums.GradeEnum;
import com.jtt.javatachteam_hakaton.entity.enums.TaskLevelEnum;
import com.jtt.javatachteam_hakaton.entity.enums.TaskTypeEnum;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "tasks")
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(columnDefinition = "uuid", updatable = false, nullable = false)
    private UUID id;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "description", length = Integer.MAX_VALUE)
    private String description;

    @Column(name = "task_type", columnDefinition = "task_type_enum not null")
    @Enumerated(EnumType.STRING)
    private TaskTypeEnum taskType;

    @Column(name = "task_level", columnDefinition = "task_level_enum")
    @Enumerated(EnumType.STRING)
    private TaskLevelEnum taskLevel;

    @Column(name = "grade_level", columnDefinition = "grade_level_enum")
    @Enumerated(EnumType.STRING)
    private GradeEnum gradeLevel;

    @Column(name = "max_points", nullable = false)
    private Integer maxPoints;

    @ColumnDefault("1")
    @Column(name = "correct_answers_count")
    private Integer correctAnswersCount;


}