package vendingmachine.view;

import static camp.nextstep.edu.missionutils.Console.*;

import camp.nextstep.edu.missionutils.Console;

public class InputView {

    private static final String INPUT_VENDING_MONEY = "자판기가 보유하고 있는 금액을 입력해 주세요.";
    private static final String INPUT_PRODUCTS_INFO = "상품명과 가격, 수량을 입력해주세요.";
    private static final String INPUT_PAY_AMOUNT = "투입 금액을 입력해주세요.";
    private static final String INPUT_PRODUCT_NAME = "구매할 상품명을 입력해주세요.";

    public String getVendingMoney() {
        System.out.println(INPUT_VENDING_MONEY);
        return readLine();
    }

    public String getProductsInfo() {
        System.out.println(INPUT_PRODUCTS_INFO);
        return readLine();
    }

    public String getPayAmount() {
        System.out.println(INPUT_PAY_AMOUNT);
        return readLine();
    }

    public String getProductName() {
        System.out.println(INPUT_PRODUCT_NAME);
        return readLine();
    }
}
