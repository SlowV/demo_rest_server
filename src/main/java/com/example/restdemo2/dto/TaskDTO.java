package com.example.restdemo2.dto;
import com.example.restdemo2.domain.Task;
import lombok.Data;
import org.modelmapper.ModelMapper;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class TaskDTO {
    private static ModelMapper modelMapper = new ModelMapper();

    private Long id;
    @NotNull(message = "{Task.errors.title.notNull}")
    @NotBlank(message = "{Task.errors.title.notBlank}")
    private String title;
    private String description;
    @NotNull(message = "{Task.errors.image.notNull}")
    private String image;
    private Long personId;

    public TaskDTO() {
    }

    public TaskDTO(Task task) {
        modelMapper.map(task, this);
    }

    public Task toEntity() {
        Task task = new Task();
        modelMapper.map(this, task);
        return task;
    }
}
