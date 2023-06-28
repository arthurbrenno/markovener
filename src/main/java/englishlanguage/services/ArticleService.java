package englishlanguage.services;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;

public class ArticleService implements Randomizeable{
   private final Set<String> commonArticles = new HashSet<>(List.of("a", "an", "the"));
   private static ArticleService instance = null;

   private ArticleService() {}

   public Set<String> commonArticles() {
      return commonArticles;
   }

   public boolean isArticle(@NotNull String article) {
      return commonArticles.contains(article.toLowerCase());
   }

   public Set<String> articles() {
      return commonArticles;
   }

   public static ArticleService getInstance() {
      if (instance == null) {
         instance = new ArticleService();
      }
      return instance;
   }

   public String getRandom() {
      List<String> temp = new ArrayList<>(commonArticles);
      return temp.get(ThreadLocalRandom.current().nextInt(temp.size()));
   }
}
