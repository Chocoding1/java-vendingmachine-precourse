package vendingmachine.model;

import static vendingmachine.model.ErrorCode.ERR_PAY_AMOUNT_INTEGER;
import static vendingmachine.model.ErrorCode.ERR_PAY_AMOUNT_NOT_NEGATIVE;

public class PayAmount {

    private int amount;

    public PayAmount(String input) {
        int amount = convertToInt(input);
        validateNotNegative(amount);
        this.amount = amount;
    }

    public int getAmount() {
        return amount;
    }

    public boolean isLessThan(int price) {
        return amount < price;
    }

    public void subtract(int price) {
        amount -= price;
    }

    private int convertToInt(String input) {
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(ERR_PAY_AMOUNT_INTEGER.getMessage());
        }
    }

    private void validateNotNegative(int amount) {
        if (amount < 0) {
            throw new IllegalArgumentException(ERR_PAY_AMOUNT_NOT_NEGATIVE.getMessage());
        }
    }
}
