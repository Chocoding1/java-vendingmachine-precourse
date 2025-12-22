package vendingmachine.model;

import java.util.ArrayList;
import java.util.List;

public class ProductParser {

    private static final String SEMI_COLON = ";";
    private static final String COMMA = ",";


    public Products parse(String input) {
        String[] tokens = input.split(SEMI_COLON);
        return new Products(parseTokens(tokens));
    }

    private List<Product> parseTokens(String[] tokens) {
        List<Product> products = new ArrayList<>();
        for (String token : tokens) {
            if (token.contains("[") && token.contains("]")) {
                String subToken = token.substring(1, token.length() - 1);
                String[] splitToken = subToken.split(COMMA);
                products.add(new Product(splitToken[0], splitToken[1], splitToken[2]));
            }
        }
        return products;
    }
}
