package englishlanguage;

import englishlanguage.services.PrepositionService;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

public class Preposition implements ReadableWord<String> {
   private final String preposition;

   private Preposition(String preposition) {
      this.preposition = preposition;
   }

   @Contract(" -> new")
   public static @NotNull Preposition createRandom() {
      return new Preposition(PrepositionService.getInstance().getRandom());
   }

   @Contract("_ -> new")
   public static @NotNull Preposition createPreposition(String preposition) {
      if (PrepositionService.getInstance().isPreposition(preposition)) {
         throw new RuntimeException(String.format("%s is not a preposition.", preposition));
      }
      return new Preposition(preposition);
   }

   @Contract("_ -> new")
   public static @NotNull Preposition createOrRandom(String preposition) {
      PrepositionService checker = PrepositionService.getInstance();
      if (!checker.isPreposition(preposition)) {
         return new Preposition(checker.getRandom());
      }
      return new Preposition(preposition);
   }

   @Override
   public String getContent() {
      return preposition;
   }
}
