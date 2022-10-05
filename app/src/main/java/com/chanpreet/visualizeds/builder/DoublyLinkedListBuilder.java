package com.chanpreet.visualizeds.builder;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;

import com.chanpreet.visualizeds.R;
import com.chanpreet.visualizeds.classes.DoublyLinkedListNode;
import com.chanpreet.visualizeds.databinding.ItemDoublyLinkedListBinding;

import java.util.HashMap;

public class DoublyLinkedListBuilder {
    public static final int COLOR_DEFAULT = R.color.green;
    public static final int COLOR_TARGET_NOT_MATCHED = R.color.oxford_blue;
    public static final int COLOR_TARGET_MATCHED = R.color.dark_red;

    @NonNull
    public static View build(Context context, DoublyLinkedListNode head, HashMap<DoublyLinkedListNode, Integer> map) {
        LinearLayout parent = new LinearLayout(context);
        ItemDoublyLinkedListBinding doublyLinkedListBinding;

        doublyLinkedListBinding = ItemDoublyLinkedListBinding.inflate(LayoutInflater.from(context));
        doublyLinkedListBinding.dataTextView.setText("NULL");
        parent.addView(doublyLinkedListBinding.getRoot());

        DoublyLinkedListNode temp = head;
        while (temp != null) {
            doublyLinkedListBinding = ItemDoublyLinkedListBinding.inflate(LayoutInflater.from(context));
            doublyLinkedListBinding.dataTextView.setText(String.valueOf(temp.data));
            if (map.containsKey(temp)) {
                doublyLinkedListBinding.cardView.setCardBackgroundColor(context.getColor(map.get(temp)));
                doublyLinkedListBinding.indexPointer.setVisibility(View.VISIBLE);
            } else {
                doublyLinkedListBinding.cardView.setCardBackgroundColor(context.getColor(COLOR_DEFAULT));
                doublyLinkedListBinding.indexPointer.setVisibility(View.INVISIBLE);
            }
            temp = temp.next;
            parent.addView(doublyLinkedListBinding.getRoot());
        }

        doublyLinkedListBinding = ItemDoublyLinkedListBinding.inflate(LayoutInflater.from(context));
        doublyLinkedListBinding.dataTextView.setText("NULL");
        doublyLinkedListBinding.nodeNextPointer.setVisibility(View.INVISIBLE);
        parent.addView(doublyLinkedListBinding.getRoot());
        return parent;
    }
}
