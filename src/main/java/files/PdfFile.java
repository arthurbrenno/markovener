package files;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;

public class PdfFile implements ParseableFile<String> {

   private final Path path;

   public PdfFile(@NotNull Path path) throws IllegalArgumentException{
      if(!path.toString().endsWith(".pdf")) {
         throw new IllegalArgumentException("File is not a pdf.");
      }
      this.path = path;
   }

   public String getContent() throws IOException {
      try(PDDocument pdfDocument = PDDocument.load(new File(path.toUri()))) {
         return new PDFTextStripper().getText(pdfDocument);
      }
   }

   public boolean equals(@NotNull PdfFile pdf) {
      return this.path.equals(pdf.path);
   }
}
