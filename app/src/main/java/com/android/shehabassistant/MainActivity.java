package com.android.shehabassistant;
import android.annotation.SuppressLint;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.android.lib_assistant.Ui.Fragment.CallBacks;
import com.android.lib_assistant.builder_pattern.ShehabAssistant;
import com.android.lib_assistant.common.model.PatternQuestionAnswer;
import java.util.ArrayList;
import java.util.List;
public class MainActivity extends AppCompatActivity implements CallBacks {

    /**
     * @auther Shehab Osama.
     */
    ShehabAssistant shehabAssistant;
    @SuppressLint("InvalidWakeLockTag")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        List<PatternQuestionAnswer> patternQuestionAnswers = new ArrayList<>();
        patternQuestionAnswers.add(new PatternQuestionAnswer(1, "hello", "hello dear", 0));
        patternQuestionAnswers.add(new PatternQuestionAnswer(2, "hey", "What do you need", 0));
        patternQuestionAnswers.add(new PatternQuestionAnswer(3, "how are you", "fine", 0));
        patternQuestionAnswers.add(new PatternQuestionAnswer(4, "how old are you", "seventeen", 0));
        patternQuestionAnswers.add(new PatternQuestionAnswer(5, "what's your name", "my name is shehab", 0));
        patternQuestionAnswers.add(new PatternQuestionAnswer(6, "hello", "hello friend", 0));
        patternQuestionAnswers.add(new PatternQuestionAnswer(7, "hello", "hello bro", 0));
        patternQuestionAnswers.add(new PatternQuestionAnswer(8, "hello", "hello my blood", 0));
        patternQuestionAnswers.add(new PatternQuestionAnswer(8, "make toast", "okay i will make a toast", 1));
        patternQuestionAnswers.add(new PatternQuestionAnswer(8, "toast", "okay sir", 1));
        patternQuestionAnswers.add(new PatternQuestionAnswer(8, "who made you", "hello my blood", 0));
        patternQuestionAnswers.add(new PatternQuestionAnswer(8, "What's your name", "hello my blood", 0));
        patternQuestionAnswers.add(new PatternQuestionAnswer(8, "who made you", "hello my blood", 0));
        patternQuestionAnswers.add(new PatternQuestionAnswer(8, "who made you", "hello my blood", 0));

       shehabAssistant =  ShehabAssistant.Builder.newInstance()
               .with(this)
               .setLanguage("ar")
               .setVoiceTone(1.0f)
               .setVoiceSpeed(1.0f)
               .setupTextToSpeech()
               .build();

       findViewById(R.id.tv_noice).setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               shehabAssistant.speakOut("مرحبا بكم في هذا الصباح المتواضع جدا اريد ان اعرفكم بنفسي انا المهندس شهاب أسامه فتحي خريج جامعة القاهره كلية علوم الحاسب");
             //  shehabAssistantBuilder.speakOut("hello every one hope you are doing great");
               Toast.makeText(MainActivity.this, Build.MANUFACTURER, Toast.LENGTH_SHORT).show();

           }
       });
        findViewById(R.id.tv_stop).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                shehabAssistant.stopTTS();
            }
        });

    }

    @Override
    public void onStop() {
        super.onStop();
        shehabAssistant.stopTTS();
    }




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
