package com.mobytesac.cashflow.adapters;
/*
 *  Copyright (C) 3/17/17 eveR Vásquez.
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 *
 */

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mobytesac.cashflow.R;
import com.mobytesac.cashflow.model.Item;

import java.util.List;

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ItemViewHolder> {
    private static final String TAG = ItemAdapter.class.getSimpleName();
    private List<Item> items;

    public static class ItemViewHolder extends RecyclerView.ViewHolder {
        TextView text_monto;
        TextView text_tipo;

        public ItemViewHolder(View itemView) {
            super(itemView);
            text_monto = (TextView) itemView.findViewById(R.id.text_monto);
            text_tipo = (TextView) itemView.findViewById(R.id.text_tipo);
        }
    }

    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        return new ItemViewHolder(inflater.inflate(R.layout.item_row, parent, false));
    }

    @Override
    public void onBindViewHolder(ItemViewHolder holder, int position) {
        ItemViewHolder viewHolder = (ItemViewHolder) holder;
        Double monto = items.get(position).getMonto();
        String tipo = items.get(position).getTipo();
        viewHolder.text_monto.setText(String.format("S/. %.2f", monto));
        viewHolder.text_tipo.setText(tipo);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public void setItems(List<Item> items){
        this.items = items;
    }

    public void setItem(Item item){
        items.add(item);
        notifyDataSetChanged();
    }

    public void removeItem(int position){
        items.remove(position);
        notifyItemRemoved(position);
    }
}
