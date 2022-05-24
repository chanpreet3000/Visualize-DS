package com.example.visualizeds.data_structure.topics.linked_list.linked_list_basics.insertion;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.visualizeds.data_structure.classes.DataStructureAlgorithm;
import com.example.visualizeds.data_structure.utils.LinkedListNodeBuilder;
import com.example.visualizeds.data_structure.utils.StepCardBuilder;
import com.example.visualizeds.databinding.ActivityLinkedListInsertionBinding;

import java.util.ArrayList;
import java.util.List;

public class LinkedListInsertionActivity extends AppCompatActivity {

    private ActivityLinkedListInsertionBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLinkedListInsertionBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        //filling header information
        DataStructureAlgorithm dataStructureAlgorithm = (DataStructureAlgorithm) getIntent().getSerializableExtra("data");
        binding.titleTextView.setText(dataStructureAlgorithm.getName());
        binding.difficultyTextView.setText(dataStructureAlgorithm.getDifficulty().toString());
        binding.iconImageView.setImageResource(dataStructureAlgorithm.getIcon());

        //Setting title
        setTitle(dataStructureAlgorithm.getName() + " Visualizer");


        List<Integer> arr = new ArrayList<>();

        //button click listener
        binding.visualizeButton.setOnClickListener(v -> {
            //clear all views of the linear Layout
            clearLayout();

            //Generating Visuals
            StepCardBuilder stepCardBuilder = new StepCardBuilder(getApplicationContext());
            stepCardBuilder.setCardTitle("Initial Linked List");
            stepCardBuilder.setCardDescription("This is the initial Linked List before insertion.");
            //Generating Data for Step Card
            generateLinkedListView(arr, stepCardBuilder.getDataNodeHolder(), -1);
            //Adding view to the holder of the Step Card
            binding.holderLinearLayout.addView(stepCardBuilder.getStepCard());


            //Generating in Between layout
            int steps = 0;
            for (int i = 0; i < arr.size(); i++) {
                //Generating Visuals
                stepCardBuilder = new StepCardBuilder(getApplicationContext());
                stepCardBuilder.setCardTitle(String.format("Step %d", ++steps));
                if (i != arr.size() - 1)
                    stepCardBuilder.setCardDescription("This is not the end of the List.\nTherefore we move to the next node.");
                else
                    stepCardBuilder.setCardDescription("We reached the end of the linked list because the next pointer points to NULL.\nTherefore, we will add the Node here.");
                //Generating Data for Step Card
                generateLinkedListView(arr, stepCardBuilder.getDataNodeHolder(), i);
                //Adding view to the holder of the Step Card
                binding.holderLinearLayout.addView(stepCardBuilder.getStepCard());
            }

            //Inserting the value
            int target = 0;
            try {
                target = Integer.parseInt(binding.targetEditText.getText().toString().trim());
            } catch (Exception e) {
                Toast.makeText(this, e.toString(), Toast.LENGTH_SHORT).show();
                return;
            }
            arr.add(target);

            stepCardBuilder = new StepCardBuilder(getApplicationContext());
            stepCardBuilder.setCardTitle("Final Step");
            stepCardBuilder.setCardDescription(String.format("Linked list after adding node with value %d.", target));
            //Generating Data for Step Card
            generateLinkedListView(arr, stepCardBuilder.getDataNodeHolder(), arr.size() - 1);
            //Adding view to the holder of the Step Card
            binding.holderLinearLayout.addView(stepCardBuilder.getStepCard());
        });
    }

    private void clearLayout() {
        binding.holderLinearLayout.removeAllViews();
    }

    private void generateLinkedListView(@NonNull List<Integer> arr, @NonNull LinearLayout holder, int index) {
        //adding head node
        LinkedListNodeBuilder linkedListNodeBuilder = new LinkedListNodeBuilder(getApplicationContext());
        linkedListNodeBuilder.setNodeData("HEAD");
        holder.addView(linkedListNodeBuilder.getNode());

        //adding data nodes
        for (int i = 0; i < arr.size(); i++) {
            //Initializing the data node view
            linkedListNodeBuilder = new LinkedListNodeBuilder(getApplicationContext());
            linkedListNodeBuilder.setNodeData(arr.get(i));
            if (i == index) {
                linkedListNodeBuilder.showIndexPointer();
            }
            //adding data node to the linearLayout.
            holder.addView(linkedListNodeBuilder.getNode());
        }

        //adding last NULL node
        linkedListNodeBuilder = new LinkedListNodeBuilder(getApplicationContext());
        linkedListNodeBuilder.setNodeData("NULL");
        linkedListNodeBuilder.hideNodeNextPointer();
        holder.addView(linkedListNodeBuilder.getNode());
    }
}