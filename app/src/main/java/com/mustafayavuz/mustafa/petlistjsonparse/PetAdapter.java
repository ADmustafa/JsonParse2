package com.mustafayavuz.mustafa.petlistjsonparse;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class PetAdapter extends RecyclerView.Adapter<PetAdapter.PetViewHolder> {

    private Context myContext;
    private ArrayList<ModelPetItem> myPetList;

    public PetAdapter(Context context, ArrayList<ModelPetItem> petList) {
        myContext = context;
        myPetList = petList;
    }

    @NonNull
    @Override
    public PetViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(myContext).inflate(R.layout.recyclerview_item, viewGroup, false);
        return new PetViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull PetViewHolder holder, int position) {
        ModelPetItem petItem = myPetList.get(position);

        String imageUrl = petItem.getMyPhoto();
        String username = petItem.getMyUsername();
        String species = petItem.getMySpecies();
        String favFoods = petItem.getMyFavFoods();
        int birthDay = petItem.getMyBirthDay();

        holder.myTextViewPetAdi.setText(myContext.getString(R.string.pet_adi) + username);
        holder.myTextViewSpecies.setText("Türü: " + species);
        holder.myTextViewFavFoods.setText("Fav Yemekleri: " + favFoods.toString());
        holder.myTextViewBirthDay.setText("Doğum Tarihi: " + birthDay);
        Picasso.with(myContext).load(imageUrl).into(holder.myImageViewPhoto);
    }

    @Override
    public int getItemCount() {
        return myPetList.size();
    }

    public class PetViewHolder extends RecyclerView.ViewHolder {

        public ImageView myImageViewPhoto;
        public TextView myTextViewPetAdi;
        public TextView myTextViewSpecies;
        public TextView myTextViewFavFoods;
        public TextView myTextViewBirthDay;

        public PetViewHolder(View itemView) {
            super(itemView);

            myImageViewPhoto = itemView.findViewById(R.id.ivObject);
            myTextViewPetAdi = itemView.findViewById(R.id.tvUsername);
            myTextViewSpecies = itemView.findViewById(R.id.tvSpecies);
            myTextViewFavFoods = itemView.findViewById(R.id.tvFavFoods);
            myTextViewBirthDay = itemView.findViewById(R.id.tvBirthDay);
        }
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////

}

