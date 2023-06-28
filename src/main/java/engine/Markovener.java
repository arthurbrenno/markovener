package engine;


public class Markovener {

   private static final Tokenizer tokenizer = new Tokenizer();
   private static final TextFilter filter = new TextFilter();
   private static final Mapper mapper = new Mapper();

   public Tokenizer tokenizer() {
      return tokenizer;
   }

   public Mapper mapper() {
      return mapper;
   }

   public TextFilter filter() {
      return filter;
   }
   
}
