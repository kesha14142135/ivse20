package com.thunderrise.ivse20.parser;

import com.thunderrise.ivse20.model.RelatedWord;
import com.thunderrise.ivse20.model.Type;
import com.thunderrise.ivse20.model.Word;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

/**
 * Created by sergejkozin on 11/20/17.
 * requestValidation(String stringForParser) breaks a string into a list while removing obscene words deleteUnprintableWords.
 * And delete unprintable words deleteUnprintableWords(List<RelatedWord> words, List<Word> unprintables)
 * And as it unites prepositions with words that is "for 18:00".
 */

public class SentenceParserImpl implements SentenceParser {

    private final String WORD_SEPARATOR = " ";

    public SentenceParserImpl() {
    }

    @Override
    public List<RelatedWord> requestValidation(String stringForParsing) {
        List<RelatedWord> wordList;
        wordList = wordsSplitting(stringForParsing);
        wordList = deleteUnprintableWords(wordList, testUnprintableWords());
        wordList = findPrepositions(wordList, testPrepositions());
        wordList = searchAndConnectionOfTime(wordList);
        return wordList;
    }

    private List<RelatedWord> wordsSplitting(String stringForParsing) {
        List<RelatedWord> wordList = new ArrayList<>();
        stringForParsing = stringForParsing.toLowerCase();
        String[] words = stringForParsing.split(WORD_SEPARATOR);
        for (String word : words) {
            wordList.add(new RelatedWord(word));
        }
        return wordList;
    }

    private List<RelatedWord> deleteUnprintableWords(List<RelatedWord> words, List<Word> unprintable) {
        List<RelatedWord> newWords = new ArrayList<>();
        for (RelatedWord word : words) {
            if (!word.isTypeSet()) {
                word.findAndAddIdWord(unprintable, Type.UNPRINTABLE);
            }
            newWords.add(word);
        }
        return newWords;
    }


    private List<RelatedWord> findPrepositions(List<RelatedWord> words, List<Word> excuses) {
        List<RelatedWord> newWords = new ArrayList<>();
        for (RelatedWord word : words) {
            if (!word.isTypeSet()) {
                word.findAndAddIdWord(excuses, Type.PREPOSITION);
            }
            newWords.add(word);
        }
        return newWords;
    }

    //Find time format 00:00, 0:00 - через 00:00, через 0:00
    //Find time format 5 часов
    //Todo two regular after three

    private List<RelatedWord> searchAndConnectionOfTime(List<RelatedWord> words) {
        List<RelatedWord> newWords = new ArrayList<>();
        List<Pattern> patterns = new ArrayList<>();
        patterns.add(Pattern.compile("^(([0,23])|(2[0-3])):[0-5][0-9]$"));
        patterns.add(Pattern.compile("^(([0,1][0-9])|(2[0-3])):[0-5][0-9]$"));
        patterns.add(Pattern.compile("^([0-9]):[0-5][0-9]$"));
        for (int i = 0; i < words.size(); i++) {
            if (!words.get(i).isTypeSet()) {
                if (words.get(i).findAndAddIdWordUsingPattern(patterns, Type.TIME)) {
                    if (i != 0 && words.get(i - 1).getType() == Type.PREPOSITION)
                        newWords.get(i - 1).setType(Type.PREPOSITION_TIME);
                } else if (words.get(i).findAndAddIdWord(testKeywordsOfTheTime(), Type.TIME_MARKER)) {
                    words.get(i).setType(defineTimeMarker(words.get(i)));
                    if (i > 0) {
                        newWords.set(i - 1, findTime(
                                newWords.get(i - 1),
                                defineTimeMarker(words.get(i).getType())
                        ));
                        if ((i - 1) != 0) {
                            if (words.get(i - 2).getType() == Type.PREPOSITION)
                                newWords.get(i - 2).setType(Type.PREPOSITION_TIME);
                        }
                    }
                }
            }
            newWords.add(words.get(i));
        }
        return newWords;
    }

    private RelatedWord findTime(RelatedWord word, Type type) {
        RelatedWord newWord = word;
        Pattern pattern = Pattern.compile("^[0-9]+$");
        if (pattern.matcher(word.getWord()).matches()) {
            newWord.setType(type);
            newWord.setKeyId(Integer.parseInt(word.getWord()));
        } else {
            if (word.findAndAddIdWord(testTime(), type))
                newWord = word;
        }
        return newWord;
    }

    private Type defineTimeMarker(RelatedWord word) {
        Type type = Type.TIME_MARKER;
        if (word.getType() == Type.TIME_MARKER)
            switch (word.getKeyId()) {
                case 0: {
                    type = Type.SECOND_WORD;
                    break;
                }
                case 1: {
                    type = Type.MINUTE_WORD;
                    break;
                }
                case 2: {
                    type = Type.HOUR_WORD;
                    break;
                }
            }
        return type;
    }

    private Type defineTimeMarker(Type type) {
        switch (type) {
            case SECOND_WORD: {
                type = Type.SECOND;
                break;
            }
            case MINUTE_WORD: {
                type = Type.MINUTE;
                break;
            }
            case HOUR_WORD: {
                type = Type.HOUR;
                break;
            }
        }
        return type;
    }

    private List<Word> testPrepositions() {
        List<Word> words = new ArrayList<>();
        words.add(new Word(0, "в"));
        words.add(new Word(1, "на"));
        words.add(new Word(2, "через"));
        words.add(new Word(3, "у"));
        words.add(new Word(4, "к"));
        words.add(new Word(5, "над"));
        words.add(new Word(6, "спустя"));
        return words;
    }

    private List<Word> testUnprintableWords() {
        List<Word> words = new ArrayList<>();
        words.add(new Word(0, "сука"));
        words.add(new Word(1, "шлюха"));
        words.add(new Word(2, "пидорас"));
        words.add(new Word(3, "долбоеб"));
        words.add(new Word(4, "гандон"));
        words.add(new Word(5, "пиздюк"));
        words.add(new Word(5, "лох"));

        return words;
    }

    private List<Word> testKeywordsOfTheTime() {
        List<Word> words = new ArrayList<>();
        words.add(new Word(0, "секунд"));
        words.add(new Word(1, "минут"));
        words.add(new Word(1, "минута"));
        words.add(new Word(2, "час"));
        words.add(new Word(2, "часа"));
        words.add(new Word(2, "часов"));
        words.add(new Word(2, "часав"));
        words.add(new Word(2, "часам"));
        words.add(new Word(3, "год"));
        words.add(new Word(3, "годов"));
        return words;
    }

    private List<Word> testTime() {
        List<Word> words = new ArrayList<>();
        words.add(new Word(1, "одна"));
        words.add(new Word(1, "один"));
        words.add(new Word(2, "две"));
        words.add(new Word(2, "два"));
        words.add(new Word(2, "двух"));
        words.add(new Word(3, "три"));
        words.add(new Word(3, "три"));
        words.add(new Word(4, "четыри"));
        words.add(new Word(10, "десять"));
        return words;
    }
}
