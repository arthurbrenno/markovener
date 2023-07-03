package util;
import org.jetbrains.annotations.NotNull;
import java.util.stream.Stream;

/**
 * The main purpose of this class is to clear your text. When dealing with tokenization, a common approach before
 * doing it is cleaning up the text first. That means that this class is supposed to clear unwanted characters from a
 * text.
 * @since 1.0 SNAPSHOT
 * @author Arthur Brenno
 */
public final class TextFilter {

   /**
    * Cleans all the non-letter characters from the text.
    * @param text to be cleaned.
    * @return cleaned text.
    */
   public static String clearSymbolsAndNumbers(@NotNull String text) {
      return text.replaceAll("[^a-zA-Z]", "");
   }

   /**
    * Cleans all the non-letter characters from the Stream.
    * @param textStream Stream to be cleaned.
    * @return cleaned stream.
    */
   public static Stream<String> clearSymbolsAndNumbers(@NotNull Stream<String> textStream) {
      return textStream.map(TextFilter::clearSymbolsAndNumbers);
   }

}
