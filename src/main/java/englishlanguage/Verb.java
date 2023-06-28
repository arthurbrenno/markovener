package englishlanguage;

import englishlanguage.services.VerbService;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

public class Verb implements ReadableWord<String> {
   private final String verb;

   private Verb(String verb) {
      this.verb = verb;
   }

   @Contract(" -> new")
   public static @NotNull Verb createRandom() {
      return new Verb(VerbService.getInstance().getRandom());
   }

   @Contract("_ -> new")
   public static @NotNull Verb createVerb(String verb) {
      if (!VerbService.getInstance().isVerb(verb)) {
         throw new RuntimeException(String.format("%s is not a verb.", verb));
      }
      return new Verb(verb);
   }

   @Contract("_ -> new")
   public static @NotNull Verb createOrRandom(String verb) {
      VerbService verbs = VerbService.getInstance();
      if (!verbs.isVerb(verb)) {
         return new Verb(verbs.getRandom());
      }
      return new Verb(verb);
   }

   public String getContent() {
      return this.verb;
   }
}
