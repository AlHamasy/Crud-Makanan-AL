package com.catatanasad.crudmakanan;

import android.content.Context;
import android.content.Intent;
import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.catatanasad.crudmakanan.model.DataItem;

import java.util.List;

public class MakananAdapter extends RecyclerView.Adapter<MakananAdapter.ViewHolder> {

    List<DataItem> dataItems;
    Context context;

    public MakananAdapter(List<DataItem> dataItems, Context context) {
        this.dataItems = dataItems;
        this.context = context;
    }

    @NonNull
    @Override
    public MakananAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_makanan, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MakananAdapter.ViewHolder holder, final int position) {
        holder.tvNama.setText(dataItems.get(position).getMenuNama());
        holder.tvHarga.setText(dataItems.get(position).getMenuHarga());
        Glide.with(context).load(dataItems.get(position).getMenuGambar()).into(holder.imgMakanan);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(context, UpdateDeleteActivity.class);
                intent.putExtra("DATA_ITEM", dataItems.get(position));
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return dataItems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView tvNama, tvHarga;
        private ImageView imgMakanan;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tvHarga = itemView.findViewById(R.id.item_harga);
            tvNama = itemView.findViewById(R.id.item_nama);
            imgMakanan = itemView.findViewById(R.id.item_image);

        }
    }
}
