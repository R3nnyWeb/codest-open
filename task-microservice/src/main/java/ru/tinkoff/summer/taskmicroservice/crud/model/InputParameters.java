package ru.tinkoff.summer.taskmicroservice.crud.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.tinkoff.summer.taskmicroservice.crud.model.composite_key.InputParametersId;

@Entity
@Table(name = "input_parameters")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class InputParameters {

    @EmbeddedId
    private InputParametersId id;

    @ManyToOne
    @MapsId("testId")
    @JoinColumn(name = "test_id", insertable = false, updatable = false)
    private Test test;
}
