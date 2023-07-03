package example;

import files.PdfFile;
import markovener.engine.Chain;

import java.nio.file.Path;

public class Example {
   public static void main(String[] args) {
      Chain markovChain = Chain.createByNgrams("Hello! This is markovener!", 2);
      System.out.println(markovChain.toJson());
   }

}
