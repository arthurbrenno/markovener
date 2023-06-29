package engine;
import org.jetbrains.annotations.NotNull;
import java.util.stream.Stream;

/**
 * This class represents a TextFilter object. When dealing with tokenization, a common approach before doing it is
 * cleaning up the text. That means that this class is supposed to clear unwanted characters from a text.
 * @since 1.0 SNAPSHOT
 * @author Arthur Brenno
 */
public class TextFilter {

   /**
    * Cleans all the non-letter characters from the text.
    * @param text to be cleaned.
    * @return cleaned text.
    */
   public String clearSymbolsAndNumbers(@NotNull String text) {
      return text.replaceAll("[^a-zA-Z]", "");
   }

   /**
    * Cleans all the non-letter characters from the Stream.
    * @param textStream Stream to be cleaned.
    * @return cleaned stream.
    */
   public Stream<String> clearSymbolsAndNumbers(@NotNull Stream<String> textStream) {
      return textStream.map(w -> w.replaceAll("[^a-zA-Z]", ""));
   }

}
