package com.simona.sp3;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolderFreeSpace> {

    ArrayList<Cell> spaces;

    public Adapter(ArrayList<Cell> spaces) {
        this.spaces = spaces;
    }

    class ViewHolderFreeSpace extends RecyclerView.ViewHolder{
        TextView letterSpaceTV;
        public ViewHolderFreeSpace(@NonNull View itemView) {
            super(itemView);
            letterSpaceTV = itemView.findViewById(R.id.freeSpaceTextView);
        }
    }

    @NonNull
    @Override
    public ViewHolderFreeSpace onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolderFreeSpace(LayoutInflater.from(parent.getContext()).inflate(R.layout.free_space, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderFreeSpace holder, int position) {
        Cell space = spaces.get(position);
        holder.letterSpaceTV.setText(space.getValue());
    }

    @Override
    public int getItemCount() {
        return spaces.size();
    }

    public void setCells(ArrayList<Cell> spaces) {
        this.spaces = spaces;
        notifyDataSetChanged();
    }

}
