package com.android.shehabassistant;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.android.lib_assistant.Ui.Fragment.CallBacks;
import com.android.lib_assistant.Ui.Fragment.MainFragment;
import com.android.lib_assistant.common.SqlHelper.MyDbAdapter;
import com.android.lib_assistant.common.base.BaseActivity;
import com.android.lib_assistant.common.model.PatternQuestionAnswer;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends BaseActivity implements CallBacks {

    private TextView txt;
    private MyDbAdapter myDbAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myDbAdapter = new MyDbAdapter(this);
        myDbAdapter.delete();
        List<PatternQuestionAnswer> patternQuestionAnswers = new ArrayList<>();
            patternQuestionAnswers.add(new PatternQuestionAnswer(1,"hello","hello dear",0));
            patternQuestionAnswers.add(new PatternQuestionAnswer(2,"hey","What do you need",0));
            patternQuestionAnswers.add(new PatternQuestionAnswer(3,"how are you","fine",0));
            patternQuestionAnswers.add(new PatternQuestionAnswer(4,"how old are you","seventeen",0));
            patternQuestionAnswers.add(new PatternQuestionAnswer(5,"what's your name","my name is shehab",0));
            patternQuestionAnswers.add(new PatternQuestionAnswer(6,"hello","hello friend",0));
            patternQuestionAnswers.add(new PatternQuestionAnswer(7,"hello","hello bro",0));
            patternQuestionAnswers.add(new PatternQuestionAnswer(8,"hello","hello my blood",0));
            patternQuestionAnswers.add(new PatternQuestionAnswer(8,"make toast","okay i will make a toast",1));
            patternQuestionAnswers.add(new PatternQuestionAnswer(8,"toast","okay sir",1));
        patternQuestionAnswers.add(new PatternQuestionAnswer(8,"who made you","hello my blood",0));
        patternQuestionAnswers.add(new PatternQuestionAnswer(8,"What's your name","hello my blood",0));
        patternQuestionAnswers.add(new PatternQuestionAnswer(8,"who made you","hello my blood",0));
        patternQuestionAnswers.add(new PatternQuestionAnswer(8,"who made you","hello my blood",0));
        myDbAdapter.insertData(patternQuestionAnswers);
        replaceFragment(R.id.container, MainFragment.newInstance(this),"voice");
        txt = findViewById(R.id.txt_btn);

    }

    @Override
    public void doAction(int key) {
        Log.e("toast", "onListenerToFragment: toaaaaaaaast ");
        if (key==1){
            Toast.makeText(MainActivity.this, "hello", Toast.LENGTH_SHORT).show();
        }
    }
}
