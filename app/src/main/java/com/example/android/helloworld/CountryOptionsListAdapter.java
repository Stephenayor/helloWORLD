package com.example.android.helloworld;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


public class CountryOptionsListAdapter extends RecyclerView.Adapter<CountryOptionsListAdapter.CountryOptionsViewHolder> {

    private List<CountryModel> modelList;
    private LayoutInflater layoutInflater;
    private Context mContext;
    private ItemClickListener mClickListener;

    public CountryOptionsListAdapter(Context context, List<CountryModel> modelList, ItemClickListener clickListener) {
        this.modelList = modelList;
        this.layoutInflater = LayoutInflater.from(context);
        this.mContext = context;
        this.mClickListener = clickListener;
    }


    @NonNull
    @Override
    public CountryOptionsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = layoutInflater.inflate(R.layout.countries_list_item, parent, false);
        return new CountryOptionsViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull CountryOptionsViewHolder holder, int position) {
        //Get the required value
        CountryModel countryModel = modelList.get(position);
        holder.optionsButton.setText(countryModel.getOptionList().get(position));
    }

    @Override
    public int getItemCount() {
        return modelList.size();
    }



    public class CountryOptionsViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public Button optionsButton;

        public CountryOptionsViewHolder(@NonNull View itemView) {
            super(itemView);
            optionsButton = itemView.findViewById(R.id.optionsButton);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (mClickListener != null) mClickListener.onItemClick(optionsButton.getText().toString(), getAdapterPosition());

        }
    }

    // parent activity will implement this method to respond to click events
    public interface ItemClickListener {
        void onItemClick(String option, int position);
    }
}
