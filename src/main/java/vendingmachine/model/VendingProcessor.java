package vendingmachine.model;

public class VendingProcessor {


    public void sell(Product findProduct, PayAmount payAmount) {
        findProduct.sell(payAmount);
    }
}
