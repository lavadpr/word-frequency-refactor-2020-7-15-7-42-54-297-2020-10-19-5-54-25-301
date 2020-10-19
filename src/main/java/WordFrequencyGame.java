import java.util.*;

public class WordFrequencyGame {

    public static final String WHITESPACE = "\\s+";

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
        StringJoiner joiner = new StringJoiner("\n");
        wordInfoList.forEach(wordInfo -> {
            String wordInfoLine = String.format("%s %d", wordInfo.getValue(), wordInfo.getWordCount());
            joiner.add(wordInfoLine);
        });
        return joiner.toString();
    }

    private List<WordInfo> getWordInfoList(String sentence) {
        String[] words = sentence.split(WHITESPACE);

        List<WordInfo> wordInfoList = new ArrayList<>();
        for (String word : words) {
            WordInfo wordInfo = new WordInfo(word, 1);
            wordInfoList.add(wordInfo);
        }
        Map<String, List<WordInfo>> wordInfoMap = getWordInfoMap(wordInfoList);

        List<WordInfo> wordInfos = new ArrayList<>();
        for (Map.Entry<String, List<WordInfo>> entry : wordInfoMap.entrySet()){
            WordInfo wordInfo = new WordInfo(entry.getKey(), entry.getValue().size());
            wordInfos.add(wordInfo);
        }
        wordInfoList = wordInfos;

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
