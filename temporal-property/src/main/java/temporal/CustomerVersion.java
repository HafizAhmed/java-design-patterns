package temporal;

public final class CustomerVersion {
    private String name;
    private String address;

    public CustomerVersion(String customerName, String customerAddress) {
        this.name = customerName;
        this.address = customerAddress;
    }

    public String getName() {
        return name;
    }

    public void setName(String customerName) {
        this.name = customerName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String customerAddress) {
        this.address = customerAddress;
    }
}
