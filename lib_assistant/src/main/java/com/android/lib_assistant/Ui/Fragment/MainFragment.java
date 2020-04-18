package com.android.lib_assistant.Ui.Fragment;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.RequiresApi;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.provider.Settings;
import android.speech.RecognitionListener;
import android.speech.RecognizerIntent;
import android.speech.SpeechRecognizer;
import android.speech.tts.TextToSpeech;
import android.speech.tts.Voice;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.android.lib_assistant.R;
import com.android.lib_assistant.common.Patterns.PatternsContract;
import com.android.lib_assistant.common.Patterns.PresenterPatterns;
import com.android.lib_assistant.common.base.BaseFragment;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Set;

/**
 * A simple {@link Fragment} subclass.
 */
public class MainFragment extends BaseFragment implements TextToSpeech.OnInitListener, PatternsContract.View {

    private static final int REQUEST_MICROPHONE = 1011;
    private PresenterPatterns presenter;
    private List<String> patterns;
    private SpeechRecognizer mSpeechRecognizer;
    private Intent mSpeechRecognizerIntent;
    private TextToSpeech textToSpeech;
    private String textRekognation;
    private  ArrayList<String> matches;
    private FloatingActionButton floatingActionButton;

    public MainFragment() {
        // Required empty public constructor
    }

    public static MainFragment newInstance(){
        return new MainFragment();
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_main, container, false);
        initializeViews(view);
        setListeners();
        return view;
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void initializeViews(View v) {

        floatingActionButton = v.findViewById(R.id.btn_floating);
        patterns = new ArrayList<>();
        textToSpeech = new TextToSpeech(getActivity(),this, "com.google.android.tts");

        Set<String> a=new HashSet<>();
        a.add("male");//here you can give male if you want to select male voice.
        Voice voice=new Voice("en-us-x-sfg#male_2-local",new Locale("en","US"),400,200,true,a);
        textToSpeech.setVoice(voice);
        textToSpeech.setSpeechRate(0.8f);

        presenter = new PresenterPatterns(this);

        checkPermission();
        mSpeechRecognizer = SpeechRecognizer.createSpeechRecognizer(getActivity());
        mSpeechRecognizerIntent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        mSpeechRecognizerIntent.putExtra(RecognizerIntent.EXTRA_CALLING_PACKAGE, getClass().getPackage().getName());
        mSpeechRecognizerIntent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        //  mSpeechRecognizerIntent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, "ar-EG");
        // mSpeechRecognizerIntent.putExtra("android.speech.extra.EXTRA_ADDITIONAL_LANGUAGES", new String[]{"en-US"});
        mSpeechRecognizerIntent.putExtra("android.speech.extra.EXTRA_ADDITIONAL_LANGUAGES",
                Locale.getDefault());
        mSpeechRecognizer.setRecognitionListener(mSpeechRecognizerListener);
    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void setListeners() {
        floatingActionButton.setOnTouchListener(floatingActionButtonListener);
    }


    private RecognitionListener mSpeechRecognizerListener = new RecognitionListener() {
        @Override
        public void onReadyForSpeech(Bundle bundle) {

        }

        @Override
        public void onBeginningOfSpeech() {

        }

        @Override
        public void onRmsChanged(float v) {

        }

        @Override
        public void onBufferReceived(byte[] bytes) {

        }

        @Override
        public void onEndOfSpeech() {

        }

        @Override
        public void onError(int i) {


        }
        @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
        @Override
        public void onResults(Bundle bundle) {
            //getting all the matches
            matches = bundle
                    .getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION);

            //displaying the first match
            if (matches != null){
                Log.e("speshhh", "onResults: "+matches.get(0) );
                search_array(matches.get(0).toLowerCase(),presenter.pat_en);
                speakOut();
            }
        }

        @Override
        public void onPartialResults(Bundle bundle) {

        }

        @Override
        public void onEvent(int i, Bundle bundle) {

        }
    };
    private View.OnTouchListener floatingActionButtonListener = new View.OnTouchListener() {
        @Override
        public boolean onTouch(View view, MotionEvent motionEvent) {
            switch (motionEvent.getAction()) {
                case MotionEvent.ACTION_UP:
                case MotionEvent.ACTION_CANCEL:
                    Snackbar.make(view, "processing your order.....", Snackbar.LENGTH_SHORT)
                            .setAction("Action", null).show();
                    mSpeechRecognizer.stopListening();
                    break;
                case MotionEvent.ACTION_DOWN:
                    //finger is on the button
                    Snackbar.make(view, "Recording now.....", Snackbar.LENGTH_INDEFINITE)
                            .setAction("Action", null).show();
                    mSpeechRecognizer.startListening(mSpeechRecognizerIntent);
                    return true;
            }
            return false;
        }
    };
    private void checkPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {

                if (ContextCompat.checkSelfPermission(getActivity(),
                        Manifest.permission.RECORD_AUDIO) != PackageManager.PERMISSION_GRANTED) {

                    ActivityCompat.requestPermissions(getActivity(),
                            new String[]{Manifest.permission.RECORD_AUDIO},
                            REQUEST_MICROPHONE);


            }
        }
    }
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onInit(int status) {
        if(status == TextToSpeech.SUCCESS){



            Set<String> a=new HashSet<>();
            a.add("male");//here you can give male if you want to select male voice.
            //Voice v=new Voice("en-us-x-sfg#female_2-local",new Locale("en","US"),400,200,true,a);
            Voice v=new Voice("en-us-x-sfg#male_2-local",new Locale("en","US"),400,200,true,a);
            textToSpeech.setVoice(v);
            textToSpeech.setSpeechRate(0.8f);

            // int result = T2S.setLanguage(Locale.US);
            int result = textToSpeech.setVoice(v);



            // int result =textToSpeech.setLanguage(Locale.US);
            if(result == TextToSpeech.LANG_MISSING_DATA||result == TextToSpeech.LANG_NOT_SUPPORTED){

            }else{
                speakOut();
            }
        }
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        if(textToSpeech !=null){
            textToSpeech.stop();
            textToSpeech.shutdown();
        }
    }
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public void speakOut(){
        textToSpeech.speak(textRekognation,TextToSpeech.QUEUE_FLUSH,null,null);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public  void search_array(String txt, String[] pat) {
        int M = 0;
        int N = 0;
        for (int i = 0; i < pat.length; i++) {
            M = pat[i].length();
            N = txt.length();
            for (int _i = 0; _i <= N - M; _i++) {

                int j;
            /* For current index i, check for pattern
              match */
                for (j = 0; j < M; j++)
                    if (txt.charAt(_i + j) != pat[i].charAt(j)) {
                        textRekognation = "i don't understand you";
                        break;
                    }
                if (j == M) { // if pat[0...M-1] = txt[i, i+1, ...i+M-1]
                    // System.out.println("Pattern found at index " + _i);
                    System.out.println(pat[i]);
                    String sb = "";
                    patterns.add(pat[i]);

                }else{
                    textRekognation = "i don't understand you";
                }
            }
        }
        for (int i =0 ;i<patterns.size();i++){

            Log.e("tesxt", "search_array: "+patterns.toString() );
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                textRekognation = presenter.PatternsFunc(patterns,i,getActivity());
            }
            patterns.clear();
        }
    }

    @Override
    public void onListenerToFragment(String key) {

    }
}
