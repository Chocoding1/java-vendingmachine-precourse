package vendingmachine.model;

import java.util.List;

public class Products {

    private final List<Product> products;

    public Products(List<Product> products) {
        this.products = products;
    }

    public int minPrice() {
        int minPrice = Integer.MAX_VALUE;
        for (Product product : products) {
            minPrice = Math.min(minPrice, product.getPrice());
        }
        return minPrice;
    }

    public Product find(String productName, PayAmount payAmount) {
        for (Product product : products) {
            if (product.isSameName(productName)) {
                product.checkPrice(payAmount);
                product.checkSoldOut();
                return product;
            }
        }
        throw new IllegalArgumentException("[ERROR] 존재하지 않는 상품입니다.");
    }
}
