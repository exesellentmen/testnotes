package example.app.entities;

import java.util.Map;

public class Stats {
    Map<String, Long> wordCounts;

    public Map<String, Long> getWordCounts() {
        return wordCounts;
    }

    public Stats setWordCounts(Map<String, Long> wordCounts) {
        this.wordCounts = wordCounts;
        return this;
    }
}

