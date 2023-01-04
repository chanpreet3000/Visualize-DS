package com.chanpreet.visualizeds.builder;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;

import com.chanpreet.visualizeds.classes.data_structure_containers.LinkedListNode;
import com.chanpreet.visualizeds.databinding.ItemLinkedListNodeBinding;

import com.chanpreet.visualizeds.R;

import java.util.HashMap;

public class LinkedListBuilder {
    public static final int COLOR_DEFAULT = R.color.green;
    public static final int COLOR_TARGET_NOT_MATCHED = R.color.oxford_blue;
    public static final int COLOR_TARGET_MATCHED = R.color.dark_red;

    @NonNull
    public static View build(Context context, LinkedListNode head, HashMap<LinkedListNode, Integer> map) {
        LinearLayout parent = new LinearLayout(context);

        ItemLinkedListNodeBinding linkedListNodeBinding;
        LinkedListNode temp = head;
        while (temp != null) {
            linkedListNodeBinding = ItemLinkedListNodeBinding.inflate(LayoutInflater.from(context));
            linkedListNodeBinding.dataTextView.setText(String.valueOf(temp.data));
            if (map.containsKey(temp)) {
                linkedListNodeBinding.cardView.setCardBackgroundColor(context.getColor(map.get(temp)));
                linkedListNodeBinding.indexPointer.setVisibility(View.VISIBLE);
            } else {
                linkedListNodeBinding.cardView.setCardBackgroundColor(context.getColor(COLOR_DEFAULT));
                linkedListNodeBinding.indexPointer.setVisibility(View.INVISIBLE);
            }
            temp = temp.next;
            parent.addView(linkedListNodeBinding.getRoot());
        }

        linkedListNodeBinding = ItemLinkedListNodeBinding.inflate(LayoutInflater.from(context));
        linkedListNodeBinding.dataTextView.setText("NULL");
        linkedListNodeBinding.nodeNextPointer.setVisibility(View.INVISIBLE);
        parent.addView(linkedListNodeBinding.getRoot());
        return parent;
    }
}
