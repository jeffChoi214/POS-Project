import java.math.BigDecimal;

/*
 * POS Project 2/15/2017
 * Jeff, Wei, Robert
 */
public class Product {
    private String name;
    private String category;
    private String description;
    private BigDecimal price;

    // TODO: maybe add quantity here

    public Product(String category, String description, String name, String price) {
        this.category = category;
        this.description = description;
        this.name = name;
        String holder = price.replaceAll(",","");
        this.price = new BigDecimal(holder);
    }

    public String getName() {
        return this.name;
    }

    public String getCategory() {
        return this.category;
    }

    public String getDescription() {
        return this.description;
    }

    public BigDecimal getPrice() {
        return this.price;
    }










}
