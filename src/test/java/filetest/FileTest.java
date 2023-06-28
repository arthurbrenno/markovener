package filetest;

import files.PdfFile;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.assertEquals;
public class FileTest {


   @Test
   public void shouldReturnContent() throws IOException {
      assertEquals(
              "this is a normal text",
              new PdfFile(Path.of("src\\test\\java\\filetest\\shorttext.pdf")).getContent()
      );
   }
}
