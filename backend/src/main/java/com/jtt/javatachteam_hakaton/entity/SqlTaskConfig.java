package com.jtt.javatachteam_hakaton.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "sql_task_configs")
public class SqlTaskConfig {
    @Id
    @Column(name = "task_id", columnDefinition = "uuid", nullable = false)
    private UUID taskId;

    @Column(name = "starter_sql", nullable = false, length = Integer.MAX_VALUE)
    private String starterSql;

    @Column(name = "expected_sql", nullable = false, length = Integer.MAX_VALUE)
    private String expectedSql;
}
