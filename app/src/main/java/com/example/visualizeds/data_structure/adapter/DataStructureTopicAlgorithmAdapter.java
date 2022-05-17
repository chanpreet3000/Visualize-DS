package com.example.visualizeds.data_structure.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.visualizeds.R;
import com.example.visualizeds.data_structure.classes.DataStructureTopicAlgorithm;

import java.util.List;

public class DataStructureTopicAlgorithmAdapter extends RecyclerView.Adapter<DataStructureTopicAlgorithmAdapter.DSItemViewHolder> {

    private final List<DataStructureTopicAlgorithm> list;
    private onClickListener theoryListener;
    private onClickListener visualizeListener;

    public DataStructureTopicAlgorithmAdapter(List<DataStructureTopicAlgorithm> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public DSItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_data_structure_topic_algorithm, parent, false);
        return new DSItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DSItemViewHolder holder, int position) {
        holder.titleTextView.setText(list.get(position).getName());
        holder.difficultyTextView.setText(list.get(position).getDifficulty().name());
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
        public TextView titleTextView;
        public TextView difficultyTextView;
        public Button theoryBtn;
        public Button visualizeBtn;

        public DSItemViewHolder(@NonNull View itemView) {
            super(itemView);
            //referencing
            titleTextView = itemView.findViewById(R.id.title);
            difficultyTextView = itemView.findViewById(R.id.difficultyTextView);
            theoryBtn = itemView.findViewById(R.id.theoryBtn);
            visualizeBtn = itemView.findViewById(R.id.visualizeBtn);


            //OnClick Listener for the item View
            theoryBtn.setOnClickListener(v -> {
                if (theoryListener == null) return;
                theoryListener.onCLick(getAdapterPosition());
            });

            visualizeBtn.setOnClickListener(v -> {
                if (visualizeListener == null) return;
                visualizeListener.onCLick(getAdapterPosition());
            });
        }
    }

    public interface onClickListener {
        void onCLick(int position);
    }
}

