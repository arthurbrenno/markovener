package englishlanguage.services;

/**
 * This functional interface represents an object that can be randomized.
 * @param <T> return type of the method.
 */
@FunctionalInterface
public interface Randomizeable<T> {
   T getRandom();
}
