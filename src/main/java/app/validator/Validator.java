package app.validator;

@FunctionalInterface
public interface Validator<T> {
    boolean isValid(T value);
}
