package enginetest;

import engine.TextFilter;
import org.junit.jupiter.api.Test;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TextFilterTest {

   @Test
   public void textShouldBeClean() {
      assertEquals(
              "hi",
              new TextFilter().cleanSymbols("!@!h75612i")
      );
   }

   @Test
   public void streamShouldBeClean() {
      assertEquals(
              Stream.of("hi").collect(Collectors.toList()),
              new TextFilter().cleanSymbols(Stream.of("!@!h75612i")).collect(Collectors.toList())
      );
   }

}
