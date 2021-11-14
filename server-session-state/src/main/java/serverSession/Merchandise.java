package serverSession;

public class Merchandise {
    private String name;
    private String description;
    private int price;

    public Merchandise(String name, String description, int price) {
        this.name = name;
        this.description = description;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    @Override
    public int hashCode() {
        int result = 17;
        result = 31 * result + name.hashCode();
        result = 31 * result + description.hashCode();
        result = 31 * result + price;
        return result;
    }

    @Override
    public boolean equals(Object other) {
        if(other == this) return true;
        if(!(other instanceof Merchandise)) {
            return false;
        }
        Merchandise otherMer = (Merchandise)other;
        return otherMer.name.equals(name) &&
                 otherMer.description.equals(description) &&
                 otherMer.price == this.price;
    }
}
