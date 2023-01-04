package com.chanpreet.visualizeds.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.chanpreet.visualizeds.classes.DataStructureAlgorithm;
import com.chanpreet.visualizeds.databinding.ItemDataStructureAlgorithmBinding;
import com.chanpreet.visualizeds.utils.DataStructureUtil;

import java.util.List;

public class DataStructureAlgorithmAdapter extends RecyclerView.Adapter<DataStructureAlgorithmAdapter.DSItemViewHolder> {

    private final Context context;
    private final List<DataStructureAlgorithm> list;
    private onClickListener theoryListener;
    private onClickListener visualizeListener;

    public DataStructureAlgorithmAdapter(Context context, List<DataStructureAlgorithm> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public DSItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemDataStructureAlgorithmBinding binding = ItemDataStructureAlgorithmBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new DSItemViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull DSItemViewHolder holder, int position) {
        DataStructureAlgorithm data = list.get(position);

        holder.binding.titleTextView.setText(data.getName());
        holder.binding.difficultyTextView.setText(data.getDifficulty().toString());

        Integer color = DataStructureUtil.listOfColors.get(position % (DataStructureUtil.listOfColors.size()));

        holder.binding.backgroundCardView1.setBackgroundColor(context.getColor(color));
        holder.binding.backgroundCardView2.setImageResource(data.getIcon());

        holder.binding.iconCardView.setCardBackgroundColor(context.getColor(color));
        holder.binding.iconImageView.setImageResource(data.getIcon());
    }

    @Override
    public int getItemCount() {
        return list.size();
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
            //Animation
//            binding.getRoot().setAnimation(AnimationUtils.loadAnimation(context, R.anim.left_slide_in_fade_in));

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

