package com.example.restdemo2.test;

public class MyDate {
    private int[] daysOfMonth = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
    private int day;
    private int month;
    private int year;

    public MyDate(String dateString) {
        if (!dateString.matches("([0-9]{2})[/-]([0-9]{2})[/-]([0-9]{4})")) {
            throw new NumberFormatException("Sai định dạng dd/MM/yyyy hoặc dd-MM-yyyys");
        }
        String[] dateArr = dateString.split("[/-]");
        this.day = Integer.parseInt(dateArr[0]);
        this.month = Integer.parseInt(dateArr[1]);
        this.year = Integer.parseInt(dateArr[2]);
        if (this.day > 31 || this.month > 12 ) {
            throw new NumberFormatException("Ngày hoặc tháng vượt quá giới hạn!");
        }
    }

    public MyDate addDays(int days) {
        if (isLeapYear()) {
            daysOfMonth[1] = 29;
        }
        while ((this.day + days) > daysOfMonth[this.month - 1]) {
            days -= daysOfMonth[this.month - 1];
            this.month++;
            if (this.month > 12) {
                this.month = 1;
                this.year++;
            }
        }
        this.day += days;
        return this;
    }

    private boolean isLeapYear() {
        return ((year % 4 == 0) && (year % 100 != 0) || (year % 400 == 0));
    }

    public String build() {
        return String.format("%02d/%02d/%04d", this.day, this.month, this.year);
    }
}
