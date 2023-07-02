package newengine;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public final class Chain {

   private final List<String> tokens;
   private final HashMap<String, List<String>> map;

   private Chain(String text, int order) {
      tokens = tokenizeNgrams(text, order);
      map = mapNgrams(tokens);
   }

   private Chain(String text) {
      tokens = tokenizeWords(text);
      map = mapWords(tokens);
   }

   @Contract("_, _ -> new")
   public static @NotNull Chain createByNgrams(String filteredText, int order) {
      return new Chain(filteredText, order);
   }

   public static @NotNull Chain createByWords(String filteredText) {
      return new Chain(filteredText);
   }

   private @NotNull List<String> tokenizeNgrams(@NotNull String text, int order) {
      List<String> result = new ArrayList<>();
      for (int i = 0; i <= text.length() - order; ++i) {
         result.add(text.substring(i, i + order));
      }
      return result;
   }

   private @NotNull List<String> tokenizeWords(@NotNull String text) {
      return List.of(text.split(" "));
   }

   private @NotNull HashMap<String, List<String>> mapNgrams(@NotNull List<String> tokens) {
      final HashMap<String, List<String>> chain = new HashMap<>(tokens.size());
      final int order = tokens.get(0).length();
      for (int i = 0; i < tokens.size() - order; ++i) {
         String key = tokens.get(i);
         String value = tokens.get(i + order);
         chain.computeIfAbsent(
                 key,
                 k -> new ArrayList<>()
         );
         chain.get(key).add(value);
      }
      return chain;
   }

   private @NotNull HashMap<String, List<String>> mapWords(@NotNull List<String> tokens) {
      final HashMap<String, List<String>> chain = new HashMap<>(tokens.size());
      for (int i = 0; i < tokens.size() - 1; ++i) {
         String key = tokens.get(i);
         String value = tokens.get(i + 1);
         chain.computeIfAbsent(
                 key,
                 k -> new ArrayList<>()
         );
         chain.get(key).add(value);
      }
      return chain;
   }

}
