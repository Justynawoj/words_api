package com.word_api.batch;

import com.word_api.Repositories.LemmasRepository;
import com.word_api.domains.Lemma;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Slf4j
public class BDWriter implements ItemWriter<Lemma> {

    @Autowired
    private LemmasRepository lemmasRepository;


    @Override
    public void write(List<? extends Lemma> items) {
        log.info("Data saved for Lemmas: "+items);
        lemmasRepository.saveAll(items);
    }
}
