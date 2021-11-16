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
        Customer Kate = new Customer("Kate");
        Kate.setAddress("2320 Bargen Drive, BC", LocalDate.of(2020, 10, 1));
        String addressFor2021 = Kate.getAddress(LocalDate.of(2021, 1, 1));
        assertEquals("2320 Bargen Drive, BC", addressFor2021);
    }

    @Test
    public void customerPassDateInTheMiddleOfDates() {
        Customer Randy = new Customer("Randy");
        Randy.setAddress("2320 Bargen Drive, BC", LocalDate.of(2018, 10, 1));
        Randy.setAddress("1998 Queue street, ON", LocalDate.of(2010, 9, 11));
        String addressFor2014 = Randy.getAddress(LocalDate.of(2014, 12, 12));
        assertEquals("1998 Queue street, ON", addressFor2014);
    }
    @Test
    public void customerSetThreeAddressGetOne() {
        Customer kevin = new Customer("Kevin");
        kevin.setAddress("13 Claremont Street, Toronto", LocalDate.of(2011, 10, 31));
        kevin.setAddress("8635 Yale Street, Halifax", LocalDate.of(2014, 9, 10));
        kevin.setAddress("100 Bloor Street, Toronto", LocalDate.of(2019, 1, 1));
        kevin.getAddress(LocalDate.of(2013, 2, 12));
    }

    @Test(expected = NullPointerException.class)
    public void customerDonotSetAddress_ThrowException() {
        Customer Sylvia = new Customer("Sylvia");
        String addressFor2021 = Sylvia.getAddress(LocalDate.of(2021, 1, 1));

    }

    @Test
    public void customerSetFourAddressGetFour() {
        Customer Andrew = new Customer("Andrew");
        Andrew.setAddress("1610 Quinpool Street, Halifax", LocalDate.of(2011, 10, 31));
        Andrew.setAddress("8635 Yale Street, Halifax", LocalDate.of(2014, 9, 10));
        Andrew.setAddress("100 Bloor Street, Toronto", LocalDate.of(2019, 1, 1));
        Andrew.setAddress("8675 Shaunessy Street, Vancouver", LocalDate.of(2021, 10, 1));
        Andrew.getAddress(LocalDate.of(2013, 2, 12));
        Andrew.getAddress(LocalDate.of(2014, 12, 12));
        Andrew.getAddress(LocalDate.of(2015, 2, 12));
        Andrew.getAddress(LocalDate.of(2021, 11, 12));
    }
}
