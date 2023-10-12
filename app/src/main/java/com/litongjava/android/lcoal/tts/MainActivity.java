package com.litongjava.android.lcoal.tts;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.litongjava.android.view.inject.annotation.FindViewById;
import com.litongjava.android.view.inject.annotation.FindViewByIdLayout;
import com.litongjava.android.view.inject.annotation.OnClick;
import com.litongjava.android.view.inject.utils.ViewInjectUtils;

import java.util.Locale;

@FindViewByIdLayout(R.layout.activity_main)
public class MainActivity extends AppCompatActivity {

  private Context baseContext;
  private TextToSpeech textToSpeech;

  @FindViewById(R.id.inputText)
  public EditText inputText;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    ViewInjectUtils.injectActivity(this, this);
    init();
  }

  private void init() {
    baseContext = this.getBaseContext();

    TextToSpeech.OnInitListener listener = new TextToSpeech.OnInitListener() {
      @Override
      public void onInit(int status) {
        if (status == TextToSpeech.SUCCESS) {
          Toast.makeText(baseContext, "init success", Toast.LENGTH_SHORT).show();
        }
      }
    };

    textToSpeech = new TextToSpeech(baseContext, listener);

  }

  @OnClick(R.id.play)
  public void onClick_play(View v) {
    int result = textToSpeech.setLanguage(Locale.ENGLISH);
    if (result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED) {
      Toast.makeText(baseContext, "LANG_MISSING_DATA  or LANG_NOT_SUPPORTED !", Toast.LENGTH_SHORT).show();
    } else {
      textToSpeech.speak(inputText.getText().toString(), TextToSpeech.QUEUE_FLUSH, null);
    }
  }

  @OnClick(R.id.playFiveTimes)
  public void onClick_playFiveTimes(View v) {
    int result = textToSpeech.setLanguage(Locale.ENGLISH);
    if (result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED) {
      Toast.makeText(baseContext, "LANG_MISSING_DATA  or LANG_NOT_SUPPORTED !", Toast.LENGTH_SHORT).show();
    } else {
      textToSpeech.speak(inputText.getText().toString(), TextToSpeech.QUEUE_ADD, null);
      textToSpeech.speak(inputText.getText().toString(), TextToSpeech.QUEUE_ADD, null);
      textToSpeech.speak(inputText.getText().toString(), TextToSpeech.QUEUE_ADD, null);
      textToSpeech.speak(inputText.getText().toString(), TextToSpeech.QUEUE_ADD, null);
      textToSpeech.speak(inputText.getText().toString(), TextToSpeech.QUEUE_ADD, null);
    }
  }

  @OnClick(R.id.stop)
  public void onClick_stop(View v) {
    textToSpeech.stop();
  }
}