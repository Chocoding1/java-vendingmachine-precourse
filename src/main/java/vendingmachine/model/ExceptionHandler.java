package vendingmachine.model;

import java.util.function.Supplier;

public class ExceptionHandler {

    public static <T> T repeatSupplierUntilSuccess(Supplier<T> supplier) {
        while (true) {
            try {
                return supplier.get();
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public static void repeatRunnableUntilSuccess(Runnable runnable) {
        while (true) {
            try {
                runnable.run();
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
