package files;
import org.jetbrains.annotations.NotNull;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * The main goal of this class is to get the content inside a txt file, in addition to represent a txtFile, to make
 * it explicit.
 * @since 1.0 SNAPSHOT
 * @author Arthur Brenno
 */
public class TxtFile implements ParseableFile<String> {

   private final Path path;

   /**
    * Constructor.
    * @param path to the txt file.
    * @throws IllegalArgumentException if the file is not a txt.
    */
   public TxtFile(@NotNull Path path) throws IllegalArgumentException{
      if (!path.endsWith(".txt")) {
         throw new IllegalArgumentException(path + " is not a txt file.");
      }
      this.path = path;
   }

   /**
    * This method returns the content of the txt.
    * @return String with the content of the txt.
    * @throws IOException if the file is not found.
    */
   @Override
   public String getContent() throws IOException {
      return Files.readString(path);
   }

}
