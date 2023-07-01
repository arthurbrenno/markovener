package englishlanguage.services;
import org.jetbrains.annotations.NotNull;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Provides methods to classify words.
 * It is necessary because there's a need to check if a word
 * is or not an article, in addition to having a whole articles'
 * dataset powering up the text generation at its maximum.
 * It was chosen to be static because there is no need to have another instance of this class in an application.
 * @since 1.0 SNAPSHOT
 * @author Arthur Brenno
 */
public final class ArticleService {
   private static final Set<String> commonArticles = new HashSet<>(List.of("a", "an", "the"));

   /**
    * This class is not instantiable
    */
   private ArticleService() {}

   /**
    * Getter.
    * @return commonArticles Set.
    */
   public static Set<String> commonArticles() {
      return commonArticles;
   }

   /**
    * Checks if a word is an article.
    * @param word to be checked (will be converted to lower-case).
    * @return true if the word is an article, false if not.
    */
   public static boolean isArticle(@NotNull String word) {
      return commonArticles.contains(word.toLowerCase());
   }

   /**
    * Gets a random article from the commonArticles Set.
    * @return random article.
    */
   public static String getRandom() {
      List<String> temp = new ArrayList<>(commonArticles);
      return temp.get(ThreadLocalRandom.current().nextInt(temp.size()));
   }

}
