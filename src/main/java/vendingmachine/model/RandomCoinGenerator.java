package vendingmachine.model;

import camp.nextstep.edu.missionutils.Randoms;
import java.util.EnumMap;

public class RandomCoinGenerator {

    public EnumMap<Coin, Integer> generate(VendingMoney vendingMoney) {
        EnumMap<Coin, Integer> availableCoins = setAvailableCoins();

        while (vendingMoney.isAffordable()) {
            int amount = Randoms.pickNumberInList(Coin.getAmounts());
            if (vendingMoney.isGreaterThan(amount)) {
                Coin coin = Coin.of(amount);
                availableCoins.put(coin, availableCoins.get(coin) + 1);
                vendingMoney.subtract(amount);
            }
        }
        return availableCoins;
    }

    private static EnumMap<Coin, Integer> setAvailableCoins() {
        EnumMap<Coin, Integer> availableCoins = new EnumMap<>(Coin.class);
        for (Coin coin : Coin.values()) {
            availableCoins.put(coin, 0);
        }
        return availableCoins;
    }
}
