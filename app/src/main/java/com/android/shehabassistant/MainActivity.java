package com.android.shehabassistant;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.PackageManager;
import android.content.res.AssetFileDescriptor;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.media.AudioFormat;
import android.media.AudioRecord;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.os.Bundle;
import android.os.Handler;
import android.os.PowerManager;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.MainThread;
import androidx.annotation.UiThread;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.android.lib_assistant.Ui.Fragment.CallBacks;
import com.android.lib_assistant.Ui.Fragment.MainFragment;
import com.android.lib_assistant.builder_pattern.IShehabAssistantBuilder;
import com.android.lib_assistant.builder_pattern.ShehabAssistantBuilder;
import com.android.lib_assistant.common.SqlHelper.MyDbAdapter;
import com.android.lib_assistant.common.base.BaseActivity;
import com.android.lib_assistant.common.model.PatternQuestionAnswer;

import org.w3c.dom.Text;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;


public class MainActivity extends AppCompatActivity implements CallBacks {


    //private MyDbAdapter myDbAdapter;
    /* constants */
    private static final int POLL_INTERVAL = 300;

    /**
     * running state
     **/
    private boolean mRunning = false;

    /**
     * config state
     **/
    private int mThreshold;

    int RECORD_AUDIO = 0;
    //private PowerManager.WakeLock mWakeLock;

    //  private Handler mHandler = new Handler();

    /* References to view elements */
    // private TextView mStatusView,tv_noice;

    /* sound data source */
    // private DetectNoise mSensor;
    //  ProgressBar bar;
    /****************** Define runnable thread again and again detect noise *********/

//    private Runnable mSleepTask = new Runnable() {
//        public void run() {
//            //Log.i("Noise", "runnable mSleepTask");
//            start();
//        }
//    };
//
//    // Create runnable thread to Monitor Voice
//    private Runnable mPollTask = new Runnable() {
//        public void run() {
//            double amp = mSensor.getAmplitude();
//            //Log.i("Noise", "runnable mPollTask");
//            updateDisplay("Monitoring Voice...", amp);
//
//            if ((amp > mThreshold)) {
//                callForHelp(amp);
//                //Log.i("Noise", "==== onCreate ===");
//            }
//            // Runnable(mPollTask) will again execute after POLL_INTERVAL
//            mHandler.postDelayed(mPollTask, POLL_INTERVAL);
//        }
//    };

    /**
     * Called when the activity is first created.
     */
    @SuppressLint("InvalidWakeLockTag")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        myDbAdapter = new MyDbAdapter(this);
//        myDbAdapter.delete();
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
//        myDbAdapter.insertData(patternQuestionAnswers);
      //  replaceFragment(R.id.container, MainFragment.newInstance(this),"voice");

       final IShehabAssistantBuilder shehabAssistantBuilder =  new ShehabAssistantBuilder(this,
               "en")
               .checkIfLanguageSupportInCurrentDevice()
                //.addListOfQuestionAnswerModel(patternQuestionAnswers)
               .addFloatActionButton()
                .build();

       findViewById(R.id.tv_noice).setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               shehabAssistantBuilder.speakOut("مرحبا بكم في هذا الصباح المتواضع جدا اريد ان اعرفكم بنفسي انا المهندس شهاب اسامه فتحي خريج جامعه القاهره كلية علوم الحاسب");
           }
       });
        findViewById(R.id.tv_stop).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                shehabAssistantBuilder.stopTTS();
            }
        });

//        mStatusView = (TextView) findViewById(R.id.txt_btn);
//        tv_noice=(TextView)findViewById(R.id.tv_noice);
//        bar=(ProgressBar)findViewById(R.id.progressBar1);
//        // Used to record voice
//        mSensor = new DetectNoise();
//        PowerManager pm = (PowerManager) getSystemService(Context.POWER_SERVICE);
//        mWakeLock = pm.newWakeLock(PowerManager.SCREEN_DIM_WAKE_LOCK, "NoiseAlert");
    }

    @Override
    public void onResume() {
        super.onResume();
        //Log.i("Noise", "==== onResume ===");

//        initializeApplicationConstants();
//        if (!mRunning) {
//            mRunning = true;
//            start();
//        }
    }

    @Override
    public void onStop() {
        super.onStop();
        // Log.i("Noise", "==== onStop ===");
        //Stop noise monitoring
        // stop();
    }

    private void start() {

//        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.RECORD_AUDIO) != PackageManager.PERMISSION_GRANTED) {
//
//            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.RECORD_AUDIO},
//                    RECORD_AUDIO);
//        }

        //Log.i("Noise", "==== start ===");
//        mSensor.start();
//        if (!mWakeLock.isHeld()) {
//            mWakeLock.acquire();
//        }
//        //Noise monitoring start
//        // Runnable(mPollTask) will execute after POLL_INTERVAL
//        mHandler.postDelayed(mPollTask, POLL_INTERVAL);
    }
//    private void stop() {
//        Log.d("Noise", "==== Stop Noise Monitoring===");
//        if (mWakeLock.isHeld()) {
//            mWakeLock.release();
//        }
//        mHandler.removeCallbacks(mSleepTask);
//        mHandler.removeCallbacks(mPollTask);
//        mSensor.stop();
//        bar.setProgress(0);
//        updateDisplay("stopped...", 0.0);
//        mRunning = false;
//    }

    private void initializeApplicationConstants() {
        // Set Noise Threshold
        mThreshold = 8;

    }

//    private void updateDisplay(String status, double signalEMA) {
//        mStatusView.setText(status);
//        bar.setProgress((int)signalEMA);
//        Log.d("SONUND", String.valueOf(signalEMA));
//        tv_noice.setText(signalEMA+"dB");
//    }

//    private void callForHelp(double signalEMA) {
//
//        //stop();
//        // Show alert when noise thersold crossed
//        Toast.makeText(getApplicationContext(), "Noise Thersold Crossed, do here your stuff.",
//                Toast.LENGTH_LONG).show();
//        Log.d("SONUND", String.valueOf(signalEMA));
//        tv_noice.setText(signalEMA+"dB");
//    }


    @Override
    public void doAction(int key) {
        Log.e("toast", "onListenerToFragment: toaaaaaaaast ");
        if (key == 1) {
            Toast.makeText(MainActivity.this, "hello", Toast.LENGTH_SHORT).show();
        }
    }
}
