package vendingmachine.config;

import vendingmachine.controller.VendingController;
import vendingmachine.model.ProductParser;
import vendingmachine.model.RandomCoinGenerator;
import vendingmachine.view.InputView;
import vendingmachine.view.OutputView;

public class AppConfig {

    private VendingController vendingController;
    private InputView inputView;
    private RandomCoinGenerator randomCoinGenerator;
    private OutputView outputView;
    private ProductParser productParser;

    public VendingController vendingController() {
        if (vendingController == null) {
            vendingController = new VendingController(inputView(), randomCoinGenerator(), outputView(),
                    productParser());
        }
        return vendingController;
    }

    public InputView inputView() {
        if (inputView == null) {
            inputView = new InputView();
        }
        return inputView;
    }

    private RandomCoinGenerator randomCoinGenerator() {
        if (randomCoinGenerator == null) {
            randomCoinGenerator = new RandomCoinGenerator();
        }
        return randomCoinGenerator;
    }

    private OutputView outputView() {
        if (outputView == null) {
            outputView = new OutputView();
        }
        return outputView;
    }

    private ProductParser productParser() {
        if (productParser == null) {
            productParser = new ProductParser();
        }
        return productParser;
    }
}
