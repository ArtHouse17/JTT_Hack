package com.jtt.javatachteam_hakaton.entity;

import com.jtt.javatachteam_hakaton.entity.enums.StatusEnum;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.hibernate.type.SqlTypes;
import java.time.Instant;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "attempts")
public class Attempt {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(columnDefinition = "uuid", updatable = false, nullable = false)
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "task_id")
    private Task task;

    @JdbcTypeCode(SqlTypes.NAMED_ENUM)
    @ColumnDefault("'IN_PROGRESS'")
    @Column(name = "status", columnDefinition = "attempt_status_enum not null")
    @Enumerated(EnumType.STRING)
    private StatusEnum status;

    @ColumnDefault("0")
    @Column(name = "earned_points")
    private Integer earnedPoints;

    @Column(name = "written_text", length = Integer.MAX_VALUE)
    private String writtenText;

    @ColumnDefault("CURRENT_TIMESTAMP")
    @Column(name = "started_at")
    private Instant startedAt;

    @Column(name = "completed_at")
    private Instant completedAt;


}
