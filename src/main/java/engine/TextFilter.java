package engine;

import org.jetbrains.annotations.NotNull;

import java.util.stream.Stream;

public class TextFilter {
   public String cleanSymbols(@NotNull String text) {
      return text.replaceAll("[^a-zA-Z]", "");
   }

   public Stream<String> cleanSymbols(@NotNull Stream<String> text) {
      return text.map(w -> w.replaceAll("[^a-zA-Z]", ""));
   }

}
