package vendingmachine.model;

import camp.nextstep.edu.missionutils.Randoms;
import java.util.EnumMap;
import vendingmachine.Coin;

public class RandomCoinGenerator {

    public EnumMap<Coin, Integer> generate(VendingMoney vendingMoney) {
        EnumMap<Coin, Integer> availableCoins = setAvailableCoins();
        int money = vendingMoney.getMoney();

        while (money != 0) {
            int amount = Randoms.pickNumberInList(Coin.getAmounts());
            if (amount <= money) {
                Coin coin = Coin.of(amount);
                availableCoins.put(coin, availableCoins.get(coin) + 1);
                money -= amount;
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
