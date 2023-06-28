package englishlanguage.services;
import org.jetbrains.annotations.NotNull;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;

/**
 * This class represents a single ArticleService object. The main goal is to load common articles txt file and see if a word is or not an Article.
 * @since 1.0 SNAPSHOT
 * @author Arthur Brenno
 */
public class ArticleService implements Randomizeable{
   private final Set<String> commonArticles = new HashSet<>(List.of("a", "an", "the"));
   private static ArticleService instance = null;

   /**
    * CONSTRUCTOR
    * instantiates an ArticleService object.
    */
   private ArticleService() {}

   /**
    * Getter.
    * @return commonArticles Set.
    */
   public Set<String> commonArticles() {
      return commonArticles;
   }

   /**
    * Checks if a word is or not an article.
    * @param word to be analysed
    * @return true if the word is an article, false if not
    */
   public boolean isArticle(@NotNull String word) {
      return commonArticles.contains(word.toLowerCase());
   }

   /**
    * Getter.
    * @return commonArticles HashSet.
    */
   public Set<String> articles() {
      return commonArticles;
   }

   /**
    * Gets the Singleton instance.
    * @return the only instance of this class.
    */
   public static ArticleService getInstance() {
      if (instance == null) {
         instance = new ArticleService();
      }
      return instance;
   }

   /**
    * Gets a random article from the commonArticles Set.
    * @return random article
    */
   public String getRandom() {
      List<String> temp = new ArrayList<>(commonArticles);
      return temp.get(ThreadLocalRandom.current().nextInt(temp.size()));
   }
}
