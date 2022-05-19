package com.example.visualizeds.data_structure.topics.array.searching.linear_search;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.example.visualizeds.R;
import com.example.visualizeds.data_structure.classes.DataStructureAlgorithm;
import com.example.visualizeds.databinding.ActivityLinearSearchVisualizerBinding;

import java.util.ArrayList;
import java.util.List;

public class LinearSearchVisualizerActivity extends AppCompatActivity {

    private static final int STEP_CARD_MARGIN_LEFT = 16;
    private static final int STEP_CARD_MARGIN_TOP = 24;
    private static final int STEP_CARD_MARGIN_RIGHT = 16;
    private static final int STEP_CARD_MARGIN_DOWN = 24;

    private static final int DATA_NODE_MARGIN_LEFT = 16;
    private static final int DATA_NODE_MARGIN_TOP = 16;
    private static final int DATA_NODE_MARGIN_RIGHT = 16;
    private static final int DATA_NODE_MARGIN_DOWN = 16;

    private static final int COLOR_TARGET_MATCHED = R.color.dark_red;
    private static final int COLOR_TARGET_NOT_MATCHED = R.color.citron;

    private ActivityLinearSearchVisualizerBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLinearSearchVisualizerBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        //filling header information
        DataStructureAlgorithm dataStructureAlgorithm = (DataStructureAlgorithm) getIntent().getSerializableExtra("data");
        binding.titleTextView.setText(dataStructureAlgorithm.getName());
        binding.difficultyTextView.setText(dataStructureAlgorithm.getDifficulty().toString());
        binding.iconImageView.setImageResource(dataStructureAlgorithm.getIcon());


        //button click listener
        binding.visualizeButton.setOnClickListener(v -> {

            //clear all views of the linear Layout
            clearLayout();

            //Generating Visuals
            List<Integer> arr = stringToArray(binding.arrayEditText.getText().toString().trim());
            int target = 0;
            try {
                target = Integer.parseInt(binding.targetEditText.getText().toString());
            } catch (Exception e) {
                Toast.makeText(this, e.toString(), Toast.LENGTH_SHORT).show();
                return;
            }
            for (int i = 0; i < arr.size(); i++) {
                //generating Step Card
                View view = getLayoutInflater().inflate(R.layout.item_visualizer_step_card_1, null);

                //Setting Step Card Parameters
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                params.setMargins(STEP_CARD_MARGIN_LEFT, STEP_CARD_MARGIN_TOP, STEP_CARD_MARGIN_RIGHT, STEP_CARD_MARGIN_DOWN);
                view.setLayoutParams(params);

                //Inserting data into Step Cards
                ((TextView) view.findViewById(R.id.titleTextView)).setText(String.format("Step %d", i + 1));
                LinearLayout holder = view.findViewById(R.id.holderLinearLayout);

                //Generating Data for Step Card
                generateArrayView(arr, holder, i, target);

                //filling the description
                if (arr.get(i) == target) {
                    ((TextView) view.findViewById(R.id.descriptionTextView)).setText(arr.get(i) + " is equal to " + target + ". Therefore, we found the element!");
                } else {
                    ((TextView) view.findViewById(R.id.descriptionTextView)).setText(arr.get(i) + " is not equal to " + target);
                }
                //Adding view to the holder of the Step Card
                binding.holderLinearLayout.addView(view);
                if (arr.get(i) == target) {
                    return;
                }
            }
        });
    }
    private void clearLayout() {
        binding.holderLinearLayout.removeAllViews();
    }

    private void generateArrayView(List<Integer> arr, LinearLayout holder, int index, int target) {
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        for (int i = 0; i < arr.size(); i++) {
            //Initializing the data node view
            View dataNode = getLayoutInflater().inflate(R.layout.item_data_structure_node, null);
            params.setMargins(DATA_NODE_MARGIN_LEFT, DATA_NODE_MARGIN_TOP, DATA_NODE_MARGIN_RIGHT, DATA_NODE_MARGIN_DOWN);
            dataNode.setLayoutParams(params);

            //data node setting text
            TextView dataNodeDataTextView = dataNode.findViewById(R.id.dataNodeDataTextView);
            TextView dataNodeIndexTextView = dataNode.findViewById(R.id.dataNodeIndexTextView);
            dataNodeDataTextView.setText(String.valueOf(arr.get(i)));
            dataNodeIndexTextView.setText(String.valueOf(i));

            if (i == index) {
                dataNode.findViewById(R.id.dataNodeIndexPointer).setVisibility(View.VISIBLE);
                if (arr.get(i) == target) {
                    ((CardView) dataNode.findViewById(R.id.dataNodeCardView)).setCardBackgroundColor(getColor(COLOR_TARGET_MATCHED));
                } else {
                    ((CardView) dataNode.findViewById(R.id.dataNodeCardView)).setCardBackgroundColor(getColor(COLOR_TARGET_NOT_MATCHED));
                }
            }
            //adding data node to the linearLayout.
            holder.addView(dataNode);
        }
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