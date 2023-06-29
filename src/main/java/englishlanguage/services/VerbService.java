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
 * is or not a verb, in addition to having a whole verbs dataset to power up the text generation at its maximum.
 * It was chosen to be a Singleton because there is no need to have another instance of this class in an application.
 * @since 1.0 SNAPSHOT
 * @author Arthur Brenno
 */
public class VerbService implements Randomizeable {

   private Set<String> commonVerbs;
   private static VerbService instance;

   /**
    * Constructor.
    * The constructor of this class is responsible for loading all the common verbs of the english language into the
    * "commonVerbs" instance variable (HashSet)
    */
   private VerbService() {
      commonVerbs = loadVerbs();
   }

   /**
    * Gets the only instance of this class (Singleton).
    * @return VerbService instance.
    */
   public static VerbService getInstance() {
      if (instance == null) {
         instance = new VerbService();
      }
      return instance;
   }

   /**
    * This method is responsible for loading all the common verbs of the english language and putting them into
    * a HashSet.
    * @return a HashSet of the common english verbs based on a dataset located at "resources."
    */
   @Contract(" -> new")
   private @NotNull HashSet<String> loadVerbs() {
      try (InputStream stream = VerbService.class.getResourceAsStream("/parts_of_speech/verbs/31K_verbs.txt")) {
         assert stream != null;
         return new HashSet<>(Arrays.asList(new String(stream.readAllBytes()).split(System.lineSeparator())));
      } catch (IOException e) {
         throw new RuntimeException(e);
      }
   }

   /**
    * Getter.
    * @return commonVerbs instance variable (HashSet).
    */
   public Set<String> commonVerbs() {
      return commonVerbs;
   }

   /**
    * Checks if a word is a verb
    * @param word to be checked.
    * @return true if the word is a verb, false otherwise.
    */
   public boolean isVerb(@NotNull String word) {
      return commonVerbs.contains(word.toLowerCase());
   }

   /**
    * Gets a random verb based on the most common verbs of the english language.
    * @return the random verb.
    */
   public String getRandom() {
      List<String> temp = new ArrayList<>(commonVerbs);
      return temp.get(ThreadLocalRandom.current().nextInt(temp.size()));
   }

}
