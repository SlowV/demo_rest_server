package com.example.restdemo2.date;

import com.example.restdemo2.test.MyDate;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class MyDateTest {
    @Test
    public void itShouldErrorFormatDateAndThrow() {
        Exception ex = Assertions.assertThrows(NumberFormatException.class, ()-> new MyDate("02~12~1998").addDays(12).build());
        Assertions.assertEquals("Sai định dạng dd/MM/yyyy hoặc dd-MM-yyyys", ex.getMessage());
    }

    @Test
    public void itShouldExceptionOutRangeOfDays() {
        Exception ex = Assertions.assertThrows(NumberFormatException.class, ()-> new MyDate("02/30/1998").addDays(12).build());
        Assertions.assertEquals("Ngày hoặc tháng vượt quá giới hạn!", ex.getMessage());
    }
}
