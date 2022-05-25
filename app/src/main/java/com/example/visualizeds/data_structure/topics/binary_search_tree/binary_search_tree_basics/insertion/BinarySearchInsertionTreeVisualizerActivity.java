package com.example.visualizeds.data_structure.topics.binary_search_tree.binary_search_tree_basics.insertion;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.widget.NestedScrollView;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsoluteLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.visualizeds.R;
import com.example.visualizeds.data_structure.builder.BinarySearchTreeBuilder;
import com.example.visualizeds.data_structure.builder.DoublyLinkedListNodeBuilder;
import com.example.visualizeds.data_structure.builder.LinkedListNodeBuilder;
import com.example.visualizeds.data_structure.builder.StepCardBuilder;
import com.example.visualizeds.data_structure.classes.BinaryTreeNode;
import com.example.visualizeds.data_structure.classes.DataStructureAlgorithm;
import com.example.visualizeds.databinding.ActivityBinarySearchTreeInsertionVisualizerBinding;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class BinarySearchInsertionTreeVisualizerActivity extends AppCompatActivity {

    private ActivityBinarySearchTreeInsertionVisualizerBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityBinarySearchTreeInsertionVisualizerBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        BinaryTreeNode root = null;

        for (int i = 0; i < 10; i++) {
            int rand = new Random().nextInt() % 300 + 300;
            root = BinaryTreeNode.insertNode(root, rand);
        }
        BinaryTreeNode.inOrderTraversal(root);

        BinarySearchTreeBuilder binarySearchTreeBuilder = new BinarySearchTreeBuilder(this);
        ConstraintLayout constraintLayout = binarySearchTreeBuilder.generateBinarySearchTree(root);
//        constraintLayout.setBackground(getDrawable(R.drawable.red_border));
        binding.scrollView.addView(constraintLayout);
    }
}
