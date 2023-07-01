package englishlanguage;
import utility.services.NounService;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

/**
 * The main feature of this class is to store internally (as a String) an english
 * noun.
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
      return new Noun(NounService.getInstance().getRandom());
   }

   /**
    * Factory. Creates a Noun.
    * @param word to be verified if it really is a noun.
    * @return A Noun object. This object will contain a noun if the noun provided is really a noun.
    */
   @Contract("_ -> new")
   public static @NotNull Noun createNoun(@NotNull String word) {
      if (!NounService.getInstance().isNoun(word)) {
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
      if (!NounService.getInstance().isNoun(word)) {
         return new Noun(NounService.getInstance().getRandom());
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
