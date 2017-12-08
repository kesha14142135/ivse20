package com.thunderrise.ivse20.parser;

import com.thunderrise.ivse20.model.Action;
import com.thunderrise.ivse20.model.RelatedKey;
import com.thunderrise.ivse20.model.RelatedWord;

import java.util.List;

/**
 * Created by sergejkozin on 11/24/17.
 */

public interface TypeParser {

    List<RelatedWord> findType(List<RelatedWord> words);

}
