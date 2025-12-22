package vendingmachine.model;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public enum Coin {
    COIN_500(500, "500원 - %d개\n"),
    COIN_100(100, "100원 - %d개\n"),
    COIN_50(50, "50원 - %d개\n"),
    COIN_10(10, "10원 - %d개\n");

    private final int amount;
    private final String printFormat;

    Coin(final int amount, final String printFormat) {
        this.amount = amount;
        this.printFormat = printFormat;
    }

    public static List<Integer> getAmounts() {
        return Arrays.stream(values())
                .map(Coin::getAmount)
                .collect(Collectors.toList());
    }

    public static Coin of(int amount) {
        for (Coin coin : values()) {
            if (coin.amount == amount) {
                return coin;
            }
        }
        return null;
    }

    public String getPrintFormat() {
        return printFormat;
    }

    public boolean isLess(PayAmount payAmount) {
        return !payAmount.isLess(amount);
    }

    public int getAmount() {
        return amount;
    }
}
