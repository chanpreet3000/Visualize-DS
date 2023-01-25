package com.chanpreet.visualizeds.builder;

import android.content.Context;
import android.graphics.Color;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.chanpreet.visualizeds.R;
import com.chanpreet.visualizeds.databinding.ItemStackNodeBinding;

import java.util.LinkedList;
import java.util.Objects;
import java.util.Queue;

public class QueueBuilder {
    public static final int COLOR_DEFAULT = R.color.green;
    public static final int COLOR_TARGET_MATCHED = R.color.dark_red;
    public static final int COLOR_TARGET_NOT_MATCHED = R.color.oxford_blue;

    public static final String PUSH_OPERATION = "PUSH";
    public static final String POP_OPERATION = "POP";

    @NonNull
    public static View build(Context context, Queue<Integer> queue, String param, Integer data) {
        LinearLayout parent = new LinearLayout(context);
        parent.setOrientation(LinearLayout.VERTICAL);

        LinearLayout leftLayout = new LinearLayout(context);
        leftLayout.setOrientation(LinearLayout.HORIZONTAL);
        leftLayout.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        leftLayout.setPadding(5, 0, 5, 0);
        leftLayout.setGravity(Gravity.CENTER_VERTICAL);

        LinearLayout midLayout = new LinearLayout(context);
        midLayout.setOrientation(LinearLayout.HORIZONTAL);
        midLayout.setPadding(0, 32, 0, 32);
        midLayout.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        midLayout.setGravity(Gravity.CENTER);


        LinearLayout rightLayout = new LinearLayout(context);
        rightLayout.setOrientation(LinearLayout.HORIZONTAL);
        rightLayout.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        rightLayout.setPadding(5, 0, 5, 0);
        rightLayout.setGravity(Gravity.CENTER_VERTICAL);


        TextView textView = new TextView(context);
        textView.setTextColor(Color.BLACK);
        textView.setTextSize(18);
        textView.setGravity(Gravity.CENTER);


        TextView textView1 = new TextView(context);
        textView1.setTextColor(Color.BLACK);
        textView1.setTextSize(18);
        textView1.setText("BEFORE");
        textView1.setGravity(Gravity.CENTER);

        leftLayout.addView(textView1);

        TextView textView2 = new TextView(context);
        textView2.setTextColor(Color.BLACK);
        textView2.setTextSize(18);
        textView2.setText("AFTER");
        textView2.setGravity(Gravity.CENTER);

        rightLayout.addView(textView2);


        if (Objects.equals(param, PUSH_OPERATION)) {
            textView.setText("PUSH\nOPERATION");

            Queue<Integer> temp = new LinkedList<>(queue);

            LinearLayout layout1 = new LinearLayout(context);
            layout1.setOrientation(LinearLayout.HORIZONTAL);
            layout1.setBackgroundResource(R.drawable.ic_queue_background);
            layout1.setPadding(80, 5, 80, 5);
            layout1.setGravity(Gravity.CENTER);

            while (!temp.isEmpty()) {
                ItemStackNodeBinding node = ItemStackNodeBinding.inflate(LayoutInflater.from(context));
                node.arrayNodeDataTextView.setText(String.valueOf(temp.peek()));
                node.arrayNodeCardView.setCardBackgroundColor(context.getColor(COLOR_DEFAULT));
                layout1.addView(node.getRoot());
                temp.remove();
            }
            leftLayout.addView(layout1);

            temp.clear();
            temp.addAll(queue);
            temp.add(data);

            LinearLayout layout2 = new LinearLayout(context);
            layout2.setOrientation(LinearLayout.HORIZONTAL);
            layout2.setBackgroundResource(R.drawable.ic_queue_background);
            layout2.setPadding(80, 5, 80, 5);
            layout2.setGravity(Gravity.CENTER);


            while (!temp.isEmpty()) {
                ItemStackNodeBinding node = ItemStackNodeBinding.inflate(LayoutInflater.from(context));
                node.arrayNodeDataTextView.setText(String.valueOf(temp.peek()));
                if (temp.size() == 1) {
                    node.arrayNodeCardView.setCardBackgroundColor(context.getColor(COLOR_TARGET_MATCHED));
                } else {
                    node.arrayNodeCardView.setCardBackgroundColor(context.getColor(COLOR_DEFAULT));
                }
                layout2.addView(node.getRoot());
                temp.remove();
            }
            rightLayout.addView(layout2);
        } else if (Objects.equals(param, POP_OPERATION)) {
            textView.setText("POP\nOPERATION");

            Queue<Integer> temp = new LinkedList<>(queue);

            LinearLayout layout1 = new LinearLayout(context);
            layout1.setOrientation(LinearLayout.HORIZONTAL);
            layout1.setBackgroundResource(R.drawable.ic_queue_background);
            layout1.setPadding(80, 5, 80, 5);
            layout1.setGravity(Gravity.CENTER);


            int n = temp.size();
            while (!temp.isEmpty()) {
                ItemStackNodeBinding node = ItemStackNodeBinding.inflate(LayoutInflater.from(context));
                node.arrayNodeDataTextView.setText(String.valueOf(temp.peek()));
                if (temp.size() == n) {
                    node.arrayNodeCardView.setCardBackgroundColor(context.getColor(COLOR_TARGET_MATCHED));
                } else {
                    node.arrayNodeCardView.setCardBackgroundColor(context.getColor(COLOR_DEFAULT));
                }
                layout1.addView(node.getRoot());
                temp.remove();
            }
            leftLayout.addView(layout1);

            temp.clear();
            temp.addAll(queue);
            temp.remove();

            LinearLayout layout2 = new LinearLayout(context);
            layout2.setOrientation(LinearLayout.HORIZONTAL);
            layout2.setBackgroundResource(R.drawable.ic_queue_background);
            layout2.setPadding(80, 5, 80, 5);
            layout2.setGravity(Gravity.CENTER);

            while (!temp.isEmpty()) {
                ItemStackNodeBinding node = ItemStackNodeBinding.inflate(LayoutInflater.from(context));
                node.arrayNodeDataTextView.setText(String.valueOf(temp.peek()));
                node.arrayNodeCardView.setCardBackgroundColor(context.getColor(COLOR_DEFAULT));
                layout2.addView(node.getRoot());
                temp.remove();
            }
            rightLayout.addView(layout2);
        }

        midLayout.addView(textView);

        parent.addView(leftLayout);
        parent.addView(midLayout);
        parent.addView(rightLayout);

        return parent;
    }
}
