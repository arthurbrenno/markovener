package englishlanguage.services;

/**
 * Represents an object that can be randomized.
 * @param <T> return type of the method.
 */
@FunctionalInterface
public interface Randomizeable<T> {
   T getRandom();
}
