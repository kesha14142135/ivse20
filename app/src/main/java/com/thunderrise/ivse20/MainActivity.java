package com.thunderrise.ivse20;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.thunderrise.ivse20.model.RelatedWord;
import com.thunderrise.ivse20.parser.SentenceParser;
import com.thunderrise.ivse20.parser.SentenceParserImpl;
import com.thunderrise.ivse20.parser.TypeParser;
import com.thunderrise.ivse20.parser.TypeParserImpl;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SentenceParser parser = new SentenceParserImpl();
        List<RelatedWord> words = parser.requestValidation("закажи купи нож и закажи и купи такси на 10 часов и купи томатов");
        TypeParser type = new TypeParserImpl();
        type.findType(words);
    }
}
