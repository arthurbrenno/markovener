import markovener.engine.Markovener;

import java.io.IOException;
import java.nio.file.Path;

public class UsageExample {
   public static void main(String[] args) throws IOException {
      Markovener.createByNgrams("Markovener is cool!", 2).save(Path.of("src","test","outputtest"));
   }
}
