package files;

import java.io.IOException;

@FunctionalInterface
public interface ParseableFile<T> {
   T getContent() throws IOException;
}
