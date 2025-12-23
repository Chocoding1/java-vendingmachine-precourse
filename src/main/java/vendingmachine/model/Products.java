package vendingmachine.model;

import static vendingmachine.model.ErrorCode.ERR_PRODUCTS_NOT_FOUND;

import java.util.ArrayList;
import java.util.List;

public class Products {

    private final List<Product> products;

    public Products(List<Product> products) {
        this.products = new ArrayList<>(products); // 불변 복사
    }

    public int getMinPrice() {
        if (isEmpty()) {
            return 0;
        }

        int minPrice = Integer.MAX_VALUE;
        for (Product product : products) {
            minPrice = product.lowerPrice(minPrice);
        }
        return minPrice;
    }

    public Product findByName(String productName) {
        for (Product product : products) {
            if (product.isSameName(productName)) {
                return product;
            }
        }
        throw new IllegalArgumentException(ERR_PRODUCTS_NOT_FOUND.getMessage());
    }

    private boolean isEmpty() {
        return products.isEmpty();
    }
}
