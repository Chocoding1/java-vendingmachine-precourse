package vendingmachine.model;

public class VendingMoney {

    private final int money;

    public VendingMoney(String input) {
        int money = convertToInt(input);
        checkPositive(money);
        this.money = money;
    }

    public int getMoney() {
        return money;
    }

    private int convertToInt(String input) {
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("[ERROR] 자판기 보유 금액은 정수 형태로 입력해야 합니다.");
        }
    }

    private void checkPositive(int money) {
        if (money < 0) {
            throw new IllegalArgumentException("[ERROR] 자판기 보유 금액은 0 이상이어야 합니다.");
        }
    }
}
