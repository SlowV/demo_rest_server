package com.example.restdemo2.domain;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@Entity
public class Person implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message = "{Person.errors.notblank}")
    private String name;
    @Min(value = 13, message = "{Person.errors.age.min}")
    private Integer age;
    private Double salary;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date dob;

    @Enumerated(value = EnumType.STRING)
    private Status status;

    @OneToMany(mappedBy = "person", fetch = FetchType.LAZY)
    private Set<Task> tasks = new HashSet<>();

    public enum Status {
        ACTIVE,
        INACTIVE
    }
}
