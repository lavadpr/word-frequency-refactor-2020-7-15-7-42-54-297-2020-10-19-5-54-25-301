import java.util.*;
import java.util.stream.Collectors;

public class WordFrequencyGame {

    public static final String WHITESPACE = "\\s+";
    public static final String NEW_LINE = "\n";

    public String getResult(String sentence) {
        if (sentence.split(WHITESPACE).length == 1) {
            return sentence + " 1";
        } else {
            try {
                List<WordInfo> wordInfoList = getWordInfoList(sentence);
                return formatWordInfo(wordInfoList);
            } catch (Exception e) {
                return "Calculate Error";
            }
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
        for(String word : new HashSet<>(words)) {
            int count = Collections.frequency(words, word);
            wordInfoList.add(new WordInfo(word, count));
        }
        wordInfoList.sort((firstWord, secondWord) -> secondWord.getWordCount() - firstWord.getWordCount());
        return wordInfoList;
    }


    private Map<String, List<WordInfo>> getWordInfoMap(List<WordInfo> wordInfoList) {
        Map<String, List<WordInfo>> wordInfoMap = new HashMap<>();
        for (WordInfo wordInfo : wordInfoList) {
            if (!wordInfoMap.containsKey(wordInfo.getValue())) {
                ArrayList wordInfos = new ArrayList<>();
                wordInfos.add(wordInfo);
                wordInfoMap.put(wordInfo.getValue(), wordInfos);
            } else {
                wordInfoMap.get(wordInfo.getValue()).add(wordInfo);
            }
        }
        return wordInfoMap;
    }
}
