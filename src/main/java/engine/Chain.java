package engine;
import com.google.gson.Gson;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Unmodifiable;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

/**
 * The Chain class provides short and elegant ways to store and get the token list and Markov-Chain data
 * structures of a text.
 * It has basic getters for each instance variable and enforces immutability for data safety.
 * This class is NOT thread-safe and should not be used in parallel.
 * @since Alpha 1.0
 */
public final class Chain implements AutoCloseable{

   private final List<String> tokens;
   private final Map<String, List<String>> markovChain;
   private Map<String, Map<String, Integer>> occurrences;
   private final String jsonRepresentation;


   /**
    * CONSTRUCTOR (ngrams). It expects to receive a text that has already passed through a filtering process.
    * @param text that will be tokenized in a 'ngram way.'
    * @param order ngrams order.
    */
   Chain(String text, int order) {
      tokens = tokenizeNgrams(text, order);
      markovChain = mapNgrams(tokens);
      occurrences = mapOccurrences(markovChain);
      jsonRepresentation = new Gson().toJson(markovChain);
   }

   /**
    * CONSTRUCTOR (words). It expects to receive a text that has already passed through a filtering process.
    * @param text that will be tokenized by words.
    */
   Chain(String text) {
      tokens = tokenizeWords(text);
      markovChain = mapWords(tokens);
      occurrences = mapOccurrences(markovChain);
      jsonRepresentation = new Gson().toJson(markovChain);
   }

   /**
    * Getter
    * @return token list - instance variable.
    */
   public List<String> tokens() {
      return tokens;
   }

   /**
    * Getter
    * @return markov-chain map - instance variable.
    */
   public Map<String, List<String>> getMarkovChain() {
      return markovChain;
   }

   /**
    * Getter
    * @return getJsonRepresentation - instance variable.
    */
   public String getJsonRepresentation() {
      return jsonRepresentation;
   }

   /**
    * Saves the getJsonRepresentation String instance variable into a new json file.
    * @param directory to create a new json file
    * @throws IOException if some IOException occurs. Your path might be invalid or the directory is not accessible by
    * the application.
    */
   public void save(@NotNull Path directory) throws IOException {
      final Path finalPath = Path.of(new StringBuilder(directory.toString()).append("\\output.json").toString());
      boolean success = new File(finalPath.toUri()).createNewFile();
      if (success) {
         Files.write(finalPath, getJsonRepresentation().getBytes());
      }
   }

   /**
    * Clears all the resources that the Chain object is holding. Which are the list and the map.
    * @throws Exception e.
    */
   @Override
   public void close() throws Exception {
      tokens.clear();
      markovChain.clear();
      occurrences.clear();
   }

   /**
    * Tokenizes text into ngrams based in the order.
    * @param text to be tokenized
    * @param order to split ngrams
    * @return a List of the ngrams.
    */
   private @NotNull List<String> tokenizeNgrams(@NotNull String text, int order) {
      List<String> result = new ArrayList<>();
      for (int i = 0; i <= text.length() - order; ++i) {
         result.add(text.substring(i, i + order));
      }
      return result;
   }

   /**
    * Tokenizes a text into words.
    * @param text to be tokenized.
    * @return a List of the text words.
    */
   private @NotNull @Unmodifiable List<String> tokenizeWords(@NotNull String text) {
      return List.of(text.split(" "));
   }

   /**
    * Maps the tokens (ngrams) into its next associated ngrams based on the order.
    * @param tokens to be mapped.
    * @return a HashMap of the ngrams.
    * @apiNote the mapped ngrams are taken by current-ngram + order.
    */
   private @NotNull Map<String, List<String>> mapNgrams(@NotNull final List<String> tokens) {
      final HashMap<String, List<String>> chain = new HashMap<>(tokens.size());
      final int order = tokens.get(0).length();
      for (int i = 0; i < tokens.size() - order; ++i) {
         String key = tokens.get(i);
         String value = tokens.get(i + order);
         chain.computeIfAbsent(
                 key,
                 k -> new ArrayList<>()
         );
         chain.get(key).add(value);
      }
      return chain;
   }

   /**
    * Maps the tokens (words) into its next associated ngrams based by words.
    * @param tokens to be mapped.
    * @return a HashMap of the words.
    * @apiNote the mapped words are taken by current-word + 1.
    */
   private @NotNull Map<String, List<String>> mapWords(@NotNull final List<String> tokens) {
      final HashMap<String, List<String>> chain = new HashMap<>(tokens.size());
      for (int i = 0; i < tokens.size() - 1; ++i) {
         String key = tokens.get(i);
         String value = tokens.get(i + 1);
         chain.computeIfAbsent(
                 key,
                 k -> new ArrayList<>()
         );
         chain.get(key).add(value);
      }
      return chain;
   }

   private @NotNull Map<String, Map<String, Integer>> mapOccurrences(@NotNull Map<String, List<String>> elementToNextElements) {
      final Map<String, Map<String, Integer>> occurrences = new HashMap<>();
      for (Map.Entry<String, List<String>> entry : elementToNextElements.entrySet()) {
         Map<String, Integer> nextElements = new HashMap<>();
         for (String nextElement : entry.getValue()) {
            nextElements.computeIfAbsent(nextElement, k -> 0);
            nextElements.put(nextElement, nextElements.get(nextElement) + 1);
         }
         occurrences.put(entry.getKey(), nextElements);
      }
      return occurrences;
   }

}
