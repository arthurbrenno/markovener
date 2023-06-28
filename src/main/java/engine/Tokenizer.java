package engine;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Tokenizer {

   public List<String> getNgrams(@NotNull String text, int order) {
      List<String> result = new ArrayList<>();
      for (int i = 0; i <= text.length() - order; ++i) {
         result.add(text.substring(i, i + order));
      }
      return result;
   }

   public List<String> getWords(@NotNull String text) {
      String[] words = text.split(" ");
      return new ArrayList<>(Arrays.asList(words));
   }

   public List<String> getWords(@NotNull Stream<String> text) {
      return text.map(w -> w.split(" "))
                 .flatMap(Stream::of)
                 .collect(Collectors.toList());
   }

}
