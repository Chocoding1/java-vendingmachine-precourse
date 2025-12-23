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
import vendingmachine.view.InputView;
import vendingmachine.view.OutputView;

public class VendingController {

    private final InputView inputView;
    private final RandomCoinGenerator randomCoinGenerator;
    private final OutputView outputView;
    private final ProductParser productParser;

    public VendingController(InputView inputView, RandomCoinGenerator randomCoinGenerator, OutputView outputView,
                             ProductParser productParser) {
        this.inputView = inputView;
        this.randomCoinGenerator = randomCoinGenerator;
        this.outputView = outputView;
        this.productParser = productParser;
    }

    public void run() {
        VendingMoney vendingMoney = repeatSupplierUntilSuccess(this::getVendingMoney);
        EnumMap<Coin, Integer> availableCoins = randomCoinGenerator.generate(vendingMoney);
        outputView.printAvailableCoins(availableCoins);
        Products products = repeatSupplierUntilSuccess(this::getProducts);
        int minPrice = products.getMinPrice();
        PayAmount payAmount = repeatSupplierUntilSuccess(this::getPayAmount);

        while (true) {
            outputView.printRemainAmount(payAmount);
            repeatRunnableUntilSuccess(() -> vend(products, payAmount));
            if (payAmount.isLessThan(minPrice)) {
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

    private void vend(Products products, PayAmount payAmount) {
        Product findProduct = findProduct(products);
        findProduct.sell(payAmount);
    }

    private Product findProduct(Products products) {
        String productName = inputView.getProductName();
        return products.findByName(productName);
    }
}
