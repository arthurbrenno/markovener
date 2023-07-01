package enginetest;

import util.engine.Tokenizer;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TokenizerTest {



   @Test
   public void shouldReturnBigrams() {
      String text = "this will be a bigram";
      assertEquals(
              List.of("th", "hi", "is", "s ", " w", "wi", "il", "ll", "l ", " b", "be", "e ", " a", "a ", " b", "bi", "ig", "gr", "ra", "am"),
              new Tokenizer().getNgrams(text, 2)
      );
   }




   @Test
   public void shouldReturnTrigrams() {
      String text = "this will be a trigram";
      assertEquals(
              List.of("thi", "his", "is ", "s w", " wi", "wil", "ill", "ll ", "l b", " be", "be ", "e a", " a ", "a t", " tr", "tri", "rig", "igr", "gra", "ram"),
              new Tokenizer().getNgrams(text, 3)
      );
   }




   @Test
   public void shouldReturnFourgrams() {
      String text = "this will be a fourgram";
      assertEquals(
              List.of("this", "his ", "is w", "s wi", " wil", "will", "ill ", "ll b", "l be", " be ", "be a", "e a ", " a f", "a fo", " fou", "four", "ourg", "urgr", "rgra", "gram"),
              new Tokenizer().getNgrams(text, 4)
      );
   }




   @Test
   public void shouldReturnWords() {
      assertEquals(
              List.of("This", "should", "be", "separated!"),
              new Tokenizer().getWords("This should be separated!")
      );
   }





   @Test
   public void shouldReturnWordsStream() {
      assertEquals(
              List.of("This", "should", "be", "separated!"),
              new Tokenizer().getWords(Stream.of("This should be separated!"))
      );
   }

}
