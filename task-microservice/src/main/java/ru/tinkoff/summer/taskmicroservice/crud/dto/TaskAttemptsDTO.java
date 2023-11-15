package ru.tinkoff.summer.taskmicroservice.crud.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Page;
import ru.tinkoff.summer.taskmicroservice.crud.model.Level;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TaskAttemptsDTO {
    private long taskID;
    private String title;
    private Level level;
    private Page<AttemptsPreviewDTO> attempts;
}
