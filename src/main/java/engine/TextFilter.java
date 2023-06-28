package engine;

import org.jetbrains.annotations.NotNull;

import java.util.stream.Stream;
/**
 * This class represents a TextFilter object. The main goal right here is to clean unwanted characters in a text.
 * @since 1.0 SNAPSHOT
 * @author Arthur Brenno
 */
public class TextFilter {
   /**
    * Cleans all the non-letter characters from the text.
    * @param text to be cleaned
    * @return cleaned text
    */
   public String cleanSymbols(@NotNull String text) {
      return text.replaceAll("[^a-zA-Z]", "");
   }

   /**
    * Cleans all the non-letter characters from the String Stream.
    * @param textStream Stream to be cleaned
    * @return cleaned stream
    */
   public Stream<String> cleanSymbols(@NotNull Stream<String> textStream) {
      return textStream.map(w -> w.replaceAll("[^a-zA-Z]", ""));
   }

}
