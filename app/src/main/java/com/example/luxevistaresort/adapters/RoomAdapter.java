package com.example.luxevistaresort.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.luxevistaresort.R;
import com.example.luxevistaresort.models.Room;

import java.util.List;
import java.util.Locale;

public class RoomAdapter extends RecyclerView.Adapter<RoomAdapter.RoomViewHolder> {
    private List<Room> rooms;
    private OnRoomBookClickListener listener;

    public interface OnRoomBookClickListener {
        void onRoomBookClick(Room room);
    }

    public RoomAdapter(List<Room> rooms, OnRoomBookClickListener listener) {
        this.rooms = rooms;
        this.listener = listener;
    }

    @NonNull
    @Override
    public RoomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_room, parent, false);
        return new RoomViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RoomViewHolder holder, int position) {
        Room room = rooms.get(position);
        holder.bind(room);
    }

    @Override
    public int getItemCount() {
        return rooms.size();
    }

    class RoomViewHolder extends RecyclerView.ViewHolder {
        private ImageView ivRoomImage;
        private TextView tvRoomType, tvViewType, tvCapacity, tvPrice;
        private Button btnBookNow;

        RoomViewHolder(@NonNull View itemView) {
            super(itemView);
            ivRoomImage = itemView.findViewById(R.id.ivRoomImage);
            tvRoomType = itemView.findViewById(R.id.tvRoomType);
            tvViewType = itemView.findViewById(R.id.tvViewType);
            tvCapacity = itemView.findViewById(R.id.tvCapacity);
            tvPrice = itemView.findViewById(R.id.tvPrice);
            btnBookNow = itemView.findViewById(R.id.btnBookNow);
        }

        void bind(Room room) {
            ivRoomImage.setImageResource(room.getImageResId());
            tvRoomType.setText(room.getType());
            tvViewType.setText(room.getViewType());
            tvCapacity.setText(String.format("Capacity: %d persons • %d beds", 
                room.getCapacity(), room.getBedCount()));
            tvPrice.setText(String.format(Locale.getDefault(), 
                "LKR %.2f per night", room.getPrice()));
            
            btnBookNow.setOnClickListener(v -> listener.onRoomBookClick(room));
            btnBookNow.setEnabled(room.isAvailable());
        }
    }
} 