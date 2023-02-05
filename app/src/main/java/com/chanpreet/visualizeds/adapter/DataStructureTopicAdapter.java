package com.chanpreet.visualizeds.adapter;

import android.content.Context;
import android.content.res.ColorStateList;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.chanpreet.visualizeds.utils.Util;
import com.chanpreet.visualizeds.classes.DataStructureTopic;
import com.chanpreet.visualizeds.databinding.ItemDataStructureTopicBinding;

import java.util.List;

public class DataStructureTopicAdapter extends RecyclerView.Adapter<DataStructureTopicAdapter.DSItemViewHolder> {

    private final Context context;
    private final List<DataStructureTopic> list;
    private onClickListener listener;

    public DataStructureTopicAdapter(Context context, List<DataStructureTopic> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public DSItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemDataStructureTopicBinding binding = ItemDataStructureTopicBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new DSItemViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull DSItemViewHolder holder, int position) {
        DataStructureTopic data = list.get(position);
        holder.binding.titleTextView.setText(data.getName());
        holder.binding.difficultyTextView.setText(data.getDifficulty().toString());
        Integer color = Util.listOfColors.get(position % (Util.listOfColors.size()));
        holder.binding.cardView.setImageTintList(ColorStateList.valueOf(context.getColor(color)));
        if (data.getIcon() != null) {
            holder.binding.dataStructureIcon.setImageResource(data.getIcon());
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void setOnClickListener(onClickListener listener) {
        this.listener = listener;
    }

    public class DSItemViewHolder extends RecyclerView.ViewHolder {
        public ItemDataStructureTopicBinding binding;

        public DSItemViewHolder(@NonNull ItemDataStructureTopicBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
            //Animation
//            binding.getRoot().setAnimation(AnimationUtils.loadAnimation(context, R.anim.left_slide_in_fade_in));


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

