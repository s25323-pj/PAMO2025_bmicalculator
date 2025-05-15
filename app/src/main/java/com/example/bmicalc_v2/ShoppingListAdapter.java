package com.example.bmicalc_v2;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Paint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class ShoppingListAdapter extends RecyclerView.Adapter<ShoppingListAdapter.ViewHolder> {

    private final List<ShoppingItem> shoppingItems;
    private final SharedPreferences sharedPreferences;
    private static final String PREFS_NAME = "ShoppingListPrefs";

    public ShoppingListAdapter(Context context, List<ShoppingItem> shoppingItems) {
        this.shoppingItems = shoppingItems;
        this.sharedPreferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);

        loadSavedState();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.shopping_list_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ShoppingItem item = shoppingItems.get(position);

        boolean wasPurchased = sharedPreferences.getBoolean(item.getUniqueId(), false);
        item.setPurchased(wasPurchased);

        holder.itemText.setText(item.getName());
        holder.checkBox.setChecked(item.isPurchased());

        updateItemAppearance(holder, item.isPurchased());

        holder.checkBox.setOnClickListener(v -> {
            boolean isChecked = holder.checkBox.isChecked();
            item.setPurchased(isChecked);

            saveItemState(item.getUniqueId(), isChecked);

            updateItemAppearance(holder, isChecked);
        });
    }

    private void updateItemAppearance(ViewHolder holder, boolean isPurchased) {
        if (isPurchased) {
            holder.itemText.setPaintFlags(holder.itemText.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
        } else {
            holder.itemText.setPaintFlags(holder.itemText.getPaintFlags() & ~Paint.STRIKE_THRU_TEXT_FLAG);
        }
    }

    private void saveItemState(String itemId, boolean isPurchased) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(itemId, isPurchased);
        editor.apply();
    }

    private void loadSavedState() {
        for (ShoppingItem item : shoppingItems) {
            boolean wasPurchased = sharedPreferences.getBoolean(item.getUniqueId(), false);
            item.setPurchased(wasPurchased);
        }
    }

    @Override
    public int getItemCount() {
        return shoppingItems.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView itemText;
        CheckBox checkBox;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            itemText = itemView.findViewById(R.id.itemText);
            checkBox = itemView.findViewById(R.id.itemCheckBox);
        }
    }
}