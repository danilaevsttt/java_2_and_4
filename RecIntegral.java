package pkg23vvi1;

import java.io.Serializable;
import java.util.Locale;

/**
 * Класс для хранения одной записи таблицы интегралов
 */
public class RecIntegral implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private double lowerBound;
    private double upperBound;
    private double step;
    private String result;

    private static final double MIN_VALUE = 0.000001;
    private static final double MAX_VALUE = 1000000.0;

    public RecIntegral(double lowerBound, double upperBound, double step, String result)
            throws InvalidIntegralDataException {
        
        validateValue(lowerBound, "Нижний порог");
        validateValue(upperBound, "Верхний порог");
        validateValue(step, "Шаг");

        if (lowerBound >= upperBound) {
            throw new InvalidIntegralDataException(
                String.format("Нижний порог (%.6f) не может быть больше или равен верхнему порогу (%.6f).",
                              lowerBound, upperBound));
        }

        if (step > (upperBound - lowerBound)) {
            throw new InvalidIntegralDataException(
                "Шаг не может быть больше интервала [нижний порог; верхний порог].");
        }

        this.lowerBound = lowerBound;
        this.upperBound = upperBound;
        this.step = step;
        this.result = result;
    }

    public RecIntegral() {
        this.lowerBound = 0;
        this.upperBound = 0;
        this.step = 0;
        this.result = "";
    }

    private void validateValue(double value, String fieldName) throws InvalidIntegralDataException {
        if (Double.isNaN(value) || Double.isInfinite(value)) {
            throw new InvalidIntegralDataException(
                String.format("Поле \"%s\" не является корректным числом!", fieldName));
        }
        if (value < MIN_VALUE || value > MAX_VALUE) {
            throw new InvalidIntegralDataException(fieldName, value);
        }
    }

    public double getLowerBound() { return lowerBound; }
    public double getUpperBound() { return upperBound; }
    public double getStep() { return step; }
    public String getResult() { return result; }

    // 🔧 ИСПРАВЛЕНО: добавлена проверка что шаг подходит для нового интервала
    public void setLowerBound(double lowerBound) throws InvalidIntegralDataException {
        validateValue(lowerBound, "Нижний порог");
        if (lowerBound >= this.upperBound) {
            throw new InvalidIntegralDataException(
                String.format("Нижний порог (%.6f) не может быть больше или равен верхнему порогу (%.6f).",
                              lowerBound, this.upperBound));
        }
        // Проверка: шаг не должен превышать новый интервал
        if (this.step > (this.upperBound - lowerBound)) {
            throw new InvalidIntegralDataException(
                String.format("Шаг (%.6f) превышает новый интервал (%.6f). Уменьшите шаг или измените пороги.",
                              this.step, (this.upperBound - lowerBound)));
        }
        this.lowerBound = lowerBound;
    }

    // 🔧 ИСПРАВЛЕНО: добавлена проверка что шаг подходит для нового интервала
    public void setUpperBound(double upperBound) throws InvalidIntegralDataException {
        validateValue(upperBound, "Верхний порог");
        if (this.lowerBound >= upperBound) {
            throw new InvalidIntegralDataException(
                String.format("Нижний порог (%.6f) не может быть больше или равен верхнему порогу (%.6f).",
                              this.lowerBound, upperBound));
        }
        // Проверка: шаг не должен превышать новый интервал
        if (this.step > (upperBound - this.lowerBound)) {
            throw new InvalidIntegralDataException(
                String.format("Шаг (%.6f) превышает новый интервал (%.6f). Уменьшите шаг или измените пороги.",
                              this.step, (upperBound - this.lowerBound)));
        }
        this.upperBound = upperBound;
    }

    public void setStep(double step) throws InvalidIntegralDataException {
        validateValue(step, "Шаг");
        if (step > (upperBound - lowerBound)) {
            throw new InvalidIntegralDataException(
                "Шаг не может быть больше интервала [нижний порог; верхний порог].");
        }
        this.step = step;
    }

    public void setResult(String result) { this.result = result; }

    public String compute() {
        double a = lowerBound;
        double b = upperBound;
        double h = step;

        if (h <= 0) return "Ошибка: шаг <= 0";
        if (a >= b) return "Ошибка: a >= b";

        try {
            int n = (int)((b - a) / h);
            if (n < 1) { n = 1; h = b - a; }
            double sum = 0;
            for (int i = 1; i < n; i++) {
                double x = a + i * h;
                sum += Math.sin(x);
            }
            double fa = Math.sin(a);
            double fb = Math.sin(b);
            double integral = (h / 2) * (fa + 2 * sum + fb);
            double lastFullX = a + n * h;
            double remainder = b - lastFullX;
            if (remainder > 1e-10) {
                double fLast = Math.sin(lastFullX);
                integral += (remainder / 2) * (fLast + fb);
            }
    
            return String.format(Locale.US, "%.6f", integral);
        } catch (Exception e) {
            return "Ошибка вычисления";
        }
    }

    @Override
    public String toString() {
        return String.format("RecIntegral{a=%.3f, b=%.3f, h=%.3f, res=%s}",
                             lowerBound, upperBound, step, result);
    }
}