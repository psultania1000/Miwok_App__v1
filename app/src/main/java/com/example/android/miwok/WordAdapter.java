package com.example.android.miwok;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class WordAdapter extends ArrayAdapter<Word> {
    private static final String TAG = "Prob";
    int color_id;
    public WordAdapter(@NonNull Context context, ArrayList<Word> resource, int category_numbers) {
        super(context, 0, resource);
        color_id = category_numbers;
        Log.d(TAG, "WordAdapter: "+Integer.toString(color_id));
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = convertView;

        if(view == null) {
            view = LayoutInflater.from(getContext()).inflate(
                    R.layout.translate_layout, parent, false);
        }
        LinearLayout linearLayout = view.findViewById(R.id.inner_view);
        int color = ContextCompat.getColor(getContext(), color_id);
        linearLayout.setBackgroundColor(color);

        Word currentWord = getItem(position);
        //assert view != null;
        TextView defaultTextView = view.findViewById(R.id.text1);
        //assert currentWord != null;
        defaultTextView.setText(currentWord.getmDefaultTranslation());
        TextView miwokTextView = view.findViewById(R.id.text2);
        miwokTextView.setText(currentWord.getmMiwokTranslation());



        ImageView icon = view.findViewById(R.id.image);
        if (currentWord.getIcon()!=null)
            icon.setImageDrawable(currentWord.getIcon());
        else
            icon.setVisibility(View.GONE);
        return view;
    }
}
