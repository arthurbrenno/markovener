package engine;

/**
 * This class represents a markovener object. It has, as instance variables, all the tools you need in order to analyse a text, but you can use the features separately too.
 * @since 1.0 SNAPSHOT
 * @author Arthur Brenno
 */
public class Markovener {

   private static final Tokenizer tokenizer = new Tokenizer();
   private static final TextFilter filter = new TextFilter();
   private static final Mapper mapper = new Mapper();

   /**
    * Getter.
    * @return markovener tokenizer object.
    */
   public Tokenizer tokenizer() {
      return tokenizer;
   }

   /**
    * Getter.
    * @return markovener mapper object.
    */
   public Mapper mapper() {
      return mapper;
   }

   /**
    * Getter.
    * @return markovener filter object.
    */
   public TextFilter filter() {
      return filter;
   }

}
