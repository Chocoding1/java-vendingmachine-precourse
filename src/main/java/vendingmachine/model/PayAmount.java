package vendingmachine.model;

public class PayAmount {

    private int amount;

    public PayAmount(String input) {
        this.amount = convertToInt(input);
    }

    public int getAmount() {
        return amount;
    }

    private int convertToInt(String input) {
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("[ERROR] 투입 금액은 정수 형태여야 합니다.");
        }
    }

    public boolean isLess(int price) {
        return amount < price;
    }

    public void minus(int price) {
        amount -= price;
    }
}
