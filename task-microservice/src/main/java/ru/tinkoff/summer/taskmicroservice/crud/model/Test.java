package ru.tinkoff.summer.taskmicroservice.crud.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity(name = "tests")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tests")
public class Test {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long testId;
    @ManyToOne
    @JoinColumn(name = "task_id")
    @JsonBackReference
    private Task task;

    @ElementCollection
    @CollectionTable(name = "input_parameters", joinColumns = @JoinColumn(name = "test_id"))
    @OrderColumn(name = "input_parameters_order")
    @Column(name = "value")
    private List<String> inputParameters;
    private String outputParameters;
    @OneToMany(mappedBy = "failedTest")
    @JsonIgnore
    private List<SolutionsAttempts> solutionsAttempts;

}
