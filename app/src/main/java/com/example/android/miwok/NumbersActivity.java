package com.example.android.miwok;

import android.annotation.TargetApi;
import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
//There are some changes made in this activity regarding Audio search about AudioManager and
//AudioFocus etc
//Also Implement this to other three Activity for practice
public class NumbersActivity extends AppCompatActivity {
    MediaPlayer mediaPlayer;
    ListView listView;
    private AudioManager audioManager;
    AudioManager.OnAudioFocusChangeListener audioFocusChangeListener = new AudioManager.OnAudioFocusChangeListener() {
        @Override
        public void onAudioFocusChange(int focusChange) {
            if (focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT ||
                    focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK) {
                mediaPlayer.pause();
                mediaPlayer.seekTo(0);
            } else if (focusChange == AudioManager.AUDIOFOCUS_GAIN) {
                mediaPlayer.start();
            } else if (focusChange == AudioManager.AUDIOFOCUS_LOSS) {
                releaseMediaPlayer();
            }
        }
    };
    @TargetApi(Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_numbers);

        audioManager = (AudioManager) this.getSystemService(Context.AUDIO_SERVICE);

        final ArrayList<Word> words = new ArrayList<>();
        words.add(new Word("one", "lutti",
                getDrawable(R.drawable.number_one), R.raw.number_one));
        words.add(new Word("two", "otiiko",
                getDrawable(R.drawable.number_two), R.raw.number_two));
        words.add(new Word("three", "tolookosu",
                getDrawable(R.drawable.number_three), R.raw.number_three));
        words.add(new Word("four", "oyyisa",
                getDrawable(R.drawable.number_four), R.raw.number_four));
        words.add(new Word("five", "massokka",
                getDrawable(R.drawable.number_five), R.raw.number_five));
        words.add(new Word("six", "temmokka",
                getDrawable(R.drawable.number_six), R.raw.number_six));
        words.add(new Word("seven", "kenekaku",
                getDrawable(R.drawable.number_seven), R.raw.number_seven));
        words.add(new Word("eight", "kawinta",
                getDrawable(R.drawable.number_eight), R.raw.number_eight));
        words.add(new Word("nine", "wo'e",
                getDrawable(R.drawable.number_nine), R.raw.number_nine));
        words.add(new Word("ten", "na'aacha",
                getDrawable(R.drawable.number_ten), R.raw.number_ten));

        final WordAdapter adapter  = new WordAdapter(this, words, R.color.category_numbers);
        listView = (ListView) findViewById(R.id.root_view);
        assert listView != null;
        listView.setAdapter(adapter);



        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                releaseMediaPlayer();

                int result = audioManager.requestAudioFocus(audioFocusChangeListener,
                        AudioManager.STREAM_MUSIC,
                        AudioManager.AUDIOFOCUS_GAIN_TRANSIENT);

                if (result == AudioManager.AUDIOFOCUS_REQUEST_GRANTED) {
                    //audioManager.registerMediaButtonEventReceiver(RemoteControlReceiver);


                    mediaPlayer = MediaPlayer.create(NumbersActivity.this, words.get(position).getmAudioID());
                    mediaPlayer.start();

                    mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                        @Override
                        public void onCompletion(MediaPlayer mp) {
                            releaseMediaPlayer();
                            audioManager.abandonAudioFocus(audioFocusChangeListener);
                        }
                    });


                }
            }
        });
    }

    public void releaseMediaPlayer() {
        if(mediaPlayer!=null) {
            mediaPlayer.release();
            mediaPlayer = null;
        }
    }
}