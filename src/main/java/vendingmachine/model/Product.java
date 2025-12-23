package vendingmachine.model;

import static vendingmachine.model.ErrorCode.*;

public class Product {

    private static final int MIN_PRICE = 100;
    private static final int PRICE_UNITS = 10;

    private final String name;
    private final int price;
    private int stock;

    public Product(String name, String initialPrice, String initialStock) {
        this.name = name;
        this.price = parsePrice(initialPrice);
        this.stock = parseStock(initialStock);
    }

    public int lowerPrice(int otherPrice) {
        return Math.min(price, otherPrice);
    }

    public boolean isSameName(String productName) {
        return name.equals(productName);
    }

    public void sell(PayAmount payAmount) {
        validateInStock();
        validateAffordable(payAmount);
        stock--;
        payAmount.subtract(price);
    }

    private int parsePrice(String initialPrice) {
        int price = convertToInt(initialPrice, ERR_PRODUCT_PRICE_INTEGER);
        validateAffordable(price);
        return price;
    }

    private void validateAffordable(int price) {
        if (price < MIN_PRICE) {
            throw new IllegalArgumentException(ERR_PRODUCT_PRICE_RANGE.getMessage());
        }

        if (price % PRICE_UNITS != 0) {
            throw new IllegalArgumentException(ERR_PRODUCT_PRICE_UNITS.getMessage());
        }
    }

    private int parseStock(String initialStock) {
        int stock = convertToInt(initialStock, ERR_PRODUCT_STOCK_INTEGER);
        validateStock(stock);
        return stock;
    }

    private void validateStock(int stock) {
        if (stock < 0) {
            throw new IllegalArgumentException(ERR_PRODUCT_STOCK_NOT_NEGATIVE.getMessage());
        }
    }

    private int convertToInt(String initialPrice, ErrorCode errorCode) {
        try {
            return Integer.parseInt(initialPrice);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(errorCode.getMessage());
        }
    }

    private void validateInStock() {
        if (stock == 0) {
            throw new IllegalArgumentException(ERR_OUT_OF_STOCK.getMessage());
        }
    }

    private void validateAffordable(PayAmount payAmount) {
        if (payAmount.isLessThan(price)) {
            throw new IllegalArgumentException(ERR_INSUFFICIENT_PAY_AMOUNT.getMessage());
        }
    }
}
