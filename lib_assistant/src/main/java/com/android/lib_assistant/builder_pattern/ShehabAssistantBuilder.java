package com.android.lib_assistant.builder_pattern;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.speech.tts.TextToSpeech;
import android.util.Log;

import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentTransaction;

import com.android.lib_assistant.R;
import com.android.lib_assistant.Ui.Fragment.CallBacks;
import com.android.lib_assistant.Ui.Fragment.MainFragment;
import com.android.lib_assistant.common.SqlHelper.MyDbAdapter;
import com.android.lib_assistant.common.model.PatternQuestionAnswer;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Set;

public class ShehabAssistantBuilder  implements IShehabAssistantBuilder, CallBacks {

    private Context context;
    private String language;
    private boolean checkTTSIsExists;
    private boolean checkIfLanguageNotSupportedInCurrentDevice;

    private MyDbAdapter myDbAdapter;
    private TextToSpeech textToSpeech;
    private int res;

    public Context getContext() {
        return context;
    }

    public void setContext(Context context,Activity activity) {
        this.context = context;
    }

    public boolean isCheckTTSIsExists() {
        return checkTTSIsExists;
    }

    public void setCheckTTSIsExists(boolean checkTTSIsExists) {
        this.checkTTSIsExists = checkTTSIsExists;
    }

    public boolean isCheckIfLanguageNotSupportedInCurrentDevice() {
        return checkIfLanguageNotSupportedInCurrentDevice;
    }

    public void setCheckIfLanguageNotSupportedInCurrentDevice(boolean checkIfLanguageNotSupportedInCurrentDevice) {
        this.checkIfLanguageNotSupportedInCurrentDevice = checkIfLanguageNotSupportedInCurrentDevice;
    }

    public ShehabAssistantBuilder(Context context, final String language) {
        this.context = context;
        this.language = language;
        textToSpeech = new TextToSpeech(context, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if(status == TextToSpeech.SUCCESS){
                    Set<String> a=new HashSet<>();
                    a.add("male");//here you can give male if you want to select male voice.
                    //Voice v=new Voice("en-us-x-sfg#female_2-local",new Locale("en","US"),400,200,true,a);
                   // Voice v= new Voice("en-us-x-sfg#male_2-local",new Locale("en","US"),400,200,true,a);
                  //  textToSpeech.setVoice(v);
                 //   textToSpeech.setSpeechRate(0.8f);

                    // int result = T2S.setLanguage(Locale.US);
                 //   int result = textToSpeech.setVoice(v);
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        res = textToSpeech.setLanguage(Locale.forLanguageTag(language));
                        Set<Locale> avail  = textToSpeech.getAvailableLanguages();
                        for (Locale locale : avail) {
                            Log.e("TAG", "local: ${}"+locale.getLanguage());
                            if (locale.getDisplayVariant() != null) {
                                Log.e("TAG", "  var: " + locale.getVariant());
                            }
                        }
                        Log.e("TAG", "local: ${}"+Arrays.toString(Locale.getAvailableLocales()));
                        List<TextToSpeech.EngineInfo> engineInfo = textToSpeech.getEngines();
                        for (TextToSpeech.EngineInfo info : engineInfo) {
                            Log.e("TAG", "info: "+info);
                        }
                    }



                    // int result =textToSpeech.setLanguage(Locale.US);
                    if(res == TextToSpeech.LANG_MISSING_DATA||res == TextToSpeech.LANG_NOT_SUPPORTED){
                        checkIfLanguageNotSupportedInCurrentDevice = true;
                    }else{
                        checkIfLanguageNotSupportedInCurrentDevice = false;
                    }
                }
            }
        });

    }

    @Override
    public void speakOut(String textRekognation){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            textToSpeech.speak(textRekognation,TextToSpeech.QUEUE_FLUSH,null,null);
        }
    }

    @Override
    public ShehabAssistantBuilder addFloatActionButton() {
        FragmentTransaction fragmentTransaction = ((FragmentActivity) context).getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.container, MainFragment.newInstance(this) , "voice").commit();        return this;
    }

    @Override
    public ShehabAssistantBuilder addListOfQuestionAnswerModel(List<PatternQuestionAnswer> models) {
        myDbAdapter = new MyDbAdapter(context);
        myDbAdapter.delete();
       myDbAdapter.insertData(models);
        return this;
    }

    @Override
    public ShehabAssistantBuilder checkTTSIsExists() {
        return this;
    }

    @Override
    public ShehabAssistantBuilder checkIfLanguageSupportInCurrentDevice() {
        if (checkIfLanguageNotSupportedInCurrentDevice){
            Intent intent = new Intent();
            intent.setAction(TextToSpeech.Engine.ACTION_INSTALL_TTS_DATA);
            context.startActivity(intent);
        }
        return this;
    }

    @Override
    public ShehabAssistantBuilder build() {
        return new ShehabAssistantBuilder(context,language);
    }

    @Override
    public void doAction(int key) {

    }
    public void stopTTS(){
        if(textToSpeech !=null){
            textToSpeech.stop();
            textToSpeech.shutdown();
        }
    }
}
