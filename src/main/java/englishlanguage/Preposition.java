package englishlanguage;
import englishlanguage.services.PrepositionService;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

/**
 * The main feature of this class is to store internally (as a String) an english
 * preposition.
 * The idea of this class is to provide a short and easy way to classify a word in respect to english language. This
 * could be used with the Markov Chains to create clearer random sentences and provide a good way to classify words.
 * @since 1.0 SNAPSHOT
 * @author Arthur Brenno
 */
public class Preposition implements Word<String> {

   private final String preposition;

   /**
    * Constructor.
    * @param preposition that will be set as instance variable.
    */
   private Preposition(String preposition) {
      this.preposition = preposition;
   }

   /**
    * Factory. Creates a random Preposition object.
    * @return A Preposition object. This object contains a random preposition as "preposition" instance variable.
    */
   @Contract(" -> new")
   public static @NotNull Preposition createRandom() {
      return new Preposition(PrepositionService.getRandom());
   }

   /**
    * Factory. Creates a Preposition.
    * @param preposition to be verified.
    * @return A Preposition object. This object will contain a preposition if the preposition provided is really a preposition.
    * @throws RuntimeException if the preposition is not a preposition.
    */
   @Contract("_ -> new")
   public static @NotNull Preposition createPreposition(String preposition) {
      if (PrepositionService.isPreposition(preposition)) {
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
      if (!PrepositionService.isPreposition(preposition)) {
         return new Preposition(PrepositionService.getRandom());
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
