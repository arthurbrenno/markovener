package util.engine;
import com.google.gson.Gson;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Unmodifiable;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class Markovener {

   private final List<String> tokens;
   private final HashMap<String, List<String>> map;
   private final String jsonRepresentation;


   private Markovener(String text, int order) {
      tokens = tokenizeNgrams(text, order);
      map = mapNgrams(tokens);
      jsonRepresentation = new Gson().toJson(map);
   }

   private Markovener(String text) {
      tokens = tokenizeWords(text);
      map = mapWords(tokens);
      jsonRepresentation = new Gson().toJson(map);
   }


   @Contract("_, _ -> new")
   public static @NotNull Markovener createByNgrams(String filteredText, int order) {
      return new Markovener(filteredText, order);
   }

   public static @NotNull Markovener createByWords(String filteredText) {
      return new Markovener(filteredText);
   }

   public List<String> tokens() {
      return tokens;
   }

   public Map<String, List<String>> map() {
      return map;
   }

   public String jsonRepresentation() {
      return jsonRepresentation;
   }

   public void save(@NotNull Path directory) throws IOException {
      final Path finalPath = Path.of(new StringBuilder(directory.toString()).append("\\output.json").toString());
      boolean success = new File(finalPath.toUri()).createNewFile();
      if (success) {
         Files.write(finalPath, jsonRepresentation().getBytes());
      }
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
