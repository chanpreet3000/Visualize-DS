package com.example.visualizeds.data_structure.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.visualizeds.R;
import com.example.visualizeds.data_structure.classes.DataStructureTopic;

import java.util.List;

public class DataStructureTopicAdapter extends RecyclerView.Adapter<DataStructureTopicAdapter.DSItemViewHolder> {

    private final List<DataStructureTopic> list;
    private onClickListener listener;

    public DataStructureTopicAdapter(List<DataStructureTopic> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public DSItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_data_structure, parent, false);
        return new DSItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DSItemViewHolder holder, int position) {
        holder.titleTextView.setText(list.get(position).getName());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void setOnClickListener(onClickListener listener) {
        this.listener = listener;
    }

    public class DSItemViewHolder extends RecyclerView.ViewHolder {
        public TextView titleTextView;

        public DSItemViewHolder(@NonNull View itemView) {
            super(itemView);
            titleTextView = itemView.findViewById(R.id.titleTextView);
            //OnClick Listener for the item View
            itemView.setOnClickListener(v -> {
                if(listener == null) return;
                listener.onCLick(getAdapterPosition());
            });
        }
    }

    public interface onClickListener {
        void onCLick(int position);
    }
}

