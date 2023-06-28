package englishlanguage.services;

import englishlanguage.Noun;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.io.InputStream;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

/**
 * This class represents a single NounService object. The main goal is to load common nouns txt file and see if a word is or not a Noun.
 * @since 1.0 SNAPSHOT
 * @author Arthur Brenno
 */
public class NounService implements Randomizeable<String> {

   private final Set<String> commonNouns;
   private static NounService instance = null;

   /**
    * CONSTRUCTOR
    * loads the common nouns Set based on a huge noun dataset
    */
   private NounService() {
      commonNouns = loadNouns();
   }

   /**
    * Gets the Singleton instance.
    * @return the only instance of this class.
    */
   public static NounService getInstance() {
      if (instance == null) {
         instance = new NounService();
      }
      return instance;
   }

   /**
    * Loads the nouns dataset.
    * @return a HashSet (unordered list) of the common english nouns based on a huge dataset.
    */
   @Contract(" -> new")
   private @NotNull HashSet<String> loadNouns() {
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
   public Set<String> commonNouns() {
      return commonNouns;
   }

   /**
    * Checks if a word is or not a noun.
    * @return true if it is a noun, false if it's not.
    */
   public boolean isNoun(@NotNull String n) {
      return commonNouns.contains(n.toLowerCase());
   }

   /**
    * Gets a random noun based on a huge dataset.
    * @return random noun.
    */
   public String getRandom() {
      List<String> temp = new ArrayList<>(commonNouns);
      return temp.get(ThreadLocalRandom.current().nextInt(temp.size()));
   }
}
