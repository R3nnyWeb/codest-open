package ru.tinkoff.summer.taskmicroservice.crud.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import ru.tinkoff.summer.taskmicroservice.crud.model.composite_key.InputTypesId;

@Entity
@Table(name = "input_types")
@AllArgsConstructor
@NoArgsConstructor
@Data
@EqualsAndHashCode
public class InputTypes {

    @EmbeddedId
    private InputTypesId id;

    @ManyToOne
    @MapsId("taskId")
    @JoinColumn(name = "task_id", insertable = false, updatable = false)
    private TaskParams taskParams;

    @Column(name = "value")
    private String value;
}
