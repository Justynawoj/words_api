package com.word_api.controllers;

import com.word_api.clients.WordsApiClient;
import com.word_api.domains.Lemma;
import com.word_api.domains.WordData;
import com.word_api.services.WordService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/v1/words")
@RestController
@AllArgsConstructor
public class WordController {

    private WordsApiClient client;
    private WordService service;



    @GetMapping("{word}")
    public WordData getSpecificWord(@PathVariable String word){
        return client.getSpecificWord(word);
    }

    @GetMapping("/listByCriteria")
    public List<Lemma> getListOfLemmasByGivenCriteria(@RequestParam long difficultyLevel, @RequestParam char type){
        return service.getListOfWordsByGivenCriteria(difficultyLevel,type);
    }

    @GetMapping("/random")
    public Lemma getRandomWord(@RequestParam long difficultyLevel, @RequestParam char type){
        return service.getRandomLemmaFromList(difficultyLevel,type);
    }

    @GetMapping("/random/definition")
    public WordData getRandomWordAndDefinition(@RequestParam long difficultyLevel, @RequestParam char type){
        return this.getSpecificWord(service.getRandomLemmaFromList(difficultyLevel,type).getLemma());
    }
}
