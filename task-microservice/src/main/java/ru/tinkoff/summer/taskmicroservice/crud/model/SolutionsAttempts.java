package ru.tinkoff.summer.taskmicroservice.crud.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.builder.ToStringExclude;

import java.time.LocalDateTime;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "solutions_attempts")
public class SolutionsAttempts {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long attemptId;

    @ManyToOne
    @JoinColumn(name = "task_id")
    @JsonBackReference("task-attempts")
    @ToStringExclude
    private Task task;

    @ManyToOne
    @JoinColumn(name = "test_id")
    @ToStringExclude
    private Test failedTest;
    private String code;
    private String actualResult;
    private String errorMessage;
    @ManyToOne
    @JoinColumn(name = "language_id")
    @ToStringExclude
    private Language language;
    private String status;
    private Double executionTime;
    private Double solutionsVolume;
    private LocalDateTime creationDate;
}
