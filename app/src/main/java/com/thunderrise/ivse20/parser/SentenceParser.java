package com.thunderrise.ivse20.parser;

import com.thunderrise.ivse20.model.RelatedWord;

import java.util.List;

/**
 * Created by sergejkozin on 11/24/17.
 */

public interface SentenceParser {
    List<RelatedWord> requestValidation(String stringForParsing);
}
