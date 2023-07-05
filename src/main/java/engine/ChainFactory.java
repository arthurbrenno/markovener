package engine;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

/**
 * Provides factory methods to create Chain objects.
 * @since 1.0.1 ALPHA
 */
public class ChainFactory {

   /**
    * Factory. Creates a Markovener object by calling the private constructor.
    * @param filteredText to be tokenized and mapped.
    * @param order to split the ngrams.
    * @return an instance of the Markovener class tokenized and mapped by ngrams.
    */
   @Contract("_, _ -> new")
   public static @NotNull Chain createByNgrams(String filteredText, int order) {
      return new Chain(filteredText, order);
   }

   /**
    * Factory. Creates a Markovener object by calling the private constructor.
    * @param filteredText to be tokenized and mapped.
    * @return an instance of the Markovener class tokenized and mapped by words.
    */
   public static @NotNull Chain createByWords(String filteredText) {
      return new Chain(filteredText);
   }


}
