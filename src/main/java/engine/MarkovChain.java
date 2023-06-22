package engine;

import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Pattern;

public class MarkovChain {

    /**
     * Gets a list of ngrams with the following params:
     * @param file to be read
     * @param trash will be replaced with a blank character
     * @param ngramSize (bi/tree/four...)gram
     * @return A list containing all the ngrams in the file
     * @throws IOException interrupted IO operation. File might not be found.
     */
    public List<String> getNgramList(@NotNull File file, @NotNull Pattern trash, final int ngramSize) throws IOException {

        final String BLANK_CHARACTER = "";
        String filteredText = Files
                .readAllLines(
                        file.toPath()
                )
                .toString()
                .replaceAll(
                        trash.pattern(),
                        BLANK_CHARACTER
                ).replaceAll("\\[", BLANK_CHARACTER)
                .replaceAll("]", BLANK_CHARACTER);

        return getNgrams(filteredText, ngramSize);
    }



    /**
     * Gets a ngram list based mainly on a text input
     * @param text to be parsed
     * @param trash to be removed from the text
     * @param ngramSize (2/3/4/5...) gram.
     * @return A list containing all the ngrams of the text
     */
    public List<String> getNgramList(@NotNull String text, @NotNull Pattern trash, final int ngramSize) {
        String filteredText = text.replaceAll(trash.pattern(), "");
        return getNgrams(filteredText, ngramSize);
    }



    /**
     * Gets a list of the ngrams.
     * @param filteredText to be parsed
     * @param ngramSize (2/3/4) gram.
     * @return a list of the ngrams
     */
    @NotNull
    private List<String> getNgrams(@NotNull String filteredText, int ngramSize) {
        List<String> ngramList = new ArrayList<>(filteredText.length() * ngramSize);
        for (int i = 0; i <= filteredText.length() - ngramSize; ++i) {
            ngramList.add(filteredText.substring(i, i + ngramSize));
        }
        return ngramList;
    }



    /**
     * Gets a list of the file words.
     * @param file to be parsed
     * @param trash will be removed/ignored in the text
     * @return a list of the words of the file (in lower-case)
     * @throws IOException if an IO operation fails. File may not been found.
     */
    public List<String> getLowerCaseWordList(@NotNull File file, @NotNull Pattern trash) throws IOException {

        final String BLANK_CHARACTER = "";
        String[] filteredText = Files.readAllLines(
                        Path.of(
                                file.getPath()
                        )
                )
                .toString()
                .replaceAll(trash.pattern(), BLANK_CHARACTER)
                .replaceAll("\\[", BLANK_CHARACTER)
                .replaceAll("]", BLANK_CHARACTER)
                .toLowerCase()
                .split(" ");

        return Arrays.asList(filteredText);
    }



    /**
     * Maps the words to its common previous words
     * @param words to be analysed.
     * @return a map of the words to its previous words.
     */
    public HashMap<String, List<String>> mapWordsBackwards(@NotNull List<String> words) {
        HashMap<String, List<String>> result = new HashMap<>(words.size());
        for (int i = words.size() - 1; i > 0; --i) {
            String currentWord = words.get(i);
            String previousWord = words.get(i - 1);

            result.computeIfAbsent(
                    currentWord,
                    k -> new ArrayList<>()
            );

            result.get(currentWord).add(previousWord);
        }
        return result;
    }



    /**
     * Gets a list of the file words.
     * @param text to be parsed
     * @param trash to be removed from the text
     * @return a list of the words sof the file (in lower-case)
     */
    public List<String> getLowerCaseWordList(@NotNull String text, @NotNull Pattern trash) {
        final String BLANK_CHARACTER = "";
        String[] filteredText = text
                .replaceAll(trash.pattern(), BLANK_CHARACTER)
                .split(" ");
        return Arrays.asList(filteredText);
    }



    /**
     * Maps the ngrams (List) to a HashMap containing next common ngrams
     * @param input ngrams to map
     * @return a HashMap String -> List String
     */
    public HashMap<String, List<String>> mapNgrams(final @NotNull List<String> input) {

        HashMap<String, List<String>> ngramToNgrams = new HashMap<>();

        final int ngramSize = input
                .get(0)
                .length();

        for (int i = 0; i < input.size() - ngramSize; ++i) {
            String key = input.get(i);
            ngramToNgrams.computeIfAbsent(
                    key,
                    k -> new ArrayList<String>()
            );

            int nextNgramIndex = i + ngramSize;   // current ngram (i) + (2 || 3 || 4 || x) = next ngram
            String value = input.get(nextNgramIndex);

//            if (ngramToNgrams
//                    .get(key) => I'll not remove duplicates since I want to predict % without mapping the occurrences
//                    .contains(value)
//            ) { continue; }

            ngramToNgrams
                    .get(key)
                    .add(value);
        }

        return ngramToNgrams;
    }



    /**
     * Gets a random value in a HashMap
     * @param key hashmap key to loop
     * @param ngrams list of ngrams that could be bigrams, trigrams etc.
     * @return a random value of the key
     */
    public String getRandomValue(@NotNull String key, final @NotNull HashMap<String, List<String>> ngrams) {
        final var ngramValues =         ngrams.get(key);
        final int valueAmount =         ngramValues.size();
        final int randomValueIndex =    new SecureRandom().nextInt(valueAmount); //0 <= n <= ngramValues.size()

        return ngramValues.get(randomValueIndex);
    }



    /**
     * Gets a random key in a HashMap.
     * @param ngrams the map to get a random key. Since the order isn't important, we'll use a normal hashmap
     * @return randomKey in the HashMap
     */
    public String getRandomKey(final @NotNull HashMap<String, List<String>> ngrams){
        var keys = new ArrayList<>(ngrams.keySet());
        return keys.get(new SecureRandom().nextInt(keys.size()));
    }



}

