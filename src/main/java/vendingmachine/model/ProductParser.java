package vendingmachine.model;

import static vendingmachine.model.ErrorMessage.*;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class ProductParser {

    private static final String SEMI_COLON = ";";
    private static final String COMMA = ",";
    private static final String LEFT_BRACKET = "[";
    private static final String RIGHT_BRACKET = "]";
    private static final int PRODUCT_INFO_SIZE = 3;
    private static final int PRODUCT_NAME_IDX = 0;
    private static final int PRODUCT_PRICE_IDX = 1;
    private static final int PRODUCT_AMOUNT_IDX = 2;

    public Products parse(String input) {
        List<String> productTokens = splitBySemicolon(input);
        return new Products(parseProducts(productTokens));
    }

    private List<String> splitBySemicolon(String input) {
        return Arrays.asList(input.split(SEMI_COLON));
    }

    private List<Product> parseProducts(List<String> tokens) {
        return tokens.stream()
                .map(this::parseProduct)
                .collect(Collectors.toList());
    }

    private Product parseProduct(String token) {
        validateBracket(token);
        String content = removeBracket(token);
        List<String> productInfo = splitByComma(content);
        validateProductInfo(productInfo);
        return createProduct(productInfo);
    }

    private void validateBracket(String token) {
        if (!token.startsWith(LEFT_BRACKET) || !token.endsWith(RIGHT_BRACKET)) {
            throw new IllegalArgumentException(ERR_PRODUCT_INFO_FORMAT.getMessage());
        }
    }

    private String removeBracket(String token) {
        return token.substring(1, token.length() - 1);
    }

    private List<String> splitByComma(String content) {
        return Arrays.asList(content.split(COMMA));
    }

    private void validateProductInfo(List<String> productInfo) {
        if (productInfo.size() != PRODUCT_INFO_SIZE) {
            throw new IllegalArgumentException(ERR_PRODUCT_INFO_SIZE.getMessage());
        }
    }

    private Product createProduct(List<String> productInfo) {
        String name = productInfo.get(PRODUCT_NAME_IDX);
        String price = productInfo.get(PRODUCT_PRICE_IDX);
        String amount = productInfo.get(PRODUCT_AMOUNT_IDX);
        return new Product(name, price, amount);
    }
}
