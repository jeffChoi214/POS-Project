import java.math.BigDecimal;

/*
 * Created by Jeff Choi on 2/15/17.
 */
public class Product {
    private String name;
    private String category;
    private String description;
    private BigDecimal price;

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
