package com.chanpreet.visualizeds.data_structure_algorithms.queue.basics.push_pop;

import android.text.InputType;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.chanpreet.visualizeds.activity.VisualizerActivity;
import com.chanpreet.visualizeds.builder.QueueBuilder;
import com.chanpreet.visualizeds.builder.TextBuilder;
import com.chanpreet.visualizeds.classes.StepCard;
import com.chanpreet.visualizeds.databinding.ItemErrorAlertCardBinding;
import com.chanpreet.visualizeds.databinding.ItemSuccessAlertCardBinding;
import com.chanpreet.visualizeds.databinding.ItemVisualizeInputCard2Binding;

import java.util.LinkedList;
import java.util.Locale;
import java.util.Queue;

public class QueuePushPopActivity extends VisualizerActivity {
    private EditText pushEditText;
    private final Queue<Integer> queue = new LinkedList<>();

    @Override
    public void onCreate() {
        super.onCreate();
        binding.visualizeButton.setVisibility(View.GONE);
    }

    @Override
    public void generateInputUI() {
//Creating UI
        ItemVisualizeInputCard2Binding binding1 = ItemVisualizeInputCard2Binding.inflate(getLayoutInflater());
        binding1.textView.setText("Push an element into the Queue.");
        binding1.editText.setHint("Enter a value");
        binding1.editText.setInputType(InputType.TYPE_CLASS_PHONE);
        binding1.button.setText("PUSH");

        ItemVisualizeInputCard2Binding binding2 = ItemVisualizeInputCard2Binding.inflate(getLayoutInflater());
        binding2.textView.setText("Pop an element out of the Queue.");
        binding2.editText.setVisibility(View.GONE);
        binding2.button.setText("POP");

        ItemSuccessAlertCardBinding binding3 = ItemSuccessAlertCardBinding.inflate(getLayoutInflater());
        binding3.textView.setText("PUSH operation means inserting an element into the queue");

        ItemErrorAlertCardBinding binding4 = ItemErrorAlertCardBinding.inflate(getLayoutInflater());
        binding4.textView.setText("POP operation means deleting the last element inserted from the queue");

        //adding UI
        binding.inputLinearLayout.addView(binding3.getRoot());
        binding.inputLinearLayout.addView(binding1.getRoot());
        binding.inputLinearLayout.addView(binding4.getRoot());
        binding.inputLinearLayout.addView(binding2.getRoot());

        //caching UI
        pushEditText = binding1.editText;

        binding1.button.setOnClickListener(v -> pushButtonClicked());
        binding2.button.setOnClickListener(v -> popButtonClicked());
    }

    private void pushButtonClicked() {
        super.hideKeyboard();

        //getting array and target
        int target;
        try {
            target = Integer.parseInt(pushEditText.getText().toString());
        } catch (Exception e) {
            Toast.makeText(this, e.toString(), Toast.LENGTH_SHORT).show();
            return;
        }


        StepCard stepCard = new StepCard();
        stepCard.setTitle("PUSH Operation");
        stepCard.setDescription(TextBuilder.makeBulletList(String.format(Locale.US, "Pushing %d into the queue.", target)));
        stepCard.setData(QueueBuilder.build(getApplicationContext(), queue, QueueBuilder.PUSH_OPERATION, target));
        adapter.addStepCard(stepCard);
        binding.viewPager.setCurrentItem(adapter.getItemCount() - 1, true);

        queue.add(target);
    }

    private void popButtonClicked() {
        super.hideKeyboard();

        StepCard stepCard = new StepCard();

        if (queue.isEmpty()) {
            stepCard.setTitle("Queue is Empty!");
            stepCard.setDescription(TextBuilder.makeBulletList("No element to pop from the queue."));
        } else {
            stepCard.setTitle("POP Operation");
            stepCard.setDescription(TextBuilder.makeBulletList(String.format(Locale.US, "Removing %d from the queue", queue.peek())));
            stepCard.setData(QueueBuilder.build(getApplicationContext(), queue, QueueBuilder.POP_OPERATION, -1));
            queue.remove();
        }
        adapter.addStepCard(stepCard);
        binding.viewPager.setCurrentItem(adapter.getItemCount() - 1, true);
    }

    @Override
    public void visualizeButtonClicked() {

    }

}