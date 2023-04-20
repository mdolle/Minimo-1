package edu.upc.dsa.models;
import java.util.*;

public class Product {
    private String id;
    private String description;
    private int price;
    public static Map<String, Product> Shop = new HashMap<>();

    public Product(String nid, String ndescription, int nprice) throws  Exception{
        if (Shop.containsKey(nid)) {
            throw new Exception();
        } else {
            this.id = nid;
            this.description = ndescription;
            this.price = nprice;
            Shop.put(this.id, this);
        }
        this.id = nid;
        this.description = ndescription;
        this.price = nprice;
    }

    public String getId() {
        return this.id;
    }

    public int getPrice() {
        return this.price;
    }
}
