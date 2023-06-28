package englishlanguage;

import englishlanguage.services.PrepositionService;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
/**
 * This class represents a single Preposition object. In order to instantiate, the word must be a preposition.
 * @since 1.0 SNAPSHOT
 * @author Arthur Brenno
 */
public class Preposition implements ReadableWord<String> {
   private final String preposition;

   /**
    * CONSTRUCTOR
    * @param preposition that will be set as instance variable.
    */
   private Preposition(String preposition) {
      this.preposition = preposition;
   }

   /**
    * Factory. Creates a random Preposition.
    * @return A Preposition object. This object contains a random preposition as "preposition" instance variable.
    */
   @Contract(" -> new")
   public static @NotNull Preposition createRandom() {
      return new Preposition(PrepositionService.getInstance().getRandom());
   }

   /**
    * Factory. Creates a Preposition.
    * @param preposition to be verified.
    * @return A Preposition object. This object will contain a preposition if the preposition provided is really a preposition.
    * @throws RuntimeException if the preposition is not a preposition.
    */
   @Contract("_ -> new")
   public static @NotNull Preposition createPreposition(String preposition) {
      if (PrepositionService.getInstance().isPreposition(preposition)) {
         throw new RuntimeException(String.format("%s is not a preposition.", preposition));
      }
      return new Preposition(preposition);
   }

   /**
    * Factory. Creates a preposition by the input or a random preposition.
    * @param preposition to be verified.
    * @return Preposition instance.
    */
   @Contract("_ -> new")
   public static @NotNull Preposition createOrRandom(String preposition) {
      PrepositionService checker = PrepositionService.getInstance();
      if (!checker.isPreposition(preposition)) {
         return new Preposition(checker.getRandom());
      }
      return new Preposition(preposition);
   }

   /**
    * Getter. Returns the preposition.
    * @return preposition.
    */
   @Override
   public String getContent() {
      return preposition;
   }
}
