package files;

import java.io.IOException;

/**
 * This interface represents a single ParseableFile object. In order to instantiate, the file must be a ParseableFile.
 * @param <T> The type of the content.
 */
@FunctionalInterface
public interface ParseableFile<T> {
   T getContent() throws IOException;
}
