package vendingmachine.model;

public class VendingMoney {

    private final int money;

    public VendingMoney(String input) {
        this.money = convertToInt(input);
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
}
