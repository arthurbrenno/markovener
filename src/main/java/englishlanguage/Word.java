package englishlanguage;

/**
 * This functional interface provides a "getContent()" method to Words.
 * @param <T> The type of the content.
 */
@FunctionalInterface
public interface Word<T> {
   T getContent();
}
