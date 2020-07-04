package com.example.android.miwok;

import android.annotation.TargetApi;
import android.media.MediaPlayer;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class ColorsActivity extends AppCompatActivity {
    MediaPlayer mediaPlayer;
    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_colors);

        final ArrayList<Word> arrayList = new ArrayList<>();
        arrayList.add(new Word("red", "wetetti",
                getDrawable(R.drawable.color_red), R.raw.color_red));
        arrayList.add(new Word("green", "chokokki",
                getDrawable(R.drawable.color_green), R.raw.color_green));
        arrayList.add(new Word("brown", "takaakki",
                getDrawable(R.drawable.color_brown), R.raw.color_brown));
        arrayList.add(new Word("gray", "topoppi",
                getDrawable(R.drawable.color_gray), R.raw.color_gray));
        arrayList.add(new Word("black", "kululli",
                getDrawable(R.drawable.color_black), R.raw.color_black));
        arrayList.add(new Word("white", "kelelli",
                getDrawable(R.drawable.color_white), R.raw.color_white));
        arrayList.add(new Word("mustard yellow", "chiwiite",
                getDrawable(R.drawable.color_mustard_yellow), R.raw.color_mustard_yellow));
        arrayList.add(new Word("dusty yellow", "topiise",
                getDrawable(R.drawable.color_dusty_yellow), R.raw.color_dusty_yellow));

        WordAdapter wordAdapter = new WordAdapter(this, arrayList, R.color.category_colors);
        ListView listView = (ListView) findViewById(R.id.root_view);
        listView.setAdapter(wordAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                releaseMediaPlayer();
                mediaPlayer = MediaPlayer.create(ColorsActivity.this,
                        arrayList.get(position).getmAudioID());
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