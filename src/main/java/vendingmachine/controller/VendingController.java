package vendingmachine.controller;

import java.util.EnumMap;
import java.util.List;
import vendingmachine.Coin;
import vendingmachine.model.ChangeProvider;
import vendingmachine.model.PayAmount;
import vendingmachine.model.Product;
import vendingmachine.model.ProductParser;
import vendingmachine.model.RandomCoinGenerator;
import vendingmachine.model.VendingMoney;
import vendingmachine.model.VendingProcessor;
import vendingmachine.view.InputView;
import vendingmachine.view.OutputView;

public class VendingController {

    private final InputView inputView;
    private final RandomCoinGenerator randomCoinGenerator;
    private final OutputView outputView;
    private final ProductParser productParser;
    private final VendingProcessor vendingProcessor;

    public VendingController(InputView inputView, RandomCoinGenerator randomCoinGenerator, OutputView outputView,
                             ProductParser productParser, VendingProcessor vendingProcessor) {
        this.inputView = inputView;
        this.randomCoinGenerator = randomCoinGenerator;
        this.outputView = outputView;
        this.productParser = productParser;
        this.vendingProcessor = vendingProcessor;
    }

    public void run() {
        VendingMoney vendingMoney = getVendingMoney();
        EnumMap<Coin, Integer> availableCoins = randomCoinGenerator.generate(vendingMoney);
        outputView.printAvailableCoins(availableCoins);
        String input = inputView.getProductsInfo();
        List<Product> products = productParser.parse(input);
        int minPrice = getMinPrice(products);
        input = inputView.getPayAmount();
        PayAmount payAmount = new PayAmount(input);

        while (true) {
            outputView.printRemainAmount(payAmount);

            String productName = inputView.getProductName();
            Product findProduct = findProduct(products, productName, payAmount);
            vendingProcessor.sell(findProduct, payAmount);

            if (payAmount.isLess(minPrice)) {
                break;
            }
        }

        ChangeProvider changeProvider = new ChangeProvider(availableCoins);
        outputView.printRemainAmount(payAmount);
        EnumMap<Coin, Integer> change = changeProvider.getChange(payAmount);
        outputView.printChange(change);
    }

    private VendingMoney getVendingMoney() {
        String initialInput = inputView.getVendingMoney();
        return new VendingMoney(initialInput);
    }

    private int getMinPrice(List<Product> products) {
        int minPrice = Integer.MAX_VALUE;
        for (Product product : products) {
            minPrice = Math.min(minPrice, product.getPrice());
        }
        return minPrice;
    }

    // 최적화 필수 메서드
    private Product findProduct(List<Product> products, String productName, PayAmount payAmount) {
        for (Product product : products) {
            if (product.isSameName(productName)) {
                product.checkPrice(payAmount);
                if (product.isSoldOut()) {
                    throw new IllegalArgumentException("[ERROR] 상품이 매진되었습니다.");
                }
                return product;
            }
        }
        throw new IllegalArgumentException("[ERROR] 존재하지 않는 상품입니다.");
    }
}
