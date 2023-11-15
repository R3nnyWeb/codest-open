package ru.tinkoff.summer.taskmicroservice.crud.model.criteria;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.tinkoff.summer.taskmicroservice.crud.model.Level;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TaskSearchCriteria {
    private String title;
    private Level level;
    private boolean publish;
}
