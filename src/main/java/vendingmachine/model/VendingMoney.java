package vendingmachine.model;

import static vendingmachine.model.ErrorCode.*;

public class VendingMoney {

    private int money;

    public VendingMoney(String input) {
        int money = convertToInt(input);
        validateNotNegative(money);
        this.money = money;
    }

    public boolean isAffordable() {
        return money > 0;
    }

    public boolean isGreaterThan(int amount) {
        return money >= amount;
    }

    public void subtract(int amount) {
        money -= amount;
    }

    private int convertToInt(String input) {
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(ERR_VENDING_MONEY_INTEGER.getMessage());
        }
    }

    private void validateNotNegative(int money) {
        if (money < 0) {
            throw new IllegalArgumentException(ERR_VENDING_MONEY_NOT_NEGATIVE.getMessage());
        }
    }
}
