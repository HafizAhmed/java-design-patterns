package serversession;

public final class Merchandise {
    private String name;
    private String description;
    private int price;
    private static final int HASH_CODE_BASE = 17;
    private static final int HASH_CODE_INCREMENT = 31;

    public Merchandise(String merchandiseName, String merchandiseDescription, int merchandisePrice) {
        this.name = merchandiseName;
        this.description = merchandiseDescription;
        this.price = merchandisePrice;
    }

    public String getName() {
        return name;
    }

    public void setName(String merchandiseName) {
        this.name = merchandiseName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String merchandiseDescription) {
        this.description = merchandiseDescription;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int merchandisePrice) {
        this.price = merchandisePrice;
    }

    @Override
    public int hashCode() {
        int result = HASH_CODE_BASE;
        result = HASH_CODE_INCREMENT * result + name.hashCode();
        result = HASH_CODE_INCREMENT * result + description.hashCode();
        result = HASH_CODE_INCREMENT * result + price;
        return result;
    }

    @Override
    public boolean equals(Object other) {
        if(other == this) {
            return true;
        }
        if(!(other instanceof Merchandise)) {
            return false;
        }
        Merchandise otherMer = (Merchandise)other;
        return otherMer.name.equals(name) &&
                 otherMer.description.equals(description) &&
                 otherMer.price == this.price;
    }
}
