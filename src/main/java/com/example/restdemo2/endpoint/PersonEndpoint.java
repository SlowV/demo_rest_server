package com.example.restdemo2.endpoint;

import com.example.restdemo2.domain.Person;
import com.example.restdemo2.dto.PersonDTO;
import com.example.restdemo2.endpoint.rest.RESTPagination;
import com.example.restdemo2.endpoint.rest.RESTResponse;
import com.example.restdemo2.service.PersonService;
import com.example.restdemo2.specification.PersonSpecification;
import com.example.restdemo2.specification.SearchCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/api_v1/person")
@CrossOrigin(value = "*", allowedHeaders = "*")
public class PersonEndpoint {

    @Autowired
    PersonService personService;

    @GetMapping
    public ResponseEntity<Object> persons(
            @RequestParam(value = "keyword", required = false) String keyword,
            @RequestParam(defaultValue = "1", required = false) int page,
            @RequestParam(defaultValue = "10", required = false) int limit,
            @RequestParam(value = "status", required = false) String status
    ) {
        Specification<Person> specification = Specification.where(null);

        if (keyword != null) {
            specification = specification.and(
                    new PersonSpecification(new SearchCriteria("name", ":", keyword))
            );
        }

        if(status!= null) {
            specification = specification.and(
                    new PersonSpecification(new SearchCriteria("status", ":=", Person.Status.valueOf(status)))
            );
        }

        Page<Person> pageP = personService.getAll(specification, page, limit);

        return new ResponseEntity<>(
                new RESTResponse.Success()
                        .setStatus(HttpStatus.OK.value())
                        .setMessage("Lấy danh sách thành công!")
                        .setDatas(pageP.getContent().stream().map(PersonDTO::new).collect(Collectors.toList()))
                        .setPagination(new RESTPagination(page, limit, pageP.getTotalPages(), pageP.getTotalElements())).build()
                , HttpStatus.OK
        );
    }

    @GetMapping("/{id}")
    public Person getOne(@PathVariable("id") Long id) {
        return personService.getById(id);
    }

}
