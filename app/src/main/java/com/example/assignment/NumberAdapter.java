package com.example.assignment;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;
import java.util.Objects;
import java.util.Set;

public class NumberAdapter extends BaseAdapter {

    private  Context context;
    private List<Integer> numbers;
   private Set<Integer> highlightNumbers;

    public NumberAdapter(Context context, List<Integer> numbers, Set<Integer> highlightNumbers){
        this.context = context;
        this.numbers = numbers;
        this.highlightNumbers = highlightNumbers;


    }

    @Override
    public int getCount() {
        return numbers.size();
    }

    @Override
    public Object getItem(int position) {
        return numbers.get(position);

    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;

        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.grid_layout, parent, false);
            viewHolder = new ViewHolder(convertView);
            viewHolder.numberTextView = convertView.findViewById(R.id.numberTextView);
            convertView.setTag(viewHolder);

        }else {
            viewHolder = (ViewHolder) convertView.getTag();

        }

        int number = numbers.get(position);
        viewHolder.numberTextView.setText(String.valueOf(number));

        if (highlightNumbers.contains(number)){
            viewHolder.numberTextView.setBackgroundColor(Color.YELLOW);

        }else {
            viewHolder.numberTextView.setBackgroundColor(Color.TRANSPARENT);
        }

        return convertView;
    }

    private class ViewHolder extends RecyclerView.ViewHolder {

        TextView numberTextView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
