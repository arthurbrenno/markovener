package englishlanguage;

import englishlanguage.services.NounService;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
/**
 * This class represents a single Noun object. In order to instantiate, the word must be a noun.
 * @since 1.0 SNAPSHOT
 * @author Arthur Brenno
 */
public class Noun implements ReadableWord<String> {


   private final String noun;

   /**
    * CONSTRUCTOR
    * @param noun that will be set as instance variable.
    */
   private Noun(String noun) {
      this.noun = noun;
   }

   /**
    * Factory. Creates a random Noun.
    * @return A Noun object. This object contains a random noun as "noun" instance variable.
    */
   public static @NotNull Noun createRandom() {
      NounService nouns = NounService.getInstance();
      return new Noun(nouns.getRandom());
   }

   /**
    * Factory. Creates a Noun.
    * @param noun to be verified.
    * @return A Noun object. This object will contain a noun if the noun provided is really a noun.
    */
   @Contract("_ -> new")
   public static @NotNull Noun createNoun(@NotNull String noun) {
      NounService nouns = NounService.getInstance();
      if (!nouns.isNoun(noun)) {
         throw new RuntimeException(String.format("%s is not a noun.", noun));
      }
      return new Noun(noun);
   }

   /**
    * Factory. Creates a noun by the input or a random noun.
    * @param noun to be verified.
    * @return Noun instance.
    */
   @Contract("_ -> new")
   public static @NotNull Noun createOrRandom(String noun) {
      NounService nouns = NounService.getInstance();
      if (!nouns.isNoun(noun)) {
         return new Noun(nouns.getRandom());
      }
      return new Noun(noun);
   }

   /**
    * Getter.
    * @return the noun.
    */
   public String getContent() {
      return this.noun;
   }

}
