package com.word_api.domains;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
@NoArgsConstructor
public class WordData {

    private String word;
    private ResultData[] results;
    private SyllablesData syllables;
    private PronunciationData pronunciation;
    private Double frequency;

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public SyllablesData getSyllables() {
        return syllables;
    }

    public void setSyllables(SyllablesData syllables) {
        this.syllables = syllables;
    }

    public PronunciationData getPronunciation() {
        return pronunciation;
    }

    public void setPronunciation(PronunciationData pronunciation) {
        this.pronunciation = pronunciation;
    }

    public Double getFrequency() {
        return frequency;
    }

    public void setFrequency(Double frequency) {
        this.frequency = frequency;
    }

    public ResultData[] getResults() {
        return results;
    }

    public void setResults(ResultData[] results) {
        this.results = results;
    }
}
