package engine;
import org.jetbrains.annotations.NotNull;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Provides methods to map tokens.
 * Its main functionality is to map ngrams or words into a desired return type.
 * In Markov Chains, there is a need to map the tokens into its common next tokens (or previous tokens).
 * That's what
 * this class does.
 * @since 1.0 SNAPSHOT
 * @author Arthur Brenno
 */
public final class Mapper {

   /**
    * Maps all the ngrams contained in the list into a HashMap. This maps all the ngrams to its specific next
    * ngrams based on the order.
    * @param ngramsList to be mapped.
    * @return A HashMap containing the ngrams and its most common next ngrams.
    */
   public static HashMap<String, List<String>> mapNgrams(@NotNull List<String> ngramsList) {
      final int order = ngramsList.get(0).length();
      HashMap<String, List<String>> chain = new HashMap<>(ngramsList.size());

      for (int i = 0; i < ngramsList.size() - order; ++i) {

         String key = ngramsList.get(i);
         String value = ngramsList.get(i + order);

         chain.computeIfAbsent(
                 key,
                 k -> new ArrayList<>()
         );

         chain.get(key)
                 .add(value);

      }
      return chain;
   }

   /**
    * Maps all the ngrams contained in the list into a HashMap. This maps all the ngrams to its specific previous ngrams based
    * on the order.
    * @param ngramsList to be mapped backwards
    * @return A HashMap containing the ngrams and its most common previous states/ngrams.
    */
   public static HashMap<String, List<String>> mapNgramsBackwards(@NotNull List<String> ngramsList) {
      HashMap<String, List<String>> chain = new HashMap<>(ngramsList.size());
      final int order = ngramsList.get(0).length();
      for (int i = ngramsList.size() - 1; i >= order; --i) {

         String key = ngramsList.get(i);
         String value = ngramsList.get(i - order);

         chain.computeIfAbsent(
                 key,
                 k -> new ArrayList<>()
         );

         chain.get(key)
                 .add(value);

      }
      return chain;
   }

   /**
    * Maps all the words contained in the list into a HashMap. This maps all the word to its specific next
    * words.
    * @param wordList to be mapped.
    * @return A HashMap containing the words and its most common next words.
    */
   public static HashMap<String, List<String>> mapWords(@NotNull List<String> wordList) {
      HashMap<String, List<String>> chain = new HashMap<>(wordList.size());
      for (int i = 0; i < wordList.size() - 1; ++i) {

         String key = wordList.get(i);
         String value = wordList.get(i + 1);

         chain.computeIfAbsent(
                 key,
                 k -> new ArrayList<>()
         );

         chain.get(key)
                 .add(value);
      }
      return chain;
   }

   /**
    * Maps all the words contained in the list into a HashMap. This maps all the word to its specific previous
    * words.
    * @param wordList to be mapped.
    * @return A HashMap containing the words and its most common previous words.
    */
   public static HashMap<String, List<String>> mapWordsBackwards(@NotNull List<String> wordList) {
      HashMap<String, List<String>> chain = new HashMap<>(wordList.size());

      for (int i = wordList.size() - 1; i > 0; --i) {

         String key = wordList.get(i);
         String value = wordList.get(i - 1);

         chain.computeIfAbsent(
                 key,
                 k -> new ArrayList<>()
         );

         chain.get(key)
                 .add(value);
      }

      return chain;
   }

}
