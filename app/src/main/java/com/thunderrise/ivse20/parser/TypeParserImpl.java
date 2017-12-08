package com.thunderrise.ivse20.parser;

import com.thunderrise.ivse20.model.Action;
import com.thunderrise.ivse20.model.RelatedKey;
import com.thunderrise.ivse20.model.RelatedWord;
import com.thunderrise.ivse20.model.Sentence;
import com.thunderrise.ivse20.model.Type;
import com.thunderrise.ivse20.model.TypeSentence;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

/**
 * Created by sergejkozin on 11/22/17.
 */

public class TypeParserImpl implements TypeParser {

    @Override
    public List<RelatedWord> findType(List<RelatedWord> words) {
        words = findAndAddUnifyingSymbol(words);
        words = findAndAddAction(words, testFindAction());
        List<Sentence> sentences = splittingСomplexSentencesIntoSimple(words);
        return null;
    }

    //Todo not hard code in the method
    private List<RelatedWord> findAndAddUnifyingSymbol(List<RelatedWord> words) {
        String unifyingSymbol = "и";
        for (RelatedWord word : words) {
            if (!word.isTypeSet()
                    && word.getWord().length() == unifyingSymbol.length()) {
                word.setType(Type.UNIFYNG_SYMBOL);
            }
        }
        return words;
    }

    private List<Sentence> splittingСomplexSentencesIntoSimple(List<RelatedWord> words) {
        List<Sentence> sentences = new ArrayList<>();
        List<Integer> actionsPosition = findActionsInSentence(words);
        if (actionsPosition.size() == 0) {
            sentences.add(new Sentence(TypeSentence.OFFER_WITHOUT_ACTION, words));
        } else if (actionsPosition.size() == 1) {
            sentences.add(new Sentence(TypeSentence.SENTENCE_WITH_ONE_ACTION, words));
        } else if (actionsPosition.size() > 1) {
            sentences = complexSentencePartitioning(words, actionsPosition);
            for (Sentence sentence : sentences) {
                sentence = definitionTypeSentence(sentence);
            }
        }
        return sentences;
    }

    private List<Integer> findActionsInSentence(List<RelatedWord> words) {
        List<Integer> actionPosition = new ArrayList<>();
        for (int i = 0; i < words.size(); i++) {
            if (words.get(i).getType() == Type.ACTION) {
                actionPosition.add(i);
            }
        }
        return actionPosition;
    }

    private List<Sentence> complexSentencePartitioning(List<RelatedWord> words, List<Integer> actions) {
        List<Sentence> sentences = new ArrayList<>();
        int position = 0;
        for (int i = 0; i < actions.size(); i++) {
            if (i + 1 < actions.size()) {
                if (actions.get(i + 1) - actions.get(i) > 1 && words.get(actions.get(i) + 1).getType() != Type.UNIFYNG_SYMBOL) {
                    sentences.add(new Sentence(TypeSentence.UNKNOWN_ACTION, words.subList(position, actions.get(i + 1))));
                    position = actions.get(i + 1);
                }
            } else if (words.size() - position > 1) {
                sentences.add(new Sentence(TypeSentence.UNKNOWN_ACTION, words.subList(position, words.size())));
            }
        }
        return sentences;
    }

    private Sentence definitionTypeSentence(Sentence sentence) {
        List<Integer> actionsPosition = findActionsInSentence(sentence.getWords());
        if (actionsPosition.size() == 1) {
            sentence.setTypeSentence(TypeSentence.SENTENCE_WITH_ONE_ACTION);
        } else if (actionsPosition.size() > 1) {

            sentence.setWords(deleteUnifyingSimbol(sentence.getWords()));
            for (int i = 0; i < sentence.getWords().size() - 1; i++) {
                if (sentence.getWords().get(i).getType() == Type.ACTION
                        && sentence.getWords().get(i + 1).getType() == Type.ACTION) {
                    sentence = deleteElementInList(sentence, actionAssociation(
                            sentence.getWords().get(i),
                            sentence.getWords().get(i + 1)));
                    i--;
                }
            }
        }
        return sentence;
    }

    private Sentence deleteElementInList(Sentence sentence, RelatedWord word) {
        for (ListIterator<RelatedWord> iterator = sentence.getWords().listIterator(); iterator.hasNext(); ) {
            RelatedWord w = iterator.next();
            if (word != null && word.equals(w)) {
                iterator.remove();
            }
        }
        return sentence;
    }

    //Todo word processing in the method
    private RelatedWord actionAssociation(RelatedWord firstAction, RelatedWord secondAction) {
        return firstAction;
    }

    private List<RelatedWord> deleteUnifyingSimbol(List<RelatedWord> words) {
        List<RelatedWord> newWords = new ArrayList<>();
        for (RelatedWord word : words) {
            if (word.getType() != Type.UNIFYNG_SYMBOL)
                newWords.add(word);
        }
        return newWords;
    }

    private List<RelatedWord> searchType(List<RelatedWord> words, List<RelatedKey> keys) {
        return null;
    }


    private List<RelatedWord> findAndAddAction(List<RelatedWord> words, List<Action> actions) {
        List<RelatedWord> newWords = new ArrayList<>();
        for (RelatedWord word : words) {
            if (!word.isTypeSet()) {
                word.findAndAddIdAction(actions);
            }
            newWords.add(word);
        }
        return newWords;
    }

    private List<Action> testFindAction() {
        List<Action> actions = new ArrayList<>();
        actions.add(new Action(1, true, "купить", true));
        actions.add(new Action(1, false, "купи", true));
        actions.add(new Action(1, false, "купишь", true));
        actions.add(new Action(2, true, "заказ", true));
        actions.add(new Action(2, false, "закажи", true));
        actions.add(new Action(2, false, "заказуй", true));
        actions.add(new Action(2, true, "вызов", true));
        actions.add(new Action(2, false, "вызваьть", true));
        actions.add(new Action(2, false, "вызови", true));
        return actions;
    }
}
