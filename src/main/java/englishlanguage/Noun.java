package englishlanguage;
import englishlanguage.services.NounService;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

/**
 * The main feature of this class is to store internally (as a String) an english
 * noun.
 * The idea of this class is to provide a short and easy way to classify a word in respect to english language. This
 * could be used with the Markov Chains to create clearer random sentences and provide a good way to classify words.
 * @since 1.0 SNAPSHOT
 * @author Arthur Brenno
 */
public class Noun implements Word<String> {

   private final String noun;

   /**
    * Constructor.
    * @param noun that will be set as instance variable.
    */
   private Noun(String noun) {
      this.noun = noun;
   }

   /**
    * Factory. Creates a random Noun object.
    * @return A Noun object. This object contains a random noun as "noun" instance variable.
    */
   public static @NotNull Noun createRandom() {
      return new Noun(NounService.getRandom());
   }

   /**
    * Factory. Creates a Noun.
    * @param word to be verified if it really is a noun.
    * @return A Noun object. This object will contain a noun if the noun provided is really a noun.
    */
   @Contract("_ -> new")
   public static @NotNull Noun createNoun(@NotNull String word) {
      if (!NounService.isNoun(word)) {
         throw new RuntimeException(String.format("%s is not a noun.", word));
      }
      return new Noun(word);
   }

   /**
    * Factory. Creates a noun by the input or a random noun.
    * @param word to be verified.
    * @return Noun instance.
    */
   @Contract("_ -> new")
   public static @NotNull Noun createOrRandom(String word) {
      if (!NounService.isNoun(word)) {
         return new Noun(NounService.getRandom());
      }
      return new Noun(word);
   }

   /**
    * Getter.
    * @return the noun that this class represents.
    */
   public String getContent() {
      return this.noun;
   }

}
