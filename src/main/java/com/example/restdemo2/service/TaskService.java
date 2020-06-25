package com.example.restdemo2.service;

import com.example.restdemo2.domain.Person;
import com.example.restdemo2.domain.Task;
import com.example.restdemo2.domain.rest.RESTResponse;
import com.example.restdemo2.dto.TaskDTO;
import com.example.restdemo2.repository.PersonRepository;
import com.example.restdemo2.repository.TaskRepository;
import com.example.restdemo2.specification.TaskSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TaskService {
    @Autowired
    TaskRepository taskRepository;

    @Autowired
    PersonRepository personRepository;

    public ResponseEntity<Object> getAll(Long personId) {
        TaskSpecification specification = TaskSpecification.spec();
        Optional.ofNullable(personId).ifPresent(specification::withPerson);
        List<Task> list = taskRepository.findAll(specification.build());
        return new ResponseEntity<>(
                RESTResponse.Builder()
                        .setStatus(HttpStatus.OK.value())
                        .setMessage("Lấy danh sách công việc thành công!")
                        .setDatas(list.stream().map(TaskDTO::new).collect(Collectors.toList()))
                        .build(),
                HttpStatus.OK
        );
    }

    public ResponseEntity<Object> save(TaskDTO taskDTO) {
        Person person = personRepository.getOne(taskDTO.getPersonId());
        Task task = taskDTO.toEntity();
        task.setPerson(person);
        return new ResponseEntity<>(
                RESTResponse.Builder()
                        .setStatus(HttpStatus.CREATED.value())
                        .setMessage("Lưu công việc thành công!")
                        .setData(new TaskDTO(taskRepository.save(task)))
                        .build(),
                HttpStatus.CREATED
        );
    }
}
