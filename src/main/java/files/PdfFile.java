package files;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.jetbrains.annotations.NotNull;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;

/**
 * The main goal of this class is to get the content inside the Pdf, in addition to represent a PdfFile, to make
 * it more explicit.
 * @since 1.0 SNAPSHOT
 * @author Arthur Brenno
 */
public class PdfFile implements ParseableFile<String> {

   private final Path path;

   /**
    * Constructor.
    * @param path to the pdf file.
    * @throws IllegalArgumentException if the file is not a pdf.
    */
   public PdfFile(@NotNull Path path) throws IllegalArgumentException {
      if(!path.toString().endsWith(".pdf")) {
         throw new IllegalArgumentException("File is not a pdf.");
      }
      this.path = path;
   }

   /**
    * This method returns the content of the pdf.
    * @return String with all the content of the PdfFile instance.
    * @throws IOException if the file is not found.
    */
   public String getContent() throws IOException {
      try(PDDocument pdfDocument = PDDocument.load(new File(path.toUri()))) {
         return new PDFTextStripper().getText(pdfDocument);
      }
   }

}
