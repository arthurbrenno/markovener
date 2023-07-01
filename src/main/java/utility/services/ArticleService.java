package utility.services;
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
 * It was chosen to be a Singleton because there is no need to have another instance of this class in an application.
 * @since 1.0 SNAPSHOT
 * @author Arthur Brenno
 */
public class ArticleService implements Randomizeable{
   private final Set<String> commonArticles;
   private static ArticleService instance;

   /**
    * Constructor.
    * Instantiates an ArticleService object. There is no need to load a dataset because the common articles are just
    * a few.
    */
   private ArticleService() {
      commonArticles = new HashSet<>(List.of("a", "an", "the"));
   }

   /**
    * Getter.
    * @return commonArticles Set.
    */
   public Set<String> commonArticles() {
      return commonArticles;
   }

   /**
    * Checks if a word is an article.
    * @param word to be checked (will be converted to lower-case).
    * @return true if the word is an article, false if not.
    */
   public boolean isArticle(@NotNull String word) {
      return commonArticles.contains(word.toLowerCase());
   }

   /**
    * Getter.
    * @return commonArticles instance variable (HashSet).
    */
   public Set<String> articles() {
      return commonArticles;
   }

   /**
    * Gets the only instance of this class.
    * @return ArticleService instance.
    */
   public static ArticleService getInstance() {
      if (instance == null) {
         instance = new ArticleService();
      }
      return instance;
   }

   /**
    * Gets a random article from the commonArticles Set.
    * @return random article.
    */
   public String getRandom() {
      List<String> temp = new ArrayList<>(commonArticles);
      return temp.get(ThreadLocalRandom.current().nextInt(temp.size()));
   }
}
