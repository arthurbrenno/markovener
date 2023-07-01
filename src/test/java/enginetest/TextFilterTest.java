package enginetest;

import util.engine.TextFilter;
import org.junit.jupiter.api.Test;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TextFilterTest {

   @Test
   public void textShouldBeClean() {
      assertEquals(
              "hi",
              new TextFilter().clearSymbolsAndNumbers("!@!h75612i")
      );
   }

   @Test
   public void streamShouldBeClean() {
      assertEquals(
              Stream.of("hi").collect(Collectors.toList()),
              new TextFilter().clearSymbolsAndNumbers(Stream.of("!@!h75612i")).collect(Collectors.toList())
      );
   }

}
