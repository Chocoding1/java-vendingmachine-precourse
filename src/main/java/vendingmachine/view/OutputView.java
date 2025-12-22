package vendingmachine.view;

import java.util.EnumMap;
import vendingmachine.Coin;
import vendingmachine.model.PayAmount;

public class OutputView {

    private static final String AVAILABLE_COINS_PRINT_FORMAT = "자판기가 보유한 동전";
    private static final String REMAIN_AMOUNT_PRINT_FORMAT = "투입 금액: ";
    private static final String CHANGE_PRINT_FORMAT = "잔돈";

    public void printAvailableCoins(EnumMap<Coin, Integer> availableCoins) {
        System.out.println(AVAILABLE_COINS_PRINT_FORMAT);
        for (Coin coin : availableCoins.keySet()) {
            System.out.printf(coin.getPrintFormat(), availableCoins.get(coin));
        }
    }

    public void printRemainAmount(PayAmount payAmount) {
        System.out.println(REMAIN_AMOUNT_PRINT_FORMAT + payAmount.getAmount());
    }

    public void printChange(EnumMap<Coin, Integer> change) {
        System.out.println(CHANGE_PRINT_FORMAT);
        for (Coin coin : Coin.values()) {
            if (change.containsKey(coin)) {
                System.out.printf(coin.getPrintFormat(), change.get(coin));
            }
        }
    }
}
