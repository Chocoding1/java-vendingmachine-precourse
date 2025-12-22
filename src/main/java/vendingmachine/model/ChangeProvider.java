package vendingmachine.model;

import java.util.EnumMap;
import vendingmachine.Coin;

public class ChangeProvider {

    private final EnumMap<Coin, Integer> availableCoins;

    public ChangeProvider(EnumMap<Coin, Integer> availableCoins) {
        this.availableCoins = availableCoins;
    }

    public EnumMap<Coin, Integer> getChange(PayAmount payAmount) {
        EnumMap<Coin, Integer> change = new EnumMap<>(Coin.class);

        for (Coin coin : Coin.values()) {
            if (availableCoins.get(coin) > 0) {
                while (!coin.isLarge(payAmount)) {
                    change.put(coin, change.getOrDefault(coin, 0) + 1);
                    payAmount.minus(coin.getAmount());
                }
            }
        }

        return change;
    }
}
