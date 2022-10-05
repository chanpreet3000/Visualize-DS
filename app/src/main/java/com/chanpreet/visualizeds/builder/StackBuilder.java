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

import java.util.Objects;
import java.util.Stack;

public class StackBuilder {
    public static final int COLOR_DEFAULT = R.color.green;
    public static final int COLOR_TARGET_MATCHED = R.color.dark_red;
    public static final int COLOR_TARGET_NOT_MATCHED = R.color.oxford_blue;

    public static final String PUSH_OPERATION = "PUSH";
    public static final String POP_OPERATION = "POP";

    @NonNull
    public static View build(Context context, Stack<Integer> st, String param) {
        LinearLayout parent = new LinearLayout(context);
        parent.setOrientation(LinearLayout.HORIZONTAL);

        LinearLayout leftLayout = new LinearLayout(context);
        leftLayout.setOrientation(LinearLayout.VERTICAL);
        leftLayout.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.MATCH_PARENT));
        leftLayout.setPadding(5, 0, 5, 0);
        leftLayout.setGravity(Gravity.BOTTOM);

        LinearLayout midLayout = new LinearLayout(context);
        midLayout.setOrientation(LinearLayout.VERTICAL);
        midLayout.setPadding(48, 0, 48, 0);
        midLayout.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.MATCH_PARENT));
        midLayout.setGravity(Gravity.CENTER);

        TextView textView = new TextView(context);
        textView.setTextColor(Color.BLACK);
        textView.setTextSize(18);
        textView.setGravity(Gravity.CENTER);

        LinearLayout rightLayout = new LinearLayout(context);
        rightLayout.setOrientation(LinearLayout.VERTICAL);
        rightLayout.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.MATCH_PARENT));
        rightLayout.setPadding(5, 0, 5, 0);
        rightLayout.setGravity(Gravity.BOTTOM);

        if (Objects.equals(param, PUSH_OPERATION)) {
            textView.setText("PUSH\nOPERATION");

            Stack<Integer> temp = new Stack<>();
            temp.addAll(st);
            temp.pop();

            LinearLayout layout1 = new LinearLayout(context);
            layout1.setOrientation(LinearLayout.VERTICAL);
            layout1.setBackgroundResource(R.drawable.ic_stack_background);
            layout1.setPadding(5, 80, 5, 5);

            while (!temp.empty()) {
                ItemStackNodeBinding node = ItemStackNodeBinding.inflate(LayoutInflater.from(context));
                node.arrayNodeDataTextView.setText(String.valueOf(temp.peek()));
                node.arrayNodeCardView.setCardBackgroundColor(context.getColor(COLOR_DEFAULT));
                layout1.addView(node.getRoot());
                temp.pop();
            }
            leftLayout.addView(layout1);

            temp.clear();
            temp.addAll(st);

            LinearLayout layout2 = new LinearLayout(context);
            layout2.setOrientation(LinearLayout.VERTICAL);
            layout2.setBackgroundResource(R.drawable.ic_stack_background);
            layout2.setPadding(5, 80, 5, 5);

            int n = temp.size();
            while (!temp.empty()) {
                ItemStackNodeBinding node = ItemStackNodeBinding.inflate(LayoutInflater.from(context));
                node.arrayNodeDataTextView.setText(String.valueOf(temp.peek()));
                if (temp.size() == n) {
                    node.arrayNodeCardView.setCardBackgroundColor(context.getColor(COLOR_TARGET_MATCHED));
                } else {
                    node.arrayNodeCardView.setCardBackgroundColor(context.getColor(COLOR_DEFAULT));
                }
                layout2.addView(node.getRoot());
                temp.pop();
            }
            rightLayout.addView(layout2);
        } else if (Objects.equals(param, POP_OPERATION)){
            textView.setText("POP\nOPERATION");

            Stack<Integer> temp = new Stack<>();
            temp.addAll(st);

            LinearLayout layout1 = new LinearLayout(context);
            layout1.setOrientation(LinearLayout.VERTICAL);
            layout1.setBackgroundResource(R.drawable.ic_stack_background);
            layout1.setPadding(5, 80, 5, 5);


            int n = temp.size();
            while (!temp.empty()) {
                ItemStackNodeBinding node = ItemStackNodeBinding.inflate(LayoutInflater.from(context));
                node.arrayNodeDataTextView.setText(String.valueOf(temp.peek()));
                if (temp.size() == n) {
                    node.arrayNodeCardView.setCardBackgroundColor(context.getColor(COLOR_TARGET_MATCHED));
                } else {
                    node.arrayNodeCardView.setCardBackgroundColor(context.getColor(COLOR_DEFAULT));
                }
                layout1.addView(node.getRoot());
                temp.pop();
            }
            leftLayout.addView(layout1);

            temp.clear();
            temp.addAll(st);
            temp.pop();

            LinearLayout layout2 = new LinearLayout(context);
            layout2.setOrientation(LinearLayout.VERTICAL);
            layout2.setBackgroundResource(R.drawable.ic_stack_background);
            layout2.setPadding(5, 80, 5, 5);

            while (!temp.empty()) {
                ItemStackNodeBinding node = ItemStackNodeBinding.inflate(LayoutInflater.from(context));
                node.arrayNodeDataTextView.setText(String.valueOf(temp.peek()));
                node.arrayNodeCardView.setCardBackgroundColor(context.getColor(COLOR_DEFAULT));
                layout2.addView(node.getRoot());
                temp.pop();
            }
            rightLayout.addView(layout2);
        }
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

        midLayout.addView(textView);

        parent.addView(leftLayout);
        parent.addView(midLayout);
        parent.addView(rightLayout);

        return parent;
    }
}
