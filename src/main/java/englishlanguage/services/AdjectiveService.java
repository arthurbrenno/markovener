package englishlanguage.services;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

/**
 * This class represents a single AdjectiveService object. It is necessary because there's a need to check if a word
 * is or not an adjective, in addition to having a whole adjectives' dataset to power up the text generation at its maximum.
 * It was chosen to be a Singleton because there is no need to have another instance of this class in an application.
 * @since 1.0 SNAPSHOT
 * @author Arthur Brenno
 */
public class AdjectiveService implements Randomizeable<String>{

   Set<String> commonAdjectives;
   private static AdjectiveService instance;

   /**
    * Constructor.
    * The constructor is responsible for initializing the "commonAdjectives" Set by calling the private method
    * "loadCommonAdjectives()", which implementation is private.
    */
   private AdjectiveService(){
      commonAdjectives = loadCommonAdjectives();
   }

   /**
    * This method is responsible for loading all the common adjectives of the english language and putting them into
    * a HashSet.
    * @return a HashSet of the common english adjectives based on a dataset located at "resources."
    */
   @Contract(" -> new")
   private @NotNull HashSet<String> loadCommonAdjectives() {
      try (InputStream stream = AdjectiveService.class.getResourceAsStream("/parts_of_speech/adjectives/28K_adjectives.txt")) {
         assert stream != null;
         return new HashSet<>(Arrays.asList(new String(stream.readAllBytes()).split(System.lineSeparator())));
      } catch (IOException e) {
         throw new RuntimeException(e);
      }
   }

   /**
    * Getter.
    * @return the "commonAdjectives" instance variable (HashSet).
    */
   public Set<String> commonAdjectives() {
      return commonAdjectives;
   }

   /**
    * Gets the only instance of this class.
    * @return AdjectiveService instance.
    */
   public static AdjectiveService getInstance() {
      if (instance == null) {
         instance = new AdjectiveService();
      }
      return instance;
   }

   /**
    * Checks if a word is an adjective.
    * @param word to be checked (will be converted to lower-case).
    * @return true if it is an adjective, false if it's not.
    */
   public boolean isAdjective(@NotNull String word) {
      return commonAdjectives.contains(word.toLowerCase());
   }

   /**
    * Gets a random adjective from the adjective dataset.
    * @return the random adjective.
    */
   public String getRandom() {
      List<String> temp = new ArrayList<>(commonAdjectives);
      return temp.get(ThreadLocalRandom.current().nextInt(temp.size()));
   }
}
