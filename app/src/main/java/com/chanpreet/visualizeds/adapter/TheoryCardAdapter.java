package com.chanpreet.visualizeds.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.chanpreet.visualizeds.databinding.ItemTheoryCardBinding;

import java.util.List;

public class TheoryCardAdapter extends RecyclerView.Adapter<TheoryCardAdapter.TheoryCardViewHolder> {

    private final Context context;
    private final List<String> titleList;
    private final List<String> descriptionList;
    private final List<Integer> imageList;

    public TheoryCardAdapter(Context context, List<String> titleList, List<String> descriptionList, List<Integer> imageList) {
        this.context = context;
        this.titleList = titleList;
        this.descriptionList = descriptionList;
        this.imageList = imageList;
    }

    @NonNull
    @Override
    public TheoryCardViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemTheoryCardBinding binding = ItemTheoryCardBinding.inflate(LayoutInflater.from(context), parent, false);
        return new TheoryCardViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull TheoryCardViewHolder holder, int position) {
        holder.binding.titleTextView.setText(titleList.get(position));
        holder.binding.descriptionTextView.setText(descriptionList.get(position));
        holder.binding.imageView.setImageResource(imageList.get(position));
    }

    @Override
    public int getItemCount() {
        return titleList.size();
    }

    public static class TheoryCardViewHolder extends RecyclerView.ViewHolder {
        public ItemTheoryCardBinding binding;

        public TheoryCardViewHolder(@NonNull ItemTheoryCardBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }

}
