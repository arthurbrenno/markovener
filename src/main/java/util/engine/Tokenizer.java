package util.engine;
import org.jetbrains.annotations.NotNull;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Provides methods to tokenize texts. Tokenization is a common step when dealing with Markov Chains. This
 * class is supposed to help you to tokenize your text into ngrams or words.
 * @since 1.0 SNAPSHOT
 * @author Arthur Brenno
 */
public final class Tokenizer {

   /**
    * Tokenizes the ngrams of a text to a List. this DOES NOT clean your text. Its responsibility is to just tokenize a
    * text into
    * ngrams.
    * @param text to be tokenized.
    * @param order ngram size.
    * @return a List containing all the ngrams of the text.
    */
   public List<String> getNgrams(@NotNull String text, int order) {
      List<String> result = new ArrayList<>();
      for (int i = 0; i <= text.length() - order; ++i) {
         result.add(text.substring(i, i + order));
      }
      return result;
   }

   /**
    * Tokenizes the words of a text into a List. this DOES NOT clean your text. Its responsibility is to just do one
    * thing.
    * @param text to be tokenized.
    * @return a List containing all the words of a text.
    */
   public List<String> getWords(@NotNull String text) {
      String[] words = text.split(" ");
      return new ArrayList<>(Arrays.asList(words));
   }

   /**
    * Tokenizes all the words of a stream into a List. this DOES NOT clean your text. Its responsibility is to just do
    * one thing.
    * @param text Stream to be tokenized.
    * @return a List containing all the words of a text.
    */
   public List<String> getWords(@NotNull Stream<String> text) {
      return text.map(w -> w.split(" "))
                 .flatMap(Stream::of)
                 .collect(Collectors.toList());
   }

}
