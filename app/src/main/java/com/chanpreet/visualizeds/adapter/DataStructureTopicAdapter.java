package com.chanpreet.visualizeds.adapter;

import android.content.Context;
import android.content.res.ColorStateList;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.chanpreet.visualizeds.R;
import com.chanpreet.visualizeds.classes.DataManager;
import com.chanpreet.visualizeds.classes.DataStructureAlgorithm;
import com.chanpreet.visualizeds.utils.Util;
import com.chanpreet.visualizeds.classes.DataStructureTopic;
import com.chanpreet.visualizeds.databinding.ItemDataStructureTopicBinding;

import java.util.List;
import java.util.Locale;
import java.util.Set;

public class DataStructureTopicAdapter extends RecyclerView.Adapter<DataStructureTopicAdapter.DSItemViewHolder> {

    private final Context context;
    private final List<DataStructureTopic> dataStructureTopicList;
    private onClickListener listener;

    public DataStructureTopicAdapter(Context context, List<DataStructureTopic> dataStructureTopicList) {
        this.context = context;
        this.dataStructureTopicList = dataStructureTopicList;
    }

    @NonNull
    @Override
    public DSItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemDataStructureTopicBinding binding = ItemDataStructureTopicBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new DSItemViewHolder(binding);
    }

    private Float helper(DataStructureTopic dataStructureTopic, Set<String> set) {
        int total = 0;
        int ans = 0;
        for (DataStructureAlgorithm algorithm :
                dataStructureTopic.dataStructureAlgorithms()) {
            if (algorithm.getVisualizeClass() == null) continue;
            if (set.contains(algorithm.getName())) {
                ans++;
            }
            total++;
        }
        return (ans * 100f) / total;
    }

    @Override
    public void onBindViewHolder(@NonNull DSItemViewHolder holder, int position) {
        DataStructureTopic data = dataStructureTopicList.get(position);
        holder.binding.titleTextView.setText(data.getName());
        holder.binding.difficultyTextView.setText(data.getDifficulty().toString());
        Integer color = Util.listOfColors.get(position % (Util.listOfColors.size()));
        holder.binding.cardView.setImageTintList(ColorStateList.valueOf(context.getColor(color)));
        if (data.getIcon() != null) {
            holder.binding.dataStructureIcon.setImageResource(data.getIcon());
        }


        //
        Set<String> set = DataManager.getInstance().getVisualizationSet();

        float percentage = helper(dataStructureTopicList.get(position), set);
        holder.binding.progressBar.setProgress((int) percentage, true);
        String text = String.format(Locale.US, "%.1f", percentage) + "%";
        holder.binding.progressTextView.setText(text);
        if (percentage < 40) {
            holder.binding.progressBar.setIndicatorColor(context.getColor(R.color.blood_red));
        } else if (percentage < 70) {
            holder.binding.progressBar.setIndicatorColor(context.getColor(R.color.yellow));
        } else {
            holder.binding.progressBar.setIndicatorColor(context.getColor(R.color.green));
        }
    }

    @Override
    public int getItemCount() {
        return dataStructureTopicList.size();
    }

    public void setOnClickListener(onClickListener listener) {
        this.listener = listener;
    }

    public class DSItemViewHolder extends RecyclerView.ViewHolder {
        public ItemDataStructureTopicBinding binding;

        public DSItemViewHolder(@NonNull ItemDataStructureTopicBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
            //OnClick Listener for the item View
            binding.getRoot().setOnClickListener(v -> {
                if (listener == null) return;
                listener.onCLick(getAdapterPosition());
            });
        }
    }

    public interface onClickListener {
        void onCLick(int position);
    }
}

