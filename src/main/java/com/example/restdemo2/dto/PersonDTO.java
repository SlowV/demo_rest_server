package com.example.restdemo2.dto;

import com.example.restdemo2.domain.Person;
import com.example.restdemo2.util.ObjectUtil;
import lombok.Data;

import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.ResourceBundle;

@Data
public class PersonDTO {
    private Long id;
    private String name;
    private Integer age;
    private Double salary;
    private String salaryFormat;
    private String dob;
    private String statusStr;
    private Person.Status status;

    public PersonDTO(Person person) {
        ResourceBundle bundle = ResourceBundle.getBundle("i18n/language", new Locale("vi"));
        ObjectUtil.cloneObject(this, person);
        this.statusStr = bundle.getString(this.status.name());
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        this.dob = formatter.format(person.getDob());
        this.salaryFormat = convertUSDToVND(this.salary);
    }

    public String convertUSDToVND(double salary) {
        return NumberFormat.getCurrencyInstance(new Locale("vi", "VN")).format(salary * 23000);
    }
}
