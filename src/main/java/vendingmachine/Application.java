package vendingmachine;

import vendingmachine.config.AppConfig;
import vendingmachine.controller.VendingController;

public class Application {
    public static void main(String[] args) {
        AppConfig appConfig = new AppConfig();
        VendingController vendingController = appConfig.vendingController();
        vendingController.run();
    }
}
