package englishlanguage;

import englishlanguage.services.VerbService;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
/**
 * This class represents a single Verb object. In order to instantiate, the word must be a verb.
 * @since 1.0 SNAPSHOT
 * @author Arthur Brenno
 */
public class Verb implements ReadableWord<String> {
   private final String verb;

   /**
    * CONSTRUCTOR
    * @param verb that will be set as instance variable.
    */
   private Verb(String verb) {
      this.verb = verb;
   }

   /**
    * Factory. Creates a random Verb.
    * @return A Verb object. This object contains a random verb as "verb" instance variable.
    */
   @Contract(" -> new")
   public static @NotNull Verb createRandom() {
      return new Verb(VerbService.getInstance().getRandom());
   }

   /**
    * Factory. Creates a Verb.
    * @param verb to be verified.
    * @return A Verb object. This object will contain a verb if the verb provided is really a verb.
    * @throws RuntimeException if the verb provided is not a verb.
    */
   @Contract("_ -> new")
   public static @NotNull Verb createVerb(String verb) {
      if (!VerbService.getInstance().isVerb(verb)) {
         throw new RuntimeException(String.format("%s is not a verb.", verb));
      }
      return new Verb(verb);
   }

   /**
    * Factory. Creates a verb by the input or a random verb.
    * @param verb to be verified.
    * @return Verb instance.
    */
   @Contract("_ -> new")
   public static @NotNull Verb createOrRandom(String verb) {
      VerbService verbs = VerbService.getInstance();
      if (!verbs.isVerb(verb)) {
         return new Verb(verbs.getRandom());
      }
      return new Verb(verb);
   }

   /**
    * This method returns the content of the verb.
    * @return the content of the verb.
    */
   public String getContent() {
      return this.verb;
   }
}
