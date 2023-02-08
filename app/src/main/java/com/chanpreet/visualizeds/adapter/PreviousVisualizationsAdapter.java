package com.chanpreet.visualizeds.adapter;

import android.content.Context;
import android.content.res.ColorStateList;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.chanpreet.visualizeds.builder.TextBuilder;
import com.chanpreet.visualizeds.classes.VisualizationInfo;
import com.chanpreet.visualizeds.databinding.ItemPreviousVisualizationBinding;
import com.chanpreet.visualizeds.utils.Util;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

public class PreviousVisualizationsAdapter extends RecyclerView.Adapter<PreviousVisualizationsAdapter.PreviousVisualizations> {

    private final Context context;
    private final List<VisualizationInfo> list;

    public PreviousVisualizationsAdapter(Context context, List<VisualizationInfo> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public PreviousVisualizations onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemPreviousVisualizationBinding binding = ItemPreviousVisualizationBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new PreviousVisualizations(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull PreviousVisualizations holder, int position) {
        VisualizationInfo data = list.get(position);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("EEEE", Locale.US);
        String day = simpleDateFormat.format(data.getTime());
        //
        simpleDateFormat = new SimpleDateFormat("dd LLL yyyy\nKK:mm:ss aaa", Locale.US);
        String dateTime = simpleDateFormat.format(data.getTime()).toString();

        Integer color = Util.listOfColors.get(position % (Util.listOfColors.size()));
        holder.binding.cardView.setCardBackgroundColor(ColorStateList.valueOf(context.getColor(color)));

        holder.binding.indexTextView.setText(String.valueOf(position + 1));
        holder.binding.dayTextView.setText(day);
        holder.binding.timeTextView.setText(dateTime);
        holder.binding.nameTextView.setText(data.getName());
        holder.binding.informationTextView.setText(TextBuilder.makeBulletList(data.getInformation()));

        holder.binding.nameTextView.setTextColor(ColorStateList.valueOf(context.getColor(color)));
        holder.binding.informationTextView.setTextColor(ColorStateList.valueOf(context.getColor(color)));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    public static class PreviousVisualizations extends RecyclerView.ViewHolder {
        public ItemPreviousVisualizationBinding binding;

        public PreviousVisualizations(@NonNull ItemPreviousVisualizationBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}

