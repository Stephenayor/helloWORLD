package com.example.android.helloworld;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


public class CapitalsOptionListAdapter extends RecyclerView.Adapter<CapitalsOptionListAdapter.CapitalOptionsViewHolder> {
    private CapitalsModel capitalsModel;
    private LayoutInflater layoutInflater;
    private Context context;
    private ItemClickListener mClickListener;

    public CapitalsOptionListAdapter(Context context,  ItemClickListener clickListener, CapitalsModel model) {
        this.capitalsModel = model;
        this.context = context;
        this.layoutInflater = LayoutInflater.from(context);
        this.mClickListener = clickListener;
    }


    @NonNull
    @Override
    public CapitalOptionsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = layoutInflater.inflate(R.layout.countries_list_item, parent, false);
        return new CapitalOptionsViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull CapitalOptionsViewHolder holder, int position) {
        //Get the required value
        holder.optionsText.setText(capitalsModel.getOptionsList().get(position));
    }

    @Override
    public int getItemCount() {
        return capitalsModel.getOptionsList().size();
    }



    public class CapitalOptionsViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public TextView optionsText;

        public CapitalOptionsViewHolder(@NonNull View itemView) {
            super(itemView);
            optionsText = itemView.findViewById(R.id.optionsButton);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (mClickListener != null) mClickListener.onItemClick(optionsText.getText().toString(), getAdapterPosition(), capitalsModel);

        }
    }

    // Parent Activity Will Implement this Method to Respond to Click Events
    public interface ItemClickListener {
        void onItemClick(String optionChosen, int position, CapitalsModel model);
    }
}
