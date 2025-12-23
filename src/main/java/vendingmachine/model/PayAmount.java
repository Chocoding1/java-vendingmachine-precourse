package vendingmachine.model;

public class PayAmount {

    private int amount;

    public PayAmount(String input) {
        int amount = convertToInt(input);
        checkPositive(amount);
        this.amount = amount;
    }

    public int getAmount() {
        return amount;
    }

    public boolean isLess(int price) {
        return amount < price;
    }

    public void subtract(int price) {
        amount -= price;
    }

    private int convertToInt(String input) {
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("[ERROR] 투입 금액은 정수 형태여야 합니다.");
        }
    }

    private void checkPositive(int amount) {
        if (amount < 0) {
            throw new IllegalArgumentException("[ERROR] 투입 금액은 0 이상이어야 합니다.");
        }
    }
}
