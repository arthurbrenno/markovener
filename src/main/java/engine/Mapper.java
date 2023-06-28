package engine;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Mapper {

   public static void main(String[] args) {
      System.out.println(
              new Mapper().mapNgrams(new Tokenizer().getNgrams("This is a text", 2))
      );
   }

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
