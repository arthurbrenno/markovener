package util.engine;

/**
 * This class is a shortcut to access all the important features of this project
 * without having to instantiate the classes specific objects. It groups all the main features like filtering,
 * tokenizing and mapping, in this order.
 * @since 1.0 SNAPSHOT
 * @author Arthur Brenno
 */
public class Markovener {

   private static final TextFilter filter = new TextFilter();
   private static final Tokenizer tokenizer = new Tokenizer();
   private static final Mapper mapper = new Mapper();

   /**
    * Returns an instance of a TextFilter object. You can use its methods to apply filters to your text.
    * @return a TextFilter object.
    */
   public TextFilter filter() {
      return filter;
   }

   /**
    * Returns an instance of a Tokenizer object. You can use its methods to tokenize your text.
    * @return the Tokenizer object.
    */
   public Tokenizer tokenizer() {
      return tokenizer;
   }

   /**
    * Returns an instance of a Mapper object. You can use its methods to map your tokens based on Markov Chains.
    * @return the Mapper object.
    */
   public Mapper mapper() {
      return mapper;
   }

}