package com.example.android.miwok;

import android.annotation.TargetApi;
import android.media.MediaPlayer;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class PhrasesActivity extends AppCompatActivity {

    MediaPlayer mediaPlayer;
    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phrases);

        final ArrayList<Word> words = new ArrayList<Word>();
        words.add(new Word("Where are you going?", "minto wukus",
                R.raw.phrase_where_are_you_going ));
        words.add(new Word("What is your name?", "tinne oyaasene",
                R.raw.phrase_what_is_your_name));
        words.add(new Word("My name is...", "ayaaset...",
                R.raw.phrase_my_name_is));
        words.add(new Word("How are you feeling?", "michekses",
                R.raw.phrase_how_are_you_feeling));
        words.add(new Word("I am feeling good.", "kuchi achit",
                R.raw.phrase_im_feeling_good));
        words.add(new Word("Are you coming?", "eenes'aa",
                R.raw.phrase_are_you_coming));
        words.add(new Word("Yes, I am coming", "hee'eenem",
                R.raw.phrase_yes_im_coming));
        words.add(new Word("I'm coming", "eenem",
                R.raw.phrase_im_coming));
        words.add(new Word("Let's go.", "yoowutis",
                R.raw.phrase_lets_go));
        words.add(new Word("Come here.", "enninem",
                R.raw.phrase_come_here));

        WordAdapter adapter  = new WordAdapter(this, words, R.color.category_phrases);
        final ListView listView = (ListView) findViewById(R.id.root_view);
        assert listView != null;
        listView.setAdapter(adapter);


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                releaseMediaPlayer();
                mediaPlayer = MediaPlayer.create(PhrasesActivity.this,
                        words.get(position).getmAudioID());
                mediaPlayer.start();

                mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mp) {
                        releaseMediaPlayer();
                    }
                });
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