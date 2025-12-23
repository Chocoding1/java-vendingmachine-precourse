package vendingmachine.model;

public enum ErrorMessage {

    ERR_VENDING_MONEY_INTEGER("[ERROR] 자판기 보유 금액은 정수 형태로 입력해야 합니다."),
    ERR_VENDING_MONEY_NOT_NEGATIVE("[ERROR] 자판기 보유 금액은 0 이상이어야 합니다."),

    ERR_PRODUCT_INFO_FORMAT("[ERROR] 상품 정보 형식이 올바르지 않습니다."),
    ERR_PRODUCT_INFO_SIZE("[ERROR] 상품 정보는 콤마(,)로 구분해야 합니다."),
    ;


    private final String message;

    ErrorMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
