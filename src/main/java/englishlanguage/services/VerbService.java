package englishlanguage.services;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.io.InputStream;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

public class VerbService implements Randomizeable{
   private Set<String> commonVerbs;
   private static VerbService instance = null;
   private VerbService() {
      commonVerbs = loadVerbs();
   }

   public static VerbService getInstance() {
      if (instance == null) {
         instance = new VerbService();
      }
      return instance;
   }

   @Contract(" -> new")
   private @NotNull HashSet<String> loadVerbs() {
      try (InputStream stream = VerbService.class.getResourceAsStream("/parts_of_speech/verbs/31K_verbs.txt")) {
         assert stream != null;
         return new HashSet<>(Arrays.asList(new String(stream.readAllBytes()).split(System.lineSeparator())));
      } catch (IOException e) {
         throw new RuntimeException(e);
      }
   }

   public Set<String> commonVerbs() {
      return commonVerbs;
   }

   public boolean isVerb(@NotNull String v) {
      return commonVerbs.contains(v.toLowerCase());
   }

   public String getRandom() {
      List<String> temp = new ArrayList<>(commonVerbs);
      return temp.get(ThreadLocalRandom.current().nextInt(temp.size()));
   }
}
