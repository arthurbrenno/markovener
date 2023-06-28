package englishlanguage.services;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.io.InputStream;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

/**
 * This class represents a single VerbService object. The main goal is to load common verbs txt file and see if a word is or not a Verb.
 * @since 1.0 SNAPSHOT
 * @author Arthur Brenno
 */
public class VerbService implements Randomizeable{
   private Set<String> commonVerbs;
   private static VerbService instance = null;

   /**
    * CONSTRUCTOR
    * instantiates an object from the VerbService class after loading the commonVerbs variable based on a verb dataset.
    */
   private VerbService() {
      commonVerbs = loadVerbs();
   }

   /**
    * Gets the only instance of this class (Singleton).
    * @return the single instance of the VerbService class.
    */
   public static VerbService getInstance() {
      if (instance == null) {
         instance = new VerbService();
      }
      return instance;
   }

   /**
    * Load all the common verbs from the english language.
    * @return a Set of the common verbs in the english language.
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
    * @return commonVerbs (Set) instance variable
    */
   public Set<String> commonVerbs() {
      return commonVerbs;
   }

   /**
    * Checks if a word is or not a verb
    * @param word
    * @return true if the word is a verb, false otherwise.
    */
   public boolean isVerb(@NotNull String word) {
      return commonVerbs.contains(word.toLowerCase());
   }

   /**
    * Gets a random unit from a Set of the common verbs of the English language.
    * @return the random verb.
    */
   public String getRandom() {
      List<String> temp = new ArrayList<>(commonVerbs);
      return temp.get(ThreadLocalRandom.current().nextInt(temp.size()));
   }
}
