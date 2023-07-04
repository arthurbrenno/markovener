import engine.Chain;
import util.TextFilter;

import java.io.IOException;

public class UsageExample {

   public static void main(String[] args) throws IOException {

      final String text = TextFilter.clearSymbolsAndNumbers("1 - Markovener is soooo cool!");
      Chain chain = Chain.createByNgrams(text, 2);

   }

}
