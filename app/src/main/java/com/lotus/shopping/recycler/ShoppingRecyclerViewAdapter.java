package com.lotus.shopping.recycler;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.lotus.shopping.R;
import com.lotus.shopping.activity.MainActivity;
import com.lotus.shopping.data.ShopData;
import com.lotus.shopping.module.Config;
import com.lotus.shopping.module.DialogHelper;
import com.lotus.shopping.module.SQLiteControler;

import java.util.ArrayList;

public class ShoppingRecyclerViewAdapter  extends RecyclerView.Adapter<ShoppingRecyclerViewAdapter.ViewHolder>{
    ArrayList<ShopData> listData;
    Context context;

    public ShoppingRecyclerViewAdapter(ArrayList<ShopData> listData, Context context) {
        this.listData = listData;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.recycler_shop,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ShopData data=listData.get(position);
        holder.checkBox.setChecked(data.getCheck().equals("true"));
        holder.itemTextView.setText(data.getItem());
        holder.countTextView.setText("x"+data.getCount());
        int plus=Integer.parseInt(data.getCount())*Integer.parseInt(data.getMoney());
        holder.moneyTextView.setText(plus+"元");
        Config.BuyMoney=Config.BuyMoney+plus;
        holder.addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogHelper.ChangeDialog(context,data.getItem(),data.getCount(),data.getMoney(),data.getCreate_at());
            }
        });
        holder.deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogHelper.DeleteDialog(context,data.getItem(),data.getCount(),data.getMoney(),data.getCreate_at());
            }
        });
        holder.checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (holder.checkBox.isChecked()){
                    SQLiteControler.updateCheckShop(context,data.getCreate_at(),"true");
                }else {
                    SQLiteControler.updateCheckShop(context,data.getCreate_at(),"false");
                }
            }
        });
        holder.layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (holder.checkBox.isChecked()){
                    holder.checkBox.setChecked(false);
                    SQLiteControler.updateCheckShop(context,data.getCreate_at(),"false");
                }else {
                    holder.checkBox.setChecked(true);
                    SQLiteControler.updateCheckShop(context,data.getCreate_at(),"true");
                }
            }
        });
        if (position==listData.size()-1){
            ((Activity)context).runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    MainActivity.estimatedTextView.setText(String.valueOf(Config.BuyMoney));
                    MainActivity.mainActivityControl.countSpread();
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return listData.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        Button addBtn,deleteBtn;
        CheckBox checkBox;
        TextView itemTextView,countTextView,moneyTextView;
        LinearLayout layout;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            addBtn=itemView.findViewById(R.id.recycler_add_btn);
            deleteBtn=itemView.findViewById(R.id.recycler_delete_btn);
            checkBox=itemView.findViewById(R.id.recycler_checkbox);
            itemTextView=itemView.findViewById(R.id.recycler_item_textview);
            countTextView=itemView.findViewById(R.id.recycler_count_textview);
            moneyTextView=itemView.findViewById(R.id.recycler_money_textview);
            layout=itemView.findViewById(R.id.recycler_layout);
        }
    }
}
