package com.example.restdemo2.endpoint;

import com.example.restdemo2.domain.rest.RESTResponse;
import com.example.restdemo2.dto.TaskDTO;
import com.example.restdemo2.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(value = "/api_v1/task")
public class TaskEndpoint {
    @Autowired
    TaskService taskService;

    @GetMapping
    public ResponseEntity<Object> tasks(@RequestParam("personId") Long personId) {
        return taskService.getAll(personId);
    }

    @PostMapping("/store")
    public ResponseEntity<Object> store(@Valid @RequestBody TaskDTO taskDTO, BindingResult result) {
        if (result.hasErrors()) {
            Map<String, String> errors = new HashMap<>();
            for (FieldError fieldError : result.getFieldErrors()) {
                errors.put(fieldError.getField(), fieldError.getDefaultMessage());
            }
            return ResponseEntity.badRequest().body(
                    new RESTResponse.Error()
                            .addErrors(errors)
                            .setMessage("Có lỗi xảy ra!")
                            .setStatus(HttpStatus.BAD_REQUEST.value())
                            .build()
            );
        }
        return taskService.save(taskDTO);
    }
}
