package co.mlforex.forecast.vinculadorCodigo.exceptions;

public class BusinessException extends Exception{

    private static final long serialVersionUID = -2219131656577701646L;

    public BusinessException(final String errorMessage) {
        super(errorMessage);
    }
}
