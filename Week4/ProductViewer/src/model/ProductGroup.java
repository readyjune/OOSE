package model;

import java.util.*;

/**
 * Represents a product group. Product groups are compared on the basis of
 * their names.
 */
public class ProductGroup implements ProductComponent, Comparable<ProductGroup> {
    private String name;
    private Set<ProductComponent> products;

    public ProductGroup(String name) {
        this.name = name;
        this.products = new TreeSet<>();
    }

    public String getName() {
        return name;
    }

    @Override
    public float getPrice() {
        return 0;
    }

    @Override
    public int getNumberInStock() {
        return 0;
    }

    @Override
    public Set<Product> getProducts(Set<Product> productSet) {
        for (ProductComponent p : products) {
            p.getProducts(productSet);
        }
        return productSet;
    }

    @Override
    public Set<ProductGroup> getProductGroupSet(Set<ProductGroup> productGroupSet) {
        productGroupSet.add(this);
        for (ProductComponent p : products) {
            p.getProductGroupSet(productGroupSet);
        }
        return productGroupSet;
    }

    public boolean hasProduct(ProductComponent p) {
        return products.contains(p);
    }

    public void addProduct(ProductComponent p) {
        products.add(p);
    }

    @Override
    public boolean equals(Object obj) {
        boolean eq = false;
        if (obj instanceof ProductGroup) {
            eq = name.equals(((ProductGroup) obj).name);
        }
        return eq;
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }

    @Override
    public int compareTo(ProductGroup group) {
        return name.compareTo(group.name);
    }
}
