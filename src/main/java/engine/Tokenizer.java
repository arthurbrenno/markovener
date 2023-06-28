package engine;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
/**
 * This class represents a Tokenizer object. It helps you to tokenize your text into words or Ngrams.
 * @since 1.0 SNAPSHOT
 * @author Arthur Brenno
 */
public class Tokenizer {

   /**
    * gets the ngrams of a text. this DOES NOT clean your text. Its responsibility is to just do one thing.
    * @param text to be analysed.
    * @param order ngram size.
    * @return a List containing all the ngrams of a text.
    */
   public List<String> getNgrams(@NotNull String text, int order) {
      List<String> result = new ArrayList<>();
      for (int i = 0; i <= text.length() - order; ++i) {
         result.add(text.substring(i, i + order));
      }
      return result;
   }

   /**
    * gets the words List of a text. this DOES NOT clean your text. Its responsibility is to just do one thing.
    * @param text to be analysed.
    * @return a List containing all the words of a text.
    */
   public List<String> getWords(@NotNull String text) {
      String[] words = text.split(" ");
      return new ArrayList<>(Arrays.asList(words));
   }

   /**
    * gets the words List of a Stream. this DOES NOT clean your text. Its responsibility is to just do one thing.
    * @param text Stream to be analysed.
    * @return a List containing all the words of a text.
    */
   public List<String> getWords(@NotNull Stream<String> text) {
      return text.map(w -> w.split(" "))
                 .flatMap(Stream::of)
                 .collect(Collectors.toList());
   }

}
