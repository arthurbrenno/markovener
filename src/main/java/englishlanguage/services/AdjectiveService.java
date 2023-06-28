package englishlanguage.services;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.io.InputStream;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

public class AdjectiveService implements Randomizeable<String>{

   Set<String> adjectives;
   private static AdjectiveService instance;

   private AdjectiveService(){
      adjectives = loadAdjectives();
   }

   @Contract(" -> new")
   private @NotNull HashSet<String> loadAdjectives() {
      try (InputStream stream = AdjectiveService.class.getResourceAsStream("/parts_of_speech/adjectives/28K_adjectives.txt")) {
         assert stream != null;
         return new HashSet<>(Arrays.asList(new String(stream.readAllBytes()).split(System.lineSeparator())));
      } catch (IOException e) {
         throw new RuntimeException(e);
      }
   }

   public static AdjectiveService getInstance() {
      if (instance == null) {
         instance = new AdjectiveService();
      }
      return instance;
   }

   public String getRandom() {
      List<String> temp = new ArrayList<>(adjectives);
      return temp.get(ThreadLocalRandom.current().nextInt(temp.size()));
   }
}
