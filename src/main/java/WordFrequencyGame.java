import java.util.*;
import java.util.stream.Collectors;

public class WordFrequencyGame {

    private static final String WHITESPACE = "\\s+";
    private static final String NEW_LINE = "\n";

    public String getResult(String sentence) {
        try {
            List<WordInfo> wordInfoList = getWordInfoList(sentence);
            return formatWordInfo(wordInfoList);
        } catch (Exception e) {
            return "Calculate Error";
        }
    }

    private String formatWordInfo(List<WordInfo> wordInfoList) {
        return wordInfoList.stream()
                .map(wordInfo -> String.format("%s %d", wordInfo.getValue(), wordInfo.getWordCount()))
                .collect(Collectors.joining(NEW_LINE));
    }

    private List<WordInfo> getWordInfoList(String sentence) {
        List<String> words = Arrays.asList(sentence.split(WHITESPACE));
        List<WordInfo> wordInfoList = new ArrayList<>();
        for (String word : new HashSet<>(words)) {
            int count = Collections.frequency(words, word);
            wordInfoList.add(new WordInfo(word, count));
        }
        wordInfoList.sort((firstWord, secondWord) -> secondWord.getWordCount() - firstWord.getWordCount());
        return wordInfoList;
    }
}
