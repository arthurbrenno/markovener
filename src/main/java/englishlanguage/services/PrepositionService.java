package englishlanguage.services;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.io.InputStream;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

/**
 * This class represents a single PrepositionService object. The main goal is to load common prepositions txt file and see if a word is or not a Preposition.
 * @since 1.0 SNAPSHOT
 * @author Arthur Brenno
 */
public class PrepositionService implements Randomizeable<String> {
   private final Set<String> commonPrepositions;
   private static PrepositionService instance = null;

   /**
    * CONSTRUCTOR
    * loads the commonPreposition Set from the Singleton PrepositionService object.
    */
   private PrepositionService() {
      commonPrepositions = loadPrepositions();
   }

   /**
    * Singleton getter. Gets the only instance of this class.
    * @return the only instance of this class.
    */
   public static PrepositionService getInstance() {
      if (instance == null) {
         instance = new PrepositionService();
      }
      return instance;
   }

   /**
    * Loads the prepositions Set based on a default txt file dataset.
    * @return HashSet of the prepositions file.
    */
   @Contract(" -> new")
   private @NotNull HashSet<String> loadPrepositions() {
      try (InputStream stream = PrepositionService.class.getResourceAsStream("/parts_of_speech/prepositions/70_prepositions.txt")) {
         assert stream != null;
         return new HashSet<>(Arrays.asList(new String(stream.readAllBytes()).split(System.lineSeparator())));
      } catch (IOException e) {
         throw new RuntimeException(e);
      }
   }

   /**
    * Getter.
    * @return the commonPrepositions Set instance variable.
    */
   public Set<String> commonPrepositions() {
      return commonPrepositions;
   }

   /**
    * Checks if a word is or not a preposition.
    * @param word to be analysed.
    * @return true if the word is a preposition found in a database, false if not.
    */
   public boolean isPreposition(@NotNull String word) {
      return commonPrepositions.contains(word.toLowerCase());
   }

   /**
    * Gets a random unit from commonPrepositions Set.
    * @return a random preposition.
    */
   public String getRandom() {
      List<String> temp = new ArrayList<>(commonPrepositions);
      return temp.get(ThreadLocalRandom.current().nextInt(temp.size()));
   }

}
