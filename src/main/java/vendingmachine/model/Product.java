package vendingmachine.model;

public class Product {

    private final String name;
    private final int price;
    private int remain;

    public Product(String name, String initialPrice, String initialRemain) {
        this.name = name;
        int price = convertToInt(initialPrice);
        validatePrice(price);
        this.price = price;
        this.remain = convertToInt(initialRemain);
    }

    private int convertToInt(String initialPrice) {
        try {
            return Integer.parseInt(initialPrice);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("[ERROR] 상품 가격과 수량은 정수 형태여야 합니다.");
        }
    }

    private void validatePrice(int price) {
        if (price < 100) {
            throw new IllegalArgumentException("[ERROR] 상품 가격은 100원 이상이어야 합니다.");
        }

        if (price % 10 != 0) {
            throw new IllegalArgumentException("[ERROR] 상품 가격은 10으로 나누어 떨어져야 합니다.");
        }
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    public boolean isSameName(String productName) {
        return name.equals(productName);
    }

    public void checkPrice(PayAmount payAmount) {
        if (payAmount.isLess(price)) {
            throw new IllegalArgumentException("[ERROR] 금액이 부족합니다.");
        }
    }

    public boolean isSoldOut() {
        return remain == 0;
    }

    public void sell(PayAmount payAmount) {
        remain--;
        payAmount.minus(price);
    }
}
