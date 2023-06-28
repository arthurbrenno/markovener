package englishlanguage;

import englishlanguage.services.ArticleService;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

public class Article implements ReadableWord<String> {
   private final String article;

   private Article(String article) {
      this.article = article;
   }

   @Contract(" -> new")
   public static @NotNull Article createRandom() {
      return new Article(ArticleService.getInstance().getRandom());
   }

   @Contract("_ -> new")
   public static @NotNull Article createArticle(String article) {
      if (ArticleService.getInstance().isArticle(article)) {
         throw new RuntimeException(String.format("%s is not an article.", article));
      }
      return new Article(article);
   }

   @Contract("_ -> new")
   public static @NotNull Article createOrRandom(String article) {
      ArticleService checker = ArticleService.getInstance();
      if (!checker.isArticle(article)) {
         return new Article(checker.getRandom());
      }
      return new Article(article);
   }

   @Override
   public String getContent() {
      return article;
   }

}
