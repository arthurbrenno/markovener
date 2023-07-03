package markovener.engine;
import com.google.gson.Gson;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Unmodifiable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

   public String toJson() {
      return new Gson().toJson(map);
   }

   public List<String> tokens() {
      return tokens;
   }

   public Map<String, List<String>> map() {
      return map;
   }

   private @NotNull List<String> tokenizeNgrams(@NotNull String text, int order) {
      List<String> result = new ArrayList<>();
      for (int i = 0; i <= text.length() - order; ++i) {
         result.add(text.substring(i, i + order));
      }
      return result;
   }

   private @NotNull @Unmodifiable List<String> tokenizeWords(@NotNull String text) {
      return List.of(text.split(" "));
   }

   private @NotNull HashMap<String, List<String>> mapNgrams(@NotNull final List<String> tokens) {
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

   private @NotNull HashMap<String, List<String>> mapWords(@NotNull final List<String> tokens) {
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
