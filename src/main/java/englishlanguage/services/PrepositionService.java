package englishlanguage.services;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.io.InputStream;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

public class PrepositionService implements Randomizeable<String> {
   private final Set<String> commonPrepositions;
   private static PrepositionService instance = null;

   private PrepositionService() {
      commonPrepositions = loadPrepositions();
   }

   public static PrepositionService getInstance() {
      if (instance == null) {
         instance = new PrepositionService();
      }
      return instance;
   }

   @Contract(" -> new")
   private @NotNull HashSet<String> loadPrepositions() {
      try (InputStream stream = PrepositionService.class.getResourceAsStream("/parts_of_speech/prepositions/70_prepositions.txt")) {
         assert stream != null;
         return new HashSet<>(Arrays.asList(new String(stream.readAllBytes()).split(System.lineSeparator())));
      } catch (IOException e) {
         throw new RuntimeException(e);
      }
   }

   public Set<String> commonPrepositions() {
      return commonPrepositions;
   }

   public boolean isPreposition(@NotNull String p) {
      return commonPrepositions.contains(p.toLowerCase());
   }

   public String getRandom() {
      List<String> temp = new ArrayList<>(commonPrepositions);
      return temp.get(ThreadLocalRandom.current().nextInt(temp.size()));
   }

}
