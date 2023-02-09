package com.chanpreet.visualizeds.adapter;

import android.content.Context;
import android.content.res.ColorStateList;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.chanpreet.visualizeds.classes.StepCard;
import com.chanpreet.visualizeds.databinding.ItemNoDataErrorBinding;
import com.chanpreet.visualizeds.databinding.ItemStepCardBinding;
import com.chanpreet.visualizeds.databinding.ItemStepCardDescriptionBinding;
import com.chanpreet.visualizeds.utils.Util;

import java.util.ArrayList;
import java.util.List;

public class StepCardAdapter extends RecyclerView.Adapter<StepCardAdapter.StepCardViewHolder> {

    private final Context context;
    private List<StepCard> stepCardList;

    public StepCardAdapter(Context context) {
        this.context = context;
        stepCardList = new ArrayList<>();
    }

    public void addStepCard(StepCard stepCard) {
        if (stepCardList.get(0).getData() == null) {
            stepCardList.clear();
        }
        this.stepCardList.add(stepCard);
        notifyDataSetChanged();
    }

    public void setStepCardList(List<StepCard> stepCardList) {
        this.stepCardList = stepCardList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public StepCardViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemStepCardBinding binding = ItemStepCardBinding.inflate(LayoutInflater.from(context), parent, false);
        return new StepCardViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull StepCardViewHolder holder, int position) {
        holder.binding.titleTextView.setText(stepCardList.get(position).getTitle());
        holder.binding.descriptionLinearLayout.removeAllViews();

        for (int i = 0; i < stepCardList.get(position).getDescription().size(); i++) {
            String description = stepCardList.get(position).getDescription().get(i);
            ItemStepCardDescriptionBinding binding2 = ItemStepCardDescriptionBinding.inflate(LayoutInflater.from(context));

            binding2.textView.setText(description);
            Integer color = Util.listOfColors.get((i + 1) % (Util.listOfColors.size()));
            binding2.cardView.setCardBackgroundColor(ColorStateList.valueOf(context.getColor(color)));

            holder.binding.descriptionLinearLayout.addView(binding2.getRoot());
        }

        holder.binding.dataNodeHolder.removeAllViews();
        if (stepCardList.get(position).getData() != null) {
            if (stepCardList.get(position).getData().getParent() != null) {
                ((ViewGroup) stepCardList.get(position).getData().getParent()).removeView(stepCardList.get(position).getData());
            }
            holder.binding.dataNodeHolder.addView(stepCardList.get(position).getData());
        } else {
            holder.binding.dataNodeHolder.addView(ItemNoDataErrorBinding.inflate(LayoutInflater.from(context)).getRoot());
        }
    }

    @Override
    public int getItemCount() {
        return stepCardList.size();
    }

    public static class StepCardViewHolder extends RecyclerView.ViewHolder {
        public ItemStepCardBinding binding;

        public StepCardViewHolder(@NonNull ItemStepCardBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
