package temporal;

import java.time.LocalDate;

public class Customer {
    private TemporalCollection temporalCollection;

    public Customer(String name) {
        temporalCollection = new TemporalCollection(name);
    }

    public String getName() {
        return temporalCollection.getName();
    }

    public String getAddress(LocalDate date) {
        return temporalCollection.getAddress(date);
    }

    public void setAddress(String address, LocalDate date) {
        temporalCollection.putAddress(address, date);
    }
}
