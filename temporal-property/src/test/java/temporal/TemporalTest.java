package temporal;

import org.junit.Test;

import java.time.LocalDate;

import static org.junit.Assert.assertEquals;

public class TemporalTest {

    @Test(expected = NullPointerException.class)
    public void customerAddressDateNotExist_ThrowException() {
        Customer james = new Customer("James");
        james.setAddress("2320 Bargen Drive, BC", LocalDate.of(2020, 10, 1));
        james.getAddress(LocalDate.of(2009, 12, 12));
    }

    @Test
    public void customerAddressDateEqualsPassedDate() {
        Customer james = new Customer("James");
        james.setAddress("2320 Bargen Drive, BC", LocalDate.of(2020, 10, 1));
        String addressFor2021 = james.getAddress(LocalDate.of(2021, 1, 1));
        assertEquals("2320 Bargen Drive, BC", addressFor2021);
    }

    @Test
    public void customerPassDateInTheMiddleOfDates() {
        Customer james = new Customer("James");
        james.setAddress("2320 Bargen Drive, BC", LocalDate.of(2018, 10, 1));
        james.setAddress("1998 Queue street, ON", LocalDate.of(2010, 9, 11));
        String addressFor2014 = james.getAddress(LocalDate.of(2014, 12, 12));
        assertEquals("1998 Queue street, ON", addressFor2014);
    }
}
