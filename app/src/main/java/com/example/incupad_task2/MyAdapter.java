package com.example.incupad_task2;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context context;
    private List<String> originalData;
    private List<String> filteredData;

    MyAdapter(Context context,List<String> data){
        this.context = context;
        this.originalData = data;
        this.filteredData = new ArrayList<>(data);
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.recycler_item,parent,false);

        return new myViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        myViewHolder holder1 = (myViewHolder) holder;
        holder1.bind(filteredData.get(position));
    }

    @Override
    public int getItemCount() {
        return filteredData.size();
    }

    private static class myViewHolder extends RecyclerView.ViewHolder{
        TextView textView;
        public myViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.text_title);
        }
        void bind(String text){
            textView.setText(text);
        }
    }

    public void filter(String query) {
        filteredData.clear();
        if (TextUtils.isEmpty(query)) {
            filteredData.addAll(originalData);
        } else {
            for (String item : originalData) {
                if (item.toLowerCase().contains(query.toLowerCase())) {
                    filteredData.add(item);
                }
            }
        }
        notifyDataSetChanged();
    }
}
