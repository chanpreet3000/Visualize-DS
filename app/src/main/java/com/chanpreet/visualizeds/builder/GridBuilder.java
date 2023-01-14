package com.chanpreet.visualizeds.builder;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.chanpreet.visualizeds.R;

import java.util.ArrayList;
import java.util.List;

public class GridBuilder {

    public static final int START_COLOR = R.drawable.item_grid_start;
    public static final int END_COLOR = R.drawable.item_grid_end;
    public static final int BLOCK_COLOR = R.drawable.item_grid_block;
    public static final int DEFAULT_COLOR = R.drawable.item_grid_default;
    public static final int PATH_COLOR = R.drawable.item_grid_path;
    public static final int SUCCESS_COLOR = R.drawable.item_grid_success;


    public static GridObject build(Context context, int n, int sz) {
        LinearLayout parent = new LinearLayout(context);
        parent.setBackgroundResource(R.drawable.outline_2dp);
        parent.setOrientation(LinearLayout.VERTICAL);
        parent.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        List<List<ImageView>> listOfImageViews = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            listOfImageViews.add(new ArrayList<>());

            LinearLayout child = new LinearLayout(context);
            child.setOrientation(LinearLayout.HORIZONTAL);
            child.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));

            for (int j = 0; j < n; j++) {
                ImageView imageView = new ImageView(context);
                imageView.setLayoutParams(new LinearLayout.LayoutParams(sz, sz));
                imageView.setBackgroundResource(DEFAULT_COLOR);
                listOfImageViews.get(i).add(imageView);
                child.addView(imageView);
            }
            parent.addView(child);
        }
        return new GridObject(listOfImageViews, parent);
    }

    public static class GridObject {
        private final List<List<ImageView>> listOfImageViews;
        private final View view;

        public GridObject(List<List<ImageView>> listOfImageViews, View view) {
            this.listOfImageViews = listOfImageViews;
            this.view = view;
        }

        public List<List<ImageView>> getListOfImageViews() {
            return listOfImageViews;
        }

        public View getView() {
            return view;
        }
    }
}
