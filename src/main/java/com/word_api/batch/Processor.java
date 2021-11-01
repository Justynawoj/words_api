package com.word_api.batch;

import com.word_api.domains.Lemma;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class Processor implements ItemProcessor<Lemma, Lemma> {

    private static final Map<String,String> LEMMAS_TYPES =
            new HashMap<>();

    public Processor(){
        LEMMAS_TYPES.put("a","Pronoun");
        LEMMAS_TYPES.put("c","");
        LEMMAS_TYPES.put("d","Determiner");
        LEMMAS_TYPES.put("e","");
        LEMMAS_TYPES.put("i","");
        LEMMAS_TYPES.put("j","");
        LEMMAS_TYPES.put("m","");
        LEMMAS_TYPES.put("n","Noun");
        LEMMAS_TYPES.put("p","");
        LEMMAS_TYPES.put("r","");
        LEMMAS_TYPES.put("t","");
        LEMMAS_TYPES.put("u","Interjection");
        LEMMAS_TYPES.put("v","verb");
        LEMMAS_TYPES.put("x","");
    }


    @Override
    public Lemma process(Lemma item)  {

        String pos = item.getPos();
        String partOfSpeech = LEMMAS_TYPES.get(pos);
        item.setPos(partOfSpeech);

        return item;
    }
}
