package com.omeryildizce.kriptopara.adapter;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.omeryildizce.kriptopara.R;
import com.omeryildizce.kriptopara.model.CryptoModel;

import java.util.ArrayList;

public class CryptoAdapter extends RecyclerView.Adapter<CryptoAdapter.CryptoHolder> {
    private ArrayList<CryptoModel> cryptoModelList;
    private String[] colors = {"#F44336", "#E91E63", "#9C27B0", "#673AB7",
            "#3F51B5", "#2196F3", "#03A9F4", "#00BCD4",
            "#009688", "#4CAF50", "#8BC34A", "#CDDC39",
            "#FFEB3B", "#FFC107", "#FF9800", "#FF5722"
    };

    public CryptoAdapter(ArrayList<CryptoModel> cryptoModelList) {
        this.cryptoModelList = cryptoModelList;

    }

    @NonNull
    @Override
    public CryptoHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.row_layout, parent, false);

        return new CryptoHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull CryptoHolder holder, int position) {
        holder.bind(cryptoModelList.get(position), colors, position);
    }

    @Override
    public int getItemCount() {
        return cryptoModelList.size();
    }

    public class CryptoHolder extends RecyclerView.ViewHolder {
        TextView cryptoName;
        TextView cryptoPrice;

        public CryptoHolder(@NonNull View itemView) {
            super(itemView);


        }

        public void bind(CryptoModel cryptoModel, String[] colors, int position) {
            CardView cryptoCard = itemView.findViewById(R.id.cryptoCard);
            cryptoCard.setCardBackgroundColor(Color.parseColor(colors[position % 16]));
            cryptoName = itemView.findViewById(R.id.cryptoName);
            cryptoPrice = itemView.findViewById(R.id.cryptoPrice);
            cryptoName.setText(cryptoModel.currency);
            cryptoPrice.setText(cryptoModel.price);

        }

    }
}
