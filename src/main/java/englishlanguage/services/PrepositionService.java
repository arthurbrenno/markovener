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
 * is or not a preposition, in addition to having a whole prepositions dataset to power up the text generation at its
 * maximum.
 * It was chosen to be a Singleton because there is no need to have another instance of this class in an application.
 * @since 1.0 SNAPSHOT
 * @author Arthur Brenno
 */
public final class PrepositionService {

   private static final Set<String> commonPrepositions = loadCommonPrepositions();

   /**
    * This class is not instantiable
    */
   private PrepositionService() {}

   /**
    * This method is responsible for loading all the common prepositions of the english language and putting them into
    * a HashSet.
    * @return a HashSet of the common english prepositions based on a dataset located at "resources."
    */
   @Contract(" -> new")
   private static @NotNull HashSet<String> loadCommonPrepositions() {
      try (InputStream stream = PrepositionService.class.getResourceAsStream("/parts_of_speech/prepositions/70_prepositions.txt")) {
         assert stream != null;
         return new HashSet<>(Arrays.asList(new String(stream.readAllBytes()).split(System.lineSeparator())));
      } catch (IOException e) {
         throw new RuntimeException(e);
      }
   }

   /**
    * Getter.
    * @return the commonPrepositions instance variable (HashSet).
    */
   public static Set<String> commonPrepositions() {
      return commonPrepositions;
   }

   /**
    * Checks if a word is a preposition.
    * @param word to be analyzed.
    * @return true if the word is a preposition found in a database, false if not.
    */
   public static boolean isPreposition(@NotNull String word) {
      return commonPrepositions.contains(word.toLowerCase());
   }

   /**
    * Gets a random preposition from the commonPrepositions HashSet.
    * @return a random preposition.
    */
   public static String getRandom() {
      List<String> temp = new ArrayList<>(commonPrepositions);
      return temp.get(ThreadLocalRandom.current().nextInt(temp.size()));
   }

}
