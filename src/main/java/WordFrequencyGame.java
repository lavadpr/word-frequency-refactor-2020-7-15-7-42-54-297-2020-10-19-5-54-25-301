import java.util.*;
import java.util.stream.Collectors;

import static java.util.Collections.frequency;

public class WordFrequencyGame {

    private static final String WHITESPACE = "\\s+";
    private static final String NEW_LINE = "\n";
    private static final String CALCULATE_ERROR = "Calculate Error";

    public String getResult(String sentence) {
        try {
            List<WordInfo> wordInfoList = getWordInfoList(sentence);
            return formatWordInfo(wordInfoList);
        } catch (Exception e) {
            return CALCULATE_ERROR;
        }
    }

    private String formatWordInfo(List<WordInfo> wordInfoList) {
        return wordInfoList.stream()
                .map(wordInfo -> String.format("%s %d", wordInfo.getWord(), wordInfo.getWordCount()))
                .collect(Collectors.joining(NEW_LINE));
    }

    private List<WordInfo> getWordInfoList(String sentence) {
        List<String> wordList = Arrays.asList(sentence.split(WHITESPACE));
        Set<String> wordSet = new HashSet<>(wordList);
        return wordSet.stream()
                .map(word -> new WordInfo(word, frequency(wordList, word)))
                .sorted((firstWord, secondWord) -> secondWord.getWordCount() - firstWord.getWordCount())
                .collect(Collectors.toList());
    }
}
