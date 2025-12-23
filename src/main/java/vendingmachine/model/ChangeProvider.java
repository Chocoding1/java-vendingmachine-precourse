package vendingmachine.model;

import java.util.EnumMap;

public class ChangeProvider {

    private final EnumMap<Coin, Integer> availableCoins;

    public ChangeProvider(EnumMap<Coin, Integer> availableCoins) {
        this.availableCoins = availableCoins;
    }

    public EnumMap<Coin, Integer> getChange(PayAmount payAmount) {
        EnumMap<Coin, Integer> change = new EnumMap<>(Coin.class);

        for (Coin coin : Coin.values()) {
            while (availableCoins.get(coin) > 0 && coin.isLessThan(payAmount)) {
                change.put(coin, change.getOrDefault(coin, 0) + 1);
                availableCoins.put(coin, availableCoins.get(coin) - 1);
                payAmount.subtract(coin.getAmount());
            }
        }

        return change;
    }
}
