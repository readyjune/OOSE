package model;

import java.util.*;

public interface ProductComponent {
    float getPrice();

    int getNumberInStock();

    Set<Product> getProducts(Set<Product> productSet);

    Set<ProductGroup> getProductGroupSet(Set<ProductGroup> productGroupSet);

    String getName();
}
