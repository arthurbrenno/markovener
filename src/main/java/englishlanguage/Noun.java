package englishlanguage;

import englishlanguage.services.NounService;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

public class Noun implements ReadableWord<String> {


   private final String noun;
   private Noun(String noun) {
      this.noun = noun;
   }

   public static @NotNull Noun createRandom() {
      NounService nouns = NounService.getInstance();
      return new Noun(nouns.getRandom());
   }

   @Contract("_ -> new")
   public static @NotNull Noun createNoun(@NotNull String noun) {
      NounService nouns = NounService.getInstance();
      if (!nouns.isNoun(noun)) {
         throw new RuntimeException(String.format("%s is not a noun.", noun));
      }
      return new Noun(noun);
   }

   @Contract("_ -> new")
   public static @NotNull Noun createOrRandom(String noun) {
      NounService nouns = NounService.getInstance();
      if (!nouns.isNoun(noun)) {
         return new Noun(nouns.getRandom());
      }
      return new Noun(noun);
   }

   public String getContent() {
      return this.noun;
   }

}
