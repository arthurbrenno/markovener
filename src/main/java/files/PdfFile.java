package files;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
/**
 * This class represents a PdfFile object. The main goal is to get the content inside the Pdf.
 * @since 1.0 SNAPSHOT
 * @author Arthur Brenno
 */
public class PdfFile implements ParseableFile<String> {

   private final Path path;

   /**
    * CONSTRUCTOR
    * @param path to the pdf file.
    * @throws IllegalArgumentException if the file is not a pdf.
    */
   public PdfFile(@NotNull Path path) throws IllegalArgumentException{
      if(!path.toString().endsWith(".pdf")) {
         throw new IllegalArgumentException("File is not a pdf.");
      }
      this.path = path;
   }

   /**
    * This method returns the content of the pdf.
    * @return String with the content of the pdf.
    * @throws IOException if the file is not found.
    */
   public String getContent() throws IOException {
      try(PDDocument pdfDocument = PDDocument.load(new File(path.toUri()))) {
         return new PDFTextStripper().getText(pdfDocument);
      }
   }

}
