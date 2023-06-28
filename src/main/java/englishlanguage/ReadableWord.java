package englishlanguage;

/**
 * This interface represents a single ReadableWord object. In order to instantiate, the word must be a ReadableWord.
 * @param <T> The type of the content.
 */
@FunctionalInterface
public interface ReadableWord<T> {
   T getContent();
}
