package com.chanpreet.visualizeds.topics.doubly_linked_list.doubly_linked_list_basics.deletion;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.chanpreet.visualizeds.builder.DoublyLinkedListNodeBuilder;
import com.chanpreet.visualizeds.builder.LinkedListNodeBuilder;
import com.chanpreet.visualizeds.builder.StepCardBuilder;
import com.chanpreet.visualizeds.classes.DataStructureAlgorithm;
import com.chanpreet.visualizeds.databinding.ActivityDoublyLinkedListDeletionVisualizerBinding;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class DoublyLinkedListDeletionVisualizerActivity extends AppCompatActivity {

    private ActivityDoublyLinkedListDeletionVisualizerBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDoublyLinkedListDeletionVisualizerBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        //filling header information
        DataStructureAlgorithm dataStructureAlgorithm = (DataStructureAlgorithm) getIntent().getSerializableExtra("data");
        binding.titleTextView.setText(dataStructureAlgorithm.getName());
        binding.difficultyTextView.setText(dataStructureAlgorithm.getDifficulty().toString());
        binding.iconImageView.setImageResource(dataStructureAlgorithm.getIcon());

        //Setting title
        setTitle(dataStructureAlgorithm.getName() + " Visualizer");


        List<Integer> arr = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            int rand = new Random().nextInt() % 100;
            arr.add(rand);
        }
        initialView(arr);

        //button click listener
        binding.visualizeButton.setOnClickListener(v -> {
            //clear all views of the linear Layout

            clearLayout();
            initialView(arr);

            int target = 0;
            try {
                target = Integer.parseInt(binding.targetEditText.getText().toString().trim());
            } catch (Exception e) {
                Toast.makeText(this, e.toString(), Toast.LENGTH_SHORT).show();
                return;
            }
            //Generating in Between layout
            int steps = 0;
            for (int i = 0; i < arr.size(); i++) {
                //Generating Visuals
                StepCardBuilder stepCardBuilder = new StepCardBuilder(getApplicationContext());
                stepCardBuilder.setCardTitle(String.format("Step %d", ++steps));
                if (i != arr.size() - 1)
                    stepCardBuilder.setCardDescription("This node is not the equal to the search target.\nTherefore we move to the next node.");
                else
                    stepCardBuilder.setCardDescription("We reached the end of the Doubly Linked List because the next pointer points to NULL.\nTherefore, the element does not exists.");
                if (arr.get(i) == target)
                    stepCardBuilder.setCardDescription("We found the element to be deleted.");

                //Generating Data for Step Card
                generateLinkedListView(arr, stepCardBuilder.getDataNodeHolder(), i, target);
                //Adding view to the holder of the Step Card
                binding.holderLinearLayout.addView(stepCardBuilder.getStepCard());
                if (arr.get(i) == target) {
                    arr.remove(i);
                    StepCardBuilder builder = new StepCardBuilder(getApplicationContext());
                    builder.setCardTitle("Final Doubly Linked List");
                    builder.setCardDescription("This is the Doubly Linked List after deletion.");
                    //Generating Data for Step Card
                    generateLinkedListView(arr, builder.getDataNodeHolder(), -1, -1);
                    //Adding view to the holder of the Step Card
                    binding.holderLinearLayout.addView(builder.getStepCard());
                    return;
                }
            }
        });
    }

    private void clearLayout() {
        binding.holderLinearLayout.removeAllViews();
    }

    private void initialView(List<Integer> arr) {
        StepCardBuilder builder = new StepCardBuilder(getApplicationContext());
        builder.setCardTitle("Initial Doubly Linked List");
        builder.setCardDescription("This is the initial Linked.");
        //Generating Data for Step Card
        generateLinkedListView(arr, builder.getDataNodeHolder(), -1, -1);
        //Adding view to the holder of the Step Card
        binding.holderLinearLayout.addView(builder.getStepCard());
    }

    private void generateLinkedListView(@NonNull List<Integer> arr, @NonNull LinearLayout holder, int index, int target) {
        //adding head node
        DoublyLinkedListNodeBuilder doublyLinkedListNodeBuilder = new DoublyLinkedListNodeBuilder(getApplicationContext());
        doublyLinkedListNodeBuilder.setNodeData("HEAD");
        holder.addView(doublyLinkedListNodeBuilder.getNode());

        //adding data nodes
        //adding data nodes
        for (int i = 0; i < arr.size(); i++) {
            //Initializing the data node view
            doublyLinkedListNodeBuilder = new DoublyLinkedListNodeBuilder(getApplicationContext());
            doublyLinkedListNodeBuilder.setNodeData(arr.get(i));
            if (i == index) {
                doublyLinkedListNodeBuilder.showIndexPointer();
                if (arr.get(i) == target) {
                    doublyLinkedListNodeBuilder.setNodeColor(LinkedListNodeBuilder.COLOR_RED);
                } else {
                    doublyLinkedListNodeBuilder.setNodeColor(LinkedListNodeBuilder.COLOR_YELLOW_GREEN);
                }
            }
            //adding data node to the linearLayout.
            holder.addView(doublyLinkedListNodeBuilder.getNode());

            //requesting focus
            holder.requestChildFocus(holder.getChildAt(index+ 1),holder.getChildAt(index+ 1));
        }

        //adding last NULL node
        doublyLinkedListNodeBuilder = new DoublyLinkedListNodeBuilder(getApplicationContext());
        doublyLinkedListNodeBuilder.setNodeData("NULL");
        doublyLinkedListNodeBuilder.hideNodeNextPointer();
        holder.addView(doublyLinkedListNodeBuilder.getNode());
    }
}