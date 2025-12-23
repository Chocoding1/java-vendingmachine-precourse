package vendingmachine.model;

public enum ErrorCode {

    ERR_VENDING_MONEY_INTEGER("[ERROR] 자판기 보유 금액은 정수 형태로 입력해야 합니다."),
    ERR_VENDING_MONEY_NOT_NEGATIVE("[ERROR] 자판기 보유 금액은 0 이상이어야 합니다."),

    ERR_PRODUCT_INFO_FORMAT("[ERROR] 상품 정보 형식이 올바르지 않습니다."),
    ERR_PRODUCT_INFO_SIZE("[ERROR] 상품 정보는 콤마(,)로 구분해야 합니다."),

    ERR_PRODUCT_PRICE_INTEGER("[ERROR] 상품 가격은 정수 형태여야 합니다."),
    ERR_PRODUCT_PRICE_RANGE("[ERROR] 상품 가격은 100원 이상이어야 합니다."),
    ERR_PRODUCT_PRICE_UNITS("[ERROR] 상품 가격은 10으로 나누어 떨어져야 합니다."),

    ERR_PRODUCT_STOCK_INTEGER("[ERROR] 상품 수량은 정수 형태여야 합니다."),
    ERR_PRODUCT_STOCK_NOT_NEGATIVE("[ERROR] 상품 수량은 0 이상이어야 합니다."),

    ERR_OUT_OF_STOCK("[ERROR] 상품이 품절되었습니다."),
    ERR_INSUFFICIENT_PAY_AMOUNT("[ERROR] 구입 금액이 부족합니다."),

    ERR_PRODUCTS_NOT_FOUND("[ERROR] 존재하지 않는 상품입니다."),

    ERR_PAY_AMOUNT_INTEGER("[ERROR] 투입 금액은 정수 형태여야 합니다."),
    ERR_PAY_AMOUNT_NOT_NEGATIVE("[ERROR] 투입 금액은 0 이상이어야 합니다."),
    ;


    private final String message;

    ErrorCode(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
