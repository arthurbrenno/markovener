package englishlanguage.services;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Provides methods to classify words.
 * It is necessary because there's a need to check if a word
 * is or not a noun, in addition to having a whole nouns' dataset to power up the text generation at its maximum.
 * It was chosen to be a Singleton because there is no need to have another instance of this class in an application.
 * @since 1.0 SNAPSHOT
 * @author Arthur Brenno
 */
public final class NounService {

   private static final Set<String> commonNouns = loadNouns();

   /**
    * This class is not instantiable
    */
   private NounService() {}

   /**
    * This method is responsible for loading all the common nouns of the english language and putting them into a
    * HashSet.
    * @return a HashSet of the common english nouns based on a dataset located at "resources."
    */
   @Contract(" -> new")
   private static @NotNull HashSet<String> loadNouns() {
      try (InputStream stream = NounService.class.getResourceAsStream("/parts_of_speech/nouns/91K_nouns.txt")) {
         return new HashSet<>(Arrays.asList(new String(stream.readAllBytes()).split(System.lineSeparator())));
      } catch (IOException e) {
         throw new RuntimeException(e);
      }
   }

   /**
    * Getter.
    * @return commonNouns HashSet.
    */
   public static Set<String> commonNouns() {
      return commonNouns;
   }

   /**
    * Checks if a word is a noun.
    * @param word to be checked (will be converted to lower-case).
    * @return true if it is a noun, false if it's not.
    */
   public static boolean isNoun(@NotNull String word) {
      return commonNouns.contains(word.toLowerCase());
   }

   /**
    * Gets a random noun based on a dataset.
    * @return random noun.
    */
   public static String getRandom() {
      List<String> temp = new ArrayList<>(commonNouns);
      return temp.get(ThreadLocalRandom.current().nextInt(temp.size()));
   }

}
