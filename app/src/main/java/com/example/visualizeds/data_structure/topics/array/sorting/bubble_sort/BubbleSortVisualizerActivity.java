package com.example.visualizeds.data_structure.topics.array.sorting.bubble_sort;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.example.visualizeds.R;
import com.example.visualizeds.databinding.ActivityBubbleSortVisualizerBinding;

import java.util.ArrayList;
import java.util.List;

public class BubbleSortVisualizerActivity extends AppCompatActivity {

    private static final int DATA_NODE_MARGIN_LEFT = 16;
    private static final int DATA_NODE_MARGIN_UP = 16;
    private static final int DATA_NODE_MARGIN_RIGHT = 16;
    private static final int DATA_NODE_MARGIN_DOWN = 16;
    private static final int COLOR_SWAPPED = Color.RED;
    private static final int COLOR_NOT_SWAPPED = -13500;

    private ActivityBubbleSortVisualizerBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityBubbleSortVisualizerBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        EditText editText = findViewById(R.id.editText);
        Button btn = findViewById(R.id.generateButton);
        LinearLayout holder = findViewById(R.id.holder);

        btn.setOnClickListener(v -> {
            List<Integer> arr = stringToArray(editText.getText().toString().trim());
            for (int i = 0; i < arr.size() - 1; i++) {
                for (int j = 0; j < arr.size() - i - 1; j++) {
                    //Title Text
                    TextView textView = new TextView(getApplicationContext());
                    textView.setText("Step " + (i * arr.size() + j + 1) + " : ");
                    holder.addView(textView);

                    TextView swappedTV = new TextView(getApplicationContext());
                    View view;
                    //Swapping conditions
                    if (arr.get(j) > arr.get(j + 1)) {
                        int temp = arr.get(j);
                        arr.set(j, arr.get(j + 1));
                        arr.set(j + 1, temp);
                        swappedTV.setText("Swapped " + (j + 1) + " & " + (j + 2) + " elements.");
                        view = generateArrayView(arr, j, j + 1, COLOR_SWAPPED);
                    } else {
                        swappedTV.setText("No swapping necessary.");
                        view = generateArrayView(arr, j, j + 1, COLOR_NOT_SWAPPED);
                    }
                    holder.addView(swappedTV);
                    holder.addView(view);
                }
            }
        });
    }


    private HorizontalScrollView generateArrayView(List<Integer> arr) {
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        HorizontalScrollView scrollView = new HorizontalScrollView(getApplicationContext());
        LinearLayout linearLayout = new LinearLayout(getApplicationContext());
        for (int i = 0; i < arr.size(); i++) {
            //Initializing the data node view
            View dataNode = getLayoutInflater().inflate(R.layout.item_data_structure_node, null, false);
            params.setMargins(DATA_NODE_MARGIN_LEFT, DATA_NODE_MARGIN_UP, DATA_NODE_MARGIN_RIGHT, DATA_NODE_MARGIN_DOWN);
            dataNode.setLayoutParams(params);

            //data node setting text
            TextView dataNodeDataTextView = dataNode.findViewById(R.id.dataNodeDataTextView);
            TextView dataNodeIndexTextView = dataNode.findViewById(R.id.dataNodeIndexTextView);
            dataNodeDataTextView.setText(String.valueOf(arr.get(i)));
            dataNodeIndexTextView.setText(String.valueOf(i));

            //adding data node to the linearLayout.
            linearLayout.addView(dataNode);
        }
        scrollView.addView(linearLayout);
        return scrollView;
    }

    private HorizontalScrollView generateArrayView(List<Integer> arr, int a, int b) {
        HorizontalScrollView view = generateArrayView(arr);
        ((LinearLayout) view.getChildAt(0)).getChildAt(a).findViewById(R.id.dataNodeIndexPointer).setVisibility(View.VISIBLE);
        ((LinearLayout) view.getChildAt(0)).getChildAt(b).findViewById(R.id.dataNodeIndexPointer).setVisibility(View.VISIBLE);
        return view;
    }

    private HorizontalScrollView generateArrayView(List<Integer> arr, int a, int b, int color) {
        HorizontalScrollView view = generateArrayView(arr);
        ((LinearLayout) view.getChildAt(0)).getChildAt(a).findViewById(R.id.dataNodeIndexPointer).setVisibility(View.VISIBLE);
        ((LinearLayout) view.getChildAt(0)).getChildAt(b).findViewById(R.id.dataNodeIndexPointer).setVisibility(View.VISIBLE);
        ((CardView) ((LinearLayout) view.getChildAt(0)).getChildAt(a).findViewById(R.id.dataNodeCardView)).setCardBackgroundColor(color);
        ((CardView) ((LinearLayout) view.getChildAt(0)).getChildAt(b).findViewById(R.id.dataNodeCardView)).setCardBackgroundColor(color);
        return view;
    }

    private List<Integer> stringToArray(String s) {
        String c = "";
        List<Integer> arr = new ArrayList<>();
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == ' ') {
                try {
                    int number = Integer.parseInt(c);
                    arr.add(number);
                    c = "";
                } catch (Exception ignored) {
                }

            } else {
                c = c + s.charAt(i);
            }
            if (i == s.length() - 1) {
                try {
                    int number = Integer.parseInt(c);
                    arr.add(number);
                    c = "";
                } catch (Exception ignored) {
                }
            }
        }
        return arr;
    }
}