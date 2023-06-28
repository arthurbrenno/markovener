package files;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class TxtFile implements ParseableFile<String> {

   private final Path path;

   public TxtFile(@NotNull Path path) throws IllegalArgumentException{
      if (!path.endsWith(".txt")) {
         throw new IllegalArgumentException(path + " is not a txt file.");
      }
      this.path = path;
   }

   @Override
   public String getContent() throws IOException {
      return Files.readString(path);
   }

}
