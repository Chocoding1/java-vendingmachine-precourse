package vendingmachine.controller;

import static vendingmachine.model.ExceptionHandler.*;

import java.util.EnumMap;
import vendingmachine.model.Coin;
import vendingmachine.model.ChangeProvider;
import vendingmachine.model.PayAmount;
import vendingmachine.model.Product;
import vendingmachine.model.ProductParser;
import vendingmachine.model.Products;
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
        VendingMoney vendingMoney = repeatUntilSuccess(this::getVendingMoney);
        EnumMap<Coin, Integer> availableCoins = randomCoinGenerator.generate(vendingMoney);
        outputView.printAvailableCoins(availableCoins);
        Products products = repeatUntilSuccess(this::getProducts);
        int minPrice = products.minPrice();
        PayAmount payAmount = repeatUntilSuccess(this::getPayAmount);

        while (true) {
            outputView.printRemainAmount(payAmount);

            Product findProduct = repeatUntilSuccess(() -> findProduct(products, payAmount));
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

    private Products getProducts() {
        String input = inputView.getProductsInfo();
        return productParser.parse(input);
    }

    private PayAmount getPayAmount() {
        String input = inputView.getPayAmount();
        return new PayAmount(input);
    }

    private Product findProduct(Products products, PayAmount payAmount) {
        String productName = inputView.getProductName();
        return products.find(productName, payAmount);
    }
}
