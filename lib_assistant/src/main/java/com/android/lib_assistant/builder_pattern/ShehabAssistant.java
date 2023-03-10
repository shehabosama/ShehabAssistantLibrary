package com.android.lib_assistant.builder_pattern;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.widget.Toast;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentTransaction;
import com.android.lib_assistant.R;
import com.android.lib_assistant.Ui.Fragment.CallBacks;
import com.android.lib_assistant.Ui.Fragment.MainFragment;
import com.android.lib_assistant.common.SqlHelper.MyDbAdapter;
import com.android.lib_assistant.common.model.PatternQuestionAnswer;
import java.util.List;
import java.util.Locale;
/**
 * @auther Shehab Osama.
 */
public class ShehabAssistant {

    TextToSpeech textToSpeech;
    private Context context;
    private String mlanguage;
    private MyDbAdapter myDbAdapter;
    private float voiceTone = 0.0f;
    private int res;
    private float voiceSpeed=0.0f;

    public ShehabAssistant(Builder builder) {
        this.textToSpeech = builder.textToSpeech;
        this.context = builder.context;
        this.mlanguage = builder.mlanguage;
        this.myDbAdapter = builder.myDbAdapter;
        this.res = builder.res;
        this.voiceSpeed = builder.voiceSpeed;
        this.voiceTone = builder.voiceTone;
    }

    public void speakOut(String textRekognation) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            textToSpeech.speak(textRekognation, TextToSpeech.QUEUE_FLUSH, null, null);
        }
    }
    public void stopTTS() {
        if (textToSpeech != null) {
            textToSpeech.stop();
        }
    }

    public void shutdownTTS() {
        if (textToSpeech != null) {
            textToSpeech.shutdown();
        }
    }
    public void setVoiceTone(float voiceTone) {
        this.voiceTone = voiceTone;
    }

    public void setVoiceSpeed(float voiceSpeed) {
        this.voiceSpeed = voiceSpeed;

    }
    public void setVoiceType() {
        Intent intent = new Intent();
        intent.setAction(TextToSpeech.Engine.ACTION_INSTALL_TTS_DATA);
        context.startActivity(intent);
    }

   public static class Builder implements CallBacks{
        TextToSpeech textToSpeech;
        private Context context;
        private String mlanguage;
        private MyDbAdapter myDbAdapter;
        private int res;
        private float voiceSpeed=0.0f;
        private float voiceTone = 0.0f;
        public Builder(){
        }
        public static Builder newInstance()
        {
            return new Builder();
        }
        public Builder with(Context context){
            this.context = context;
            return this;
        }
       public Builder setLanguage(String language){
            this.mlanguage = language;
            return this;
        }
       public Builder setupTextToSpeech(){
            textToSpeech = new TextToSpeech(context, new TextToSpeech.OnInitListener() {
                @Override
                public void onInit(int status) {
                    if (status == TextToSpeech.SUCCESS) {
                        textToSpeech.setSpeechRate(voiceSpeed);
                        textToSpeech.setPitch(voiceTone);
                        if (isEnginExists() && !isLanguageExists()) {
                            final String appPackageName = "com.google.android.tts"; // getPackageName() from Context or Activity object
                            try {
                                context.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + appPackageName)));
                            } catch (android.content.ActivityNotFoundException anfe) {
                                context.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=" + appPackageName)));
                            }
                        } else if (!isEnginExists()) {
                            Toast.makeText(context, "This Device not support text to speech", Toast.LENGTH_SHORT).show();
                        }

                    }else {
                        Toast.makeText(context, "This Device not support text to speech2", Toast.LENGTH_SHORT).show();
                    }
                }
            }, "com.google.android.tts");
            return this;
        }
        private boolean isEnginExists() {
            boolean checkEngin = false;
            List<TextToSpeech.EngineInfo> engineInfo = textToSpeech.getEngines();
            for (TextToSpeech.EngineInfo info : engineInfo) {
                if (info.name.equals("com.google.android.tts")) {
                    checkEngin = true;
                    break;
                } else {
                    checkEngin = false;
                }

            }
            return checkEngin;
        }

        private boolean isLanguageExists() {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                res = textToSpeech.setLanguage(Locale.forLanguageTag(mlanguage));
                // checkIfLanguageSupportInCurrentDevice();
                Log.d("TAG", "isLanguageExists: " + res);
                return res != TextToSpeech.LANG_MISSING_DATA && res != TextToSpeech.LANG_NOT_SUPPORTED;
            }
            return false;
        }

        public Builder addFloatActionButton() {
            FragmentTransaction fragmentTransaction = ((FragmentActivity) context).getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.container, MainFragment.newInstance(this), "voice").commit();
            return this;
        }

        public Builder addListOfQuestionAnswerModel(List<PatternQuestionAnswer> models) {
            myDbAdapter = new MyDbAdapter(context);
            myDbAdapter.delete();
            myDbAdapter.insertData(models);
            return this;
        }

        public ShehabAssistant build() {
            return new ShehabAssistant(this);
        }

        public Builder setVoiceTone(float voiceTone) {
            this.voiceTone = voiceTone;
            return this;
        }

        public Builder setVoiceSpeed(float voiceSpeed) {
            this.voiceSpeed = voiceSpeed;
            return this;
        }

        public Builder setVoiceType() {
            Intent intent = new Intent();
            intent.setAction(TextToSpeech.Engine.ACTION_INSTALL_TTS_DATA);
            context.startActivity(intent);
            return this;
        }

        @Override
        public void doAction(int key) {

        }
    }

}
