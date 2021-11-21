package temporal;

import java.time.LocalDate;
import java.util.*;

/**
 * The class represents collection of customerVersions.
 * It is the core class for Temporal pattern.
 * The class uses a Map to store the date as key, and customerVersion as value.
 * The class includes methods for user to manipulate address data.
 */
public class TemporalCollection {
    /** Store all history data for customerVersion of date */
    private Map<LocalDate, CustomerVersion> customersCollection = new HashMap<>();

    /**
     * This is the constructor to create a temporalCollection instance.
     * It stores the customer information at current date.
     * @param name This is the customer name
     */
    public TemporalCollection(String name) {
        CustomerVersion customerVersion = new CustomerVersion(name, "No Address");
        customersCollection.put(LocalDate.now(), customerVersion);
    }

    /**
     * This method is used to get customer address at a given date.
     * @param date The date related to an address
     * @return customer address
     */
    public String getAddress(LocalDate date) {
        CustomerVersion customerVersion = getCustomerVersionAt(date);
        return customerVersion.getAddress();
    }

    /**
     * This method is used to update customer address.
     * If there is no customer data at given date, it will create a new
     * customerVersion, and store it in the collection.
     * @param address This is the address to store at the time
     * @param date This is the date when the address changes
     */
    public void putAddress(String address, LocalDate date) {
        CustomerVersion customerVersion = getCustomerVersionAt(date);
        if(customerVersion == null) {
            customerVersion = getCopy(getCustomerVersionAt(LocalDate.now()));
        }
        customerVersion.setAddress(address);
        customersCollection.put(date, customerVersion);
    }

    /**
     * This method is used to get customer name at current date.
     * @return customer name
     */
    public String getName() {
        CustomerVersion latest = getCustomerVersionAt(LocalDate.now());
        return latest.getName();
    }

    /**
     * This method is to get customer version based on the date.
     * @param currentDate The date which maps to a customerVersion
     * @return The customerVersion
     */
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

    /**
     * This method is used to get a deep copy of a customerVersion.
     * @param customerVersionAt The customerVersion need to be copied
     * @return The copied customerVersion
     */
    private CustomerVersion getCopy(CustomerVersion customerVersionAt) {
        return new CustomerVersion(customerVersionAt.getName(), customerVersionAt.getAddress());
    }
}
