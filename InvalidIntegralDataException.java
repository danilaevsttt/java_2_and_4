package pkg23vvi1;

/**
 * Пользовательское исключение для некорректных данных интеграла
 */
public class InvalidIntegralDataException extends Exception {
    private static final double MIN_VALUE = 0.000001;
    private static final double MAX_VALUE = 1000000.0;
    
    public InvalidIntegralDataException(String fieldName, double value) {
        super(String.format(
            "Некорректное значение поля \"%s\": %.10g\n" +
            "Допустимый диапазон: [%.6f; %.0f]",
            fieldName, value, MIN_VALUE, MAX_VALUE
        ));
    }
    
    public InvalidIntegralDataException(String message) {
        super(message);
    }
}