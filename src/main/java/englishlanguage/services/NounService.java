package englishlanguage.services;

import englishlanguage.Noun;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.io.InputStream;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

public class NounService implements Randomizeable<String> {

   private final Set<String> commonNouns;
   private static NounService instance = null;

   private NounService() {
      commonNouns = loadNouns();
   }

   public static NounService getInstance() {
      if (instance == null) {
         instance = new NounService();
      }
      return instance;
   }

   @Contract(" -> new")
   private @NotNull HashSet<String> loadNouns() {
      try (InputStream stream = NounService.class.getResourceAsStream("/parts_of_speech/nouns/91K_nouns.txt")) {
         return new HashSet<>(Arrays.asList(new String(stream.readAllBytes()).split(System.lineSeparator())));
      } catch (IOException e) {
         throw new RuntimeException(e);
      }
   }

   public Set<String> commonNouns() {
      return commonNouns;
   }

   public boolean isNoun(@NotNull String n) {
      return commonNouns.contains(n.toLowerCase());
   }

   public String getRandom() {
      List<String> temp = new ArrayList<>(commonNouns);
      return temp.get(ThreadLocalRandom.current().nextInt(temp.size()));
   }
}
