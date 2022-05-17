package com.example.visualizeds.data_structure.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.visualizeds.R;
import com.example.visualizeds.data_structure.classes.DataStructure;

import java.util.List;

public class DataStructureAdapter extends RecyclerView.Adapter<DataStructureAdapter.DSItemViewHolder> {

    private final List<DataStructure> list;
    private onClickListener listener;
    private final Context context;

    public DataStructureAdapter(Context context, List<DataStructure> list) {
        this.context = context;
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
        DataStructure data = list.get(position);
        holder.titleTextView.setText(data.getName());
        holder.difficultyTextView.setText(data.getDifficulty().toString());
        holder.dataStructureIconCard.setCardBackgroundColor(AppCompatResources.getColorStateList(context, data.getIconColor()));
        if (data.getIcon() != null) {
            holder.dataStructureIcon.setImageDrawable(AppCompatResources.getDrawable(context, data.getIcon()));
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
        public TextView titleTextView;
        public TextView difficultyTextView;
        public CardView dataStructureIconCard;
        public ImageView dataStructureIcon;

        public DSItemViewHolder(@NonNull View itemView) {
            super(itemView);

            //Animation
            itemView.setAnimation(AnimationUtils.loadAnimation(context, R.anim.left_slide_in_fade_in));

            //Referencing
            titleTextView = itemView.findViewById(R.id.titleTextView);
            difficultyTextView = itemView.findViewById(R.id.difficultyTextView);
            dataStructureIconCard = itemView.findViewById(R.id.dataStructureIconCard);
            dataStructureIcon = itemView.findViewById(R.id.dataStructureIcon);

            //OnClick Listener for the item View
            itemView.setOnClickListener(v -> {
                if (listener == null) return;
                listener.onCLick(getAdapterPosition());
            });
        }
    }

    public interface onClickListener {
        void onCLick(int position);
    }
}

