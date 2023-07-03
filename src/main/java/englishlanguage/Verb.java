package englishlanguage;
import englishlanguage.services.VerbService;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

/**
 * The main feature of this class is to store internally (as a String) an english
 * verb.
 * The idea of this class is to provide a short and easy way to classify a word in respect to english language. This
 * could be used with the Markov Chains to create clearer random sentences and provide a good way to classify words.
 * @since 1.0 SNAPSHOT
 * @author Arthur Brenno
 */
public class Verb implements Word<String> {

   private final String verb;

   /**
    * Constructor.
    * @param verb that will be set as instance variable.
    */
   private Verb(String verb) {
      this.verb = verb;
   }

   /**
    * Factory. Creates a random Verb object.
    * @return A Verb object. This object contains a random verb as "verb" instance variable.
    */
   @Contract(" -> new")
   public static @NotNull Verb createRandom() {
      return new Verb(VerbService.getRandom());
   }

   /**
    * Factory. Creates a Verb object if the input is valid.
    * @param word to be verified.
    * @return A Verb object. This object will contain a verb if the verb provided is really a verb.
    * @throws RuntimeException if the verb provided is not a verb.
    */
   @Contract("_ -> new")
   public static @NotNull Verb createVerb(String word) {
      if (!VerbService.isVerb(word)) {
         throw new RuntimeException(String.format("%s is not a verb.", word));
      }
      return new Verb(word);
   }

   /**
    * Factory. Creates a verb by the input or a random verb.
    * @param word to be verified.
    * @return Verb instance.
    */
   @Contract("_ -> new")
   public static @NotNull Verb createOrRandom(String word) {
      if (!VerbService.isVerb(word)) {
         return new Verb(VerbService.getRandom());
      }
      return new Verb(word);
   }

   /**
    * This method returns the content of the verb.
    * @return the content of the verb.
    */
   public String getContent() {
      return this.verb;
   }

}
