package engine;

import engine.MarkovChain;
import org.junit.Test;
import org.junit.runners.JUnit4;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.regex.Pattern;

import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.assertNotEquals;


public class MarkovTest {


    @Test
    public void stringShouldReturnBigram() {
        MarkovChain m = new MarkovChain();
        assertEquals(
                m.getNgramList("Once Upon!.*-!!! A time", Pattern.compile("[!*.-]"), 2),
                Arrays.asList("On", "nc", "ce", "e ", " U", "Up", "po", "on", "n ", " A", "A ", " t", "ti", "im", "me")
        );
    }

    @Test
    public void stringShouldReturnTrigram() {
        MarkovChain m = new MarkovChain();
        var list = m.getNgramList("Once Upon!.*-!!! A time", Pattern.compile("[!*.-]"), 3);
        assertEquals(list, Arrays.asList("Onc", "nce", "ce ", "e U", " Up", "Upo", "pon", "on ", "n A", " A ", "A t", " ti", "tim", "ime"));
    }

    @Test
    public void fileShouldReturnBigram() throws IOException {
        MarkovChain m = new MarkovChain();
        //Original: Move it! Come on, M'an!
        assertEquals(
                Arrays.asList("Mo", "ov", "ve", "e ", " i", "it", "t ", " C", "Co", "om", "me", "e ", " o", "on", "n " ," M", "Ma", "an"),
                m.getNgramList(
                        new File("src//test//java//engine//testNgramFile.txt"),
                        Pattern.compile("[!,']"),
                        2
                )
        );
    }

    @Test
    public void fileShouldReturnTrigram() throws IOException {
        MarkovChain m = new MarkovChain();
        //Original: Move it! Come on, M'an!
        var list = m.getNgramList(
                new File("src//test//java//engine//testNgramFile.txt"),
                Pattern.compile("[!,']"),
                3
        );
        assertEquals(
                list,
                Arrays.asList(
                        "Mov", "ove", "ve ", "e i", " it", "it ", "t C", " Co", "Com", "ome", "me ", "e o", " on", "on ", "n M", " Ma", "Man"
                )
        );
    }

    @Test
    public void keyShouldBeRandom() throws IOException {
        MarkovChain m = new MarkovChain();
        var list = m.getNgramList(
                new File("src//test//java//engine//testNgramFile.txt"),
                Pattern.compile("[!',1-9-]"),
                2
        );
        var map = m.mapNgrams(list);

        assertNotEquals(
                "On",
                m.getRandomKey(map)
        );
    }

    public static void main(String[] args) throws IOException {
        MarkovChain markovChain = new MarkovChain();
        var list = markovChain.getNgramList(
                new File("C:\\Users\\User\\Desktop\\ShrekMerge.txt"),
                Pattern.compile("[-.=,?'!1-9]"),
                2
        );
        var map = markovChain.mapNgrams(list);
        System.out.println(list);
        System.out.println(map);
    }

}
