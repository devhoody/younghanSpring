package hello.itemservice.domain;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class Item {
    private Long id;
    private String name;
    private Integer price;
    private Integer quentity;

    public Item() {
    }

    public Item(String name, Integer price, Integer quentity) {
        this.name = name;
        this.price = price;
        this.quentity = quentity;
    }
}
