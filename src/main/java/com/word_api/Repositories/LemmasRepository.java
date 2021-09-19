package com.word_api.Repositories;

import com.word_api.domains.Lemma;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface LemmasRepository extends CrudRepository<Lemma, Long> {

    @Query
    List<Lemma> findWordsByGivenCriteria(@Param("MIN_RANGE")long minRange, @Param("MAX_RANGE") long maxRange, @Param("TYPE") char type);

    @Override
    List<Lemma> findAll();

}
