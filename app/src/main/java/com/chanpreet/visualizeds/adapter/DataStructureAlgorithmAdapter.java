package com.chanpreet.visualizeds.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.chanpreet.visualizeds.R;
import com.chanpreet.visualizeds.classes.DataManager;
import com.chanpreet.visualizeds.classes.DataStructureAlgorithm;
import com.chanpreet.visualizeds.databinding.ItemDataStructureAlgorithmBinding;
import com.chanpreet.visualizeds.utils.Util;

import java.util.List;
import java.util.Set;

public class DataStructureAlgorithmAdapter extends RecyclerView.Adapter<DataStructureAlgorithmAdapter.DSItemViewHolder> {

    private final Context context;
    private final List<DataStructureAlgorithm> dataStructureAlgorithmList;
    private onClickListener theoryListener;
    private onClickListener visualizeListener;

    public DataStructureAlgorithmAdapter(Context context, List<DataStructureAlgorithm> dataStructureAlgorithmList) {
        this.context = context;
        this.dataStructureAlgorithmList = dataStructureAlgorithmList;
    }

    @NonNull
    @Override
    public DSItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemDataStructureAlgorithmBinding binding = ItemDataStructureAlgorithmBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new DSItemViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull DSItemViewHolder holder, int position) {

        DataStructureAlgorithm data = dataStructureAlgorithmList.get(position);

        holder.binding.titleTextView.setText(data.getName());
        holder.binding.difficultyTextView.setText(data.getDifficulty().toString());

        Integer color = Util.listOfColors.get(position % (Util.listOfColors.size()));

        holder.binding.backgroundCardView1.setBackgroundColor(context.getColor(color));
        holder.binding.backgroundCardView2.setImageResource(data.getIcon());

        holder.binding.iconCardView.setCardBackgroundColor(context.getColor(color));
        holder.binding.iconImageView.setImageResource(data.getIcon());

        Set<String> set = DataManager.getInstance().getVisualizationSet();
        if (dataStructureAlgorithmList.get(position).getVisualizeClass() != null) {
            if (set.contains(dataStructureAlgorithmList.get(position).getName())) {
                holder.binding.completedImageView.setImageResource(R.drawable.ic_baseline_check_24);
            } else {
                holder.binding.completedImageView.setImageResource(R.drawable.ic_sharp_close_24);
            }
        } else {
            holder.binding.completedImageView.setImageDrawable(null);
        }
    }

    @Override
    public int getItemCount() {
        return dataStructureAlgorithmList.size();
    }

    public void setTheoryOnClickListener(onClickListener listener) {
        this.theoryListener = listener;
    }

    public void setVisualizeListenerOnClickListener(onClickListener listener) {
        this.visualizeListener = listener;
    }

    public class DSItemViewHolder extends RecyclerView.ViewHolder {
        public ItemDataStructureAlgorithmBinding binding;

        public DSItemViewHolder(@NonNull ItemDataStructureAlgorithmBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
            //OnClick Listener for the item View
            binding.theoryBtn.setOnClickListener(v -> {
                if (theoryListener == null) return;
                theoryListener.onCLick(getAdapterPosition());
            });

            binding.visualizeBtn.setOnClickListener(v -> {
                if (visualizeListener == null) return;
                visualizeListener.onCLick(getAdapterPosition());
            });
        }
    }

    public interface onClickListener {
        void onCLick(int position);
    }
}

