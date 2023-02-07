package com.chanpreet.visualizeds.builder;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;

import com.chanpreet.visualizeds.R;
import com.chanpreet.visualizeds.databinding.ItemArrayNodeBinding;

import java.util.HashMap;
import java.util.List;

public class ArrayBuilder {
    public static final int COLOR_DEFAULT = R.color.white;
    public static final int COLOR_TARGET_MATCHED = R.color.oxford_blue;
    public static final int COLOR_TARGET_NOT_MATCHED = R.color.dark_red;

    @NonNull
    public static View build(Context context, List<Integer> arr, HashMap<Integer, Integer> map) {
        LinearLayout parent = new LinearLayout(context);
        for (int i = 0; i < arr.size(); i++) {
            // Initializing an Array Node.
            ItemArrayNodeBinding arrayNode = ItemArrayNodeBinding.inflate(LayoutInflater.from(context));
            arrayNode.arrayNodeDataTextView.setText(String.valueOf(arr.get(i)));
            arrayNode.arrayNodeIndexTextView.setText(String.valueOf(i));
            arrayNode.arrayNodeDataTextView.setTextColor(context.getColor(R.color.black));
            arrayNode.arrayNodeCardView.setCardBackgroundColor(context.getColor(COLOR_DEFAULT));
            //Array conditions
            if (map.containsKey(i)) {
                arrayNode.arrayNodeDataTextView.setTextColor(context.getColor(R.color.white));
                arrayNode.arrayNodeIndexPointer.setVisibility(View.VISIBLE);
                arrayNode.arrayNodeCardView.setCardBackgroundColor(context.getColor(map.get(i)));
            } else {
                arrayNode.arrayNodeIndexPointer.setVisibility(View.INVISIBLE);
            }
            // Adding array Node to the step card builder holder
            parent.addView(arrayNode.getRoot());
        }
        return parent;
    }
}
