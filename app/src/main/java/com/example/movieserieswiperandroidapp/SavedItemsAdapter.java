package com.example.movieserieswiperandroidapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class SavedItemsAdapter extends RecyclerView.Adapter<SavedItemsAdapter.SavedItemsViewHolder> {
    public static Object SavedItemsViewHolder;
    private Context context;
    private List<SavedItemsModel> savedItemsModelList;


    public SavedItemsAdapter(Context applicationContext, List<SavedItemsModel> listSavedItems){
        this.context = applicationContext;
        this.savedItemsModelList = listSavedItems;
    }

    @NonNull
    @Override
    public SavedItemsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
        View v = (CardView) LayoutInflater.from(parent.getContext()).inflate(R.layout.saveditem_card, parent, false);
        SavedItemsViewHolder savedItemsViewHolder = new SavedItemsViewHolder(v);
        return savedItemsViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull SavedItemsViewHolder holder, int position){
        SavedItemsModel savedItemsModel = savedItemsModelList.get(position);

        holder.textItemName.setText(savedItemsModel.getName());
        holder.textItemGenre.setText(savedItemsModel.getGenre());
    }

    @Override
    public int getItemCount(){
        return savedItemsModelList.size();
    }

    public class SavedItemsViewHolder extends RecyclerView.ViewHolder{
        public TextView textItemName, textItemGenre;

        public SavedItemsViewHolder(View itemView){
            super(itemView);

            textItemName = itemView.findViewById(R.id.saved_title);
            textItemGenre = itemView.findViewById(R.id.saved_genre);
        }
    }

    public List<SavedItemsModel> getItems() {
        return savedItemsModelList;
    }

    public void setSavedItemsModelList(List<ItemModel> items) {
        this.savedItemsModelList = savedItemsModelList;
    }
}

