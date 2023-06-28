package englishlanguage.services;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.io.InputStream;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

/**
 * This class represents a single AdjectiveService object. The main goal is to load common adjectives txt file and see if a word is or not an Adjective.
 * @since 1.0 SNAPSHOT
 * @author Arthur Brenno
 */
public class AdjectiveService implements Randomizeable<String>{

   Set<String> commonAdjectives;
   private static AdjectiveService instance;

   /**
    * CONSTRUCTOR.
    * initializes the adjectives list by loading a huge dataset of english common articles
    */
   private AdjectiveService(){
      commonAdjectives = loadAdjectives();
   }

   /**
    * Loads the adjectives dataset based on a default txt file.
    * @return a HashSet (unordered list) of the common english adjectives based on a huge dataset.
    */
   @Contract(" -> new")
   private @NotNull HashSet<String> loadAdjectives() {
      try (InputStream stream = AdjectiveService.class.getResourceAsStream("/parts_of_speech/adjectives/28K_adjectives.txt")) {
         assert stream != null;
         return new HashSet<>(Arrays.asList(new String(stream.readAllBytes()).split(System.lineSeparator())));
      } catch (IOException e) {
         throw new RuntimeException(e);
      }
   }

   /**
    * Getter.
    * @return commonAdjectives HashSet.
    */
   public Set<String> adjectives() {
      return commonAdjectives;
   }

   /**
    * Gets Singleton instance method.
    * @return the only instance of this class.
    */
   public static AdjectiveService getInstance() {
      if (instance == null) {
         instance = new AdjectiveService();
      }
      return instance;
   }

   /**
    * Checks if a word is or not an adjective.
    * @param word the word to be checked.
    * @return true if it is a noun, false if it's not.
    */
   public boolean isAdjective(@NotNull String word) {
      return commonAdjectives.contains(word);
   }

   /**
    * Gets a random adjective from the adjective dataset.
    * @return random adjective - String
    */
   public String getRandom() {
      List<String> temp = new ArrayList<>(commonAdjectives);
      return temp.get(ThreadLocalRandom.current().nextInt(temp.size()));
   }
}
