package com.chanpreet.visualizeds.topics.stack.basics;

import android.text.InputType;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.chanpreet.visualizeds.StepCard;
import com.chanpreet.visualizeds.builder.StackBuilder;
import com.chanpreet.visualizeds.databinding.ItemVisualizeInputCard2Binding;
import com.chanpreet.visualizeds.topics.VisualizerActivity;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Stack;

public class PushPopActivity extends VisualizerActivity {
    private EditText pushEditText;
    private Stack<Integer> st = new Stack<>();

    @Override
    public void onCreate() {
        super.onCreate();
        binding.visualizeButton.setVisibility(View.GONE);
    }

    @Override
    public void generateInputUI() {
//Creating UI
        ItemVisualizeInputCard2Binding binding1 = ItemVisualizeInputCard2Binding.inflate(getLayoutInflater());
        binding1.textView.setText("Push an element into the Stack.");
        binding1.editText.setHint("Enter a value");
        binding1.editText.setInputType(InputType.TYPE_CLASS_PHONE);
        binding1.button.setText("PUSH");

        ItemVisualizeInputCard2Binding binding2 = ItemVisualizeInputCard2Binding.inflate(getLayoutInflater());
        binding2.textView.setText("Pop an element out of the Stack.");
        binding2.editText.setVisibility(View.GONE);
        binding2.button.setText("POP");

        //adding UI
        binding.inputLinearLayout.addView(binding1.getRoot());
        binding.inputLinearLayout.addView(binding2.getRoot());

        //caching UI
        pushEditText = binding1.editText;

        binding1.button.setOnClickListener(v -> pushButtonClicked());
        binding2.button.setOnClickListener(v -> popButtonClicked());
    }

    private void pushButtonClicked() {
        //getting array and target
        int target;
        try {
            target = Integer.parseInt(pushEditText.getText().toString());
        } catch (Exception e) {
            Toast.makeText(this, e.toString(), Toast.LENGTH_SHORT).show();
            return;
        }

        st.push(target);

        List<StepCard> stepCardList = new ArrayList<>();

        StepCard stepCard = new StepCard();
        stepCard.setTitle(String.format(Locale.US, "Pushing %d into the Stack.", target));
        stepCard.setDescription("");
        stepCard.setData(StackBuilder.build(getApplicationContext(), st, StackBuilder.PUSH_OPERATION));

        stepCardList.add(stepCard);
        adapter.setStepCardList(stepCardList);
    }

    private void popButtonClicked() {

        List<StepCard> stepCardList = new ArrayList<>();

        StepCard stepCard = new StepCard();

        if (st.empty()) {
            stepCard.setTitle("Stack is Empty!");
            stepCard.setDescription("");
        } else {
            stepCard.setTitle("POPing from the Stack.");
            stepCard.setDescription("");
            stepCard.setData(StackBuilder.build(getApplicationContext(), st, StackBuilder.POP_OPERATION));
            st.pop();
        }
        stepCardList.add(stepCard);
        adapter.setStepCardList(stepCardList);
    }

    @Override
    public void visualizeButtonClicked() {

    }
}
