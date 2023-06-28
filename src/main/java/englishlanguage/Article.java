package englishlanguage;

import englishlanguage.services.ArticleService;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

/**
 * This class represents a single Article object. In order to instantiate, the word must be an article.
 * @since 1.0 SNAPSHOT
 * @author Arthur Brenno
 */
public class Article implements ReadableWord<String> {
   private final String article;

   /**
    * CONSTRUCTOR
    * @param article that will be set as instance variable.
    */
   private Article(String article) {
      this.article = article;
   }

   /**
    * Factory. Creates a random Article
    * @return An Article object. This object contains a random article as "article" instance variable
    */
   @Contract(" -> new")
   public static @NotNull Article createRandom() {
      return new Article(ArticleService.getInstance().getRandom());
   }

   /**
    * Factory. Creates an Article
    * @return An Article object. This object will contain an article if the article provided is really an article.
    * @throws RuntimeException the argument is not an article.
    */
   @Contract("_ -> new")
   public static @NotNull Article createArticle(String article) {
      if (ArticleService.getInstance().isArticle(article)) {
         throw new RuntimeException(String.format("%s is not an article.", article));
      }
      return new Article(article);
   }

   /**
    * Factory. Creates an article by the input or a random article.
    * @param article to be verified.
    * @return Article instance.
    */
   @Contract("_ -> new")
   public static @NotNull Article createOrRandom(String article) {
      ArticleService checker = ArticleService.getInstance();
      if (!checker.isArticle(article)) {
         return new Article(checker.getRandom());
      }
      return new Article(article);
   }

   /**
    * Getter.
    * @return the article that the object represents.
    */
   @Override
   public String getContent() {
      return article;
   }

}
