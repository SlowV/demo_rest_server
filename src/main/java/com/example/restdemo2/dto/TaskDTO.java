package com.example.restdemo2.dto;
import com.example.restdemo2.domain.Person;
import lombok.Data;

@Data
public class TaskDTO {
    private String title;
    private String description;
    private String image;
    private Long personId;
    private Person Person;
}
