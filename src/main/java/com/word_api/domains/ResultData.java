package com.word_api.domains;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
@NoArgsConstructor
public class ResultData {

    private String definition;
    private String partOfSpeech;
    private String[] synonyms;
    private String[] hasTypes;
    private String[] typeOf;
    private String[] usageOf;

    public String getDefinition() {
        return definition;
    }

    public void setDefinition(String definition) {
        this.definition = definition;
    }

    public String getPartOfSpeech() {
        return partOfSpeech;
    }

    public void setPartOfSpeech(String partOfSpeech) {
        this.partOfSpeech = partOfSpeech;
    }

    public String[] getSynonyms() {
        return synonyms;
    }

    public void setSynonyms(String[] synonyms) {
        this.synonyms = synonyms;
    }

    public String[] getHasTypes() {
        return hasTypes;
    }

    public void setHasTypes(String[] hasTypes) {
        this.hasTypes = hasTypes;
    }

    public String[] getTypeOf() {
        return typeOf;
    }

    public void setTypeOf(String[] typeOf) {
        this.typeOf = typeOf;
    }

    public String[] getUsageOf() {
        return usageOf;
    }

    public void setUsageOf(String[] usageOf) {
        this.usageOf = usageOf;
    }
}
