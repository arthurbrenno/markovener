package englishlanguage.services;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

/**
 * This class represents a single PrepositionService object. It is necessary because there's a need to check if a word
 * is or not a preposition, in addition to having a whole prepositions dataset to power up the text generation at its
 * maximum.
 * It was chosen to be a Singleton because there is no need to have another instance of this class in an application.
 * @since 1.0 SNAPSHOT
 * @author Arthur Brenno
 */
public class PrepositionService implements Randomizeable<String> {

   private final Set<String> commonPrepositions;
   private static PrepositionService instance = null;

   /**
    * Constructor.
    * The constructor of this object is responsible for loading all the common prepositions of the english language
    * into a HashSet "commonPrepositions."
    */
   private PrepositionService() {
      commonPrepositions = loadCommonPrepositions();
   }

   /**
    * Gets the only instance of this class.
    * @return PrepositionService instance.
    */
   public static PrepositionService getInstance() {
      if (instance == null) {
         instance = new PrepositionService();
      }
      return instance;
   }

   /**
    * This method is responsible for loading all the common prepositions of the english language and putting them into
    * a HashSet.
    * @return a HashSet of the common english prepositions based on a dataset located at "resources."
    */
   @Contract(" -> new")
   private @NotNull HashSet<String> loadCommonPrepositions() {
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
   public Set<String> commonPrepositions() {
      return commonPrepositions;
   }

   /**
    * Checks if a word is a preposition.
    * @param word to be analyzed.
    * @return true if the word is a preposition found in a database, false if not.
    */
   public boolean isPreposition(@NotNull String word) {
      return commonPrepositions.contains(word.toLowerCase());
   }

   /**
    * Gets a random preposition from the commonPrepositions HashSet.
    * @return a random preposition.
    */
   public String getRandom() {
      List<String> temp = new ArrayList<>(commonPrepositions);
      return temp.get(ThreadLocalRandom.current().nextInt(temp.size()));
   }

}
