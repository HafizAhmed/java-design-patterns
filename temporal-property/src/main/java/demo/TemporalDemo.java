package demo;

import temporal.Customer;

import java.time.LocalDate;

public class TemporalDemo {
    public static void main(String[] args) {
        Customer james = new Customer("James");
        james.setAddress("3920 Bargen Drive, BC", LocalDate.of(2020, 10, 1));
        james.setAddress("1998 Queue street, ON", LocalDate.of(2010, 9, 11));

        System.out.println(james.getAddress(LocalDate.of(2018, 8, 9)));
    }
}
