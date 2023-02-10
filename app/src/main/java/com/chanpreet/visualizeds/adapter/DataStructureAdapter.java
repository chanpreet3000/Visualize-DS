package com.chanpreet.visualizeds.adapter;

import android.content.Context;
import android.content.res.ColorStateList;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.chanpreet.visualizeds.R;
import com.chanpreet.visualizeds.classes.DataManager;
import com.chanpreet.visualizeds.classes.DataStructure;
import com.chanpreet.visualizeds.classes.DataStructureAlgorithm;
import com.chanpreet.visualizeds.classes.DataStructureTopic;
import com.chanpreet.visualizeds.databinding.ItemDataStructureBinding;
import com.chanpreet.visualizeds.utils.Util;

import java.util.List;
import java.util.Locale;
import java.util.Set;

public class DataStructureAdapter extends RecyclerView.Adapter<DataStructureAdapter.DSItemViewHolder> {

    private final List<DataStructure> dataStructureList;
    private onClickListener listener;
    private final Context context;

    public DataStructureAdapter(Context context, List<DataStructure> dataStructureList) {
        this.context = context;
        this.dataStructureList = dataStructureList;
    }

    @NonNull
    @Override
    public DSItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemDataStructureBinding binding = ItemDataStructureBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new DSItemViewHolder(binding);
    }

    private Float helper(DataStructure dataStructure, Set<String> set) {
        int total = 0;
        int ans = 0;
        for (DataStructureTopic topic :
                dataStructure.getDataStructureTopics()) {
            for (DataStructureAlgorithm algorithm :
                    topic.dataStructureAlgorithms()) {
                if (algorithm.getVisualizeClass() == null) continue;
                if (set.contains(algorithm.getName())) {
                    ans++;
                }
                total++;
            }
        }
        return (ans * 100f) / total;
    }

    @Override
    public void onBindViewHolder(@NonNull DSItemViewHolder holder, int position) {
        DataStructure data = dataStructureList.get(position);
        holder.binding.titleTextView.setText(data.getName());
        holder.binding.difficultyTextView.setText(data.getDifficulty().toString());
        Integer color = Util.listOfColors.get(position % (Util.listOfColors.size()));
        holder.binding.cardView.setImageTintList(ColorStateList.valueOf(context.getColor(color)));
        if (data.getIcon() != null) {
            holder.binding.dataStructureIcon.setImageResource(data.getIcon());
        }

        //
        Set<String> set = DataManager.getInstance().getVisualizationSet();

        float percentage = helper(dataStructureList.get(position), set);
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
        return dataStructureList.size();
    }

    public void setOnClickListener(onClickListener listener) {
        this.listener = listener;
    }

    public class DSItemViewHolder extends RecyclerView.ViewHolder {
        public ItemDataStructureBinding binding;

        public DSItemViewHolder(@NonNull ItemDataStructureBinding binding) {
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

