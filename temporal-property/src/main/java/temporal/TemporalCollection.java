package temporal;

import java.time.LocalDate;
import java.util.*;

public class TemporalCollection {
    private Map<LocalDate, CustomerVersion> customersCollection = new HashMap<>();

    public TemporalCollection(String name) {
        CustomerVersion customerVersion = new CustomerVersion(name, "No Address");
        customersCollection.put(LocalDate.now(), customerVersion);
    }

    public String getAddress(LocalDate date) {
        CustomerVersion customerVersion = getCustomerVersionAt(date);
        return customerVersion.getAddress();
    }

    public void putAddress(String address, LocalDate date) {
        CustomerVersion customerVersion = getCustomerVersionAt(date);
        if(customerVersion == null) {
            customerVersion = getCopy(getCustomerVersionAt(LocalDate.now()));
        }
        customerVersion.setAddress(address);
        customersCollection.put(date, customerVersion);
    }

    public String getName() {
        CustomerVersion latest = getCustomerVersionAt(LocalDate.now());
        return latest.getName();
    }

    // get customer version based on the date
    private CustomerVersion getCustomerVersionAt(LocalDate currentDate) {
        List<LocalDate> dateList = new ArrayList<>(customersCollection.keySet());
        // sort date
        Collections.sort(dateList);
        Collections.reverse(dateList);
        for(LocalDate date : dateList) {
            if(currentDate.compareTo(date) >= 0) {
                return customersCollection.get(date);
            }
        }
        return null;
    }
    private CustomerVersion getCopy(CustomerVersion customerVersionAt) {
        return new CustomerVersion(customerVersionAt.getName(), customerVersionAt.getAddress());
    }
}
