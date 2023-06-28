package engine;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * This class represents a mapper object. Its main functionality is to get a ngram/word list and map them forwardly or backwards.
 * @since 1.0 SNAPSHOT
 * @author Arthur Brenno
 */
public class Mapper {

   /**
    * Maps a ngram list into a HashMap: ngram(String) - next common ngrams (List - String)
    * @param ngramsList to be mapped
    * @return A HashMap containing the ngrams and its most common next states/ngrams.
    */
   public HashMap<String, List<String>> mapNgrams(@NotNull List<String> ngramsList) {
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
    * Maps a ngram list backwards into a HashMap: ngram(String) - previous common ngrams (List - String)
    * @param ngramsList to be mapped backwards
    * @return A HashMap containing the ngrams and its most common previous states/ngrams.
    */
   public HashMap<String, List<String>> mapNgramsBackwards(@NotNull List<String> ngramsList) {
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
    * Maps a word list into a HashMap: word(String) - next common words (List - String)
    * @param wordList to be mapped
    * @return A HashMap containing the ngrams and its most common next states/words.
    */
   public HashMap<String, List<String>> mapWords(@NotNull List<String> wordList) {
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
    * Maps a word list into a HashMap: word(String) - previous common words (List - String)
    * @param wordList to be mapped
    * @return A HashMap containing the ngrams and its most common previous states/words.
    */
   public HashMap<String, List<String>> mapWordsBackwards(@NotNull List<String> wordList) {
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
