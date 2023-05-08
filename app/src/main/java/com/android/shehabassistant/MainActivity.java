package com.android.shehabassistant;

import android.annotation.SuppressLint;
import android.os.Build;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.speech.tts.UtteranceProgressListener;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.lib_assistant.Ui.Fragment.CallBacks;
import com.android.lib_assistant.builder_pattern.ShehabAssistant;
import com.android.lib_assistant.common.HelperStuffs.TextToSpeechListener;
import com.android.lib_assistant.common.model.PatternQuestionAnswer;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements CallBacks {

    /**
     * @auther Shehab Osama.
     */
    ShehabAssistant shehabAssistant;
    TextView textView2;

    @SuppressLint("InvalidWakeLockTag")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        shehabAssistant = ShehabAssistant.Builder.newInstance()
                .with(this)
                .setLanguage("en")
                .setVoiceTone(1.0f)
                .setVoiceSpeed(1.0f)
                .setupTextToSpeech()
                .setOnTextToSpeechListener(textToSpeechListener)
                .build();
        TextView textView1 = findViewById(R.id.tv_noice);
        textView2 = findViewById(R.id.tv_stop);
        textView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                shehabAssistant.speakOut("Hello every one this just a mimic to make sure that every things working as expected");
            }
        });

    }

    @Override
    public void onStop() {
        super.onStop();
        shehabAssistant.stopTTS();
    }

    UtteranceProgressListener utteranceProgressListener = new UtteranceProgressListener() {
        @Override
        public void onStart(String utteranceId) {
            Toast.makeText(MainActivity.this, "hello", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onDone(String utteranceId) {
            Toast.makeText(MainActivity.this, "hello", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onError(String utteranceId) {
            Toast.makeText(MainActivity.this, "hello", Toast.LENGTH_SHORT).show();
        }
    };
    private TextToSpeechListener textToSpeechListener = new TextToSpeechListener() {
        @Override
        public void onStart(String onStart) {
            textView2.post(new Runnable() {
                @Override
                public void run() {
                    textView2.setText("loading");
                }
            });

        }

        @Override
        public void onDone(String onDone) {
            textView2.post(new Runnable() {
                @Override
                public void run() {
                    textView2.setText("onDone");
                }
            });
        }

        @Override
        public void onError(String onError) {
            textView2.post(new Runnable() {
                @Override
                public void run() {
                    textView2.setText("error");
                }
            });
        }
    };


    @Override
    public void doAction(int key) {
        if (key == 1) {
            Toast.makeText(MainActivity.this, "hello", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        shehabAssistant.stopTTS();
        shehabAssistant.shutdownTTS();
    }

    @Override
    protected void onPause() {
        super.onPause();
        shehabAssistant.stopTTS();
    }
}
