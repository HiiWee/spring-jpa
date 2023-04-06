package jpabook.jpashop.exception;

public class NotEnoughStockException extends RuntimeException {

    private static final String MESSAGE = "재고가 부족합니다.";

    public NotEnoughStockException() {
        super(MESSAGE);
    }

    public NotEnoughStockException(final String message, final Throwable cause) {
        super(MESSAGE, cause);
    }

    public NotEnoughStockException(final Throwable cause) {
        super(cause);
    }

    protected NotEnoughStockException(final String message, final Throwable cause, final boolean enableSuppression,
                                      final boolean writableStackTrace) {
        super(MESSAGE, cause, enableSuppression, writableStackTrace);
    }

}
