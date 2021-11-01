package com.word_api.services;

import com.word_api.Repositories.LemmasRepository;
import com.word_api.domains.Lemma;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

@Service
@AllArgsConstructor
@NoArgsConstructor
public class WordService {

    @Autowired
    private LemmasRepository lemmasRepository;

    public Lemma getRandomLemmaFromList(long difficultyLevel, String type){
        List<Lemma> wordsByGivenCriteria = getListOfWordsByGivenCriteria(difficultyLevel,type);
        Random random = new Random();
        int randomNumber = random.nextInt(wordsByGivenCriteria.size()+1);
        return wordsByGivenCriteria.get(randomNumber);
    }

    public List<Lemma> getListOfWordsByGivenCriteria(long difficultyLevel, String type) {

        int dbSize = lemmasRepository.findAll().size();
        int wordsPerLevel = dbSize / 10;

        long maxRange = wordsPerLevel * difficultyLevel;
        long minRange = maxRange - wordsPerLevel;

        return lemmasRepository.findWordsByGivenCriteria(minRange, maxRange,type);
    }

    public long calculateBottomRange(long difficulty) {
        return calculateTopRange(difficulty - 500);
    }

    public long calculateTopRange(long difficulty) {
        return (difficulty * 500);
    }
}