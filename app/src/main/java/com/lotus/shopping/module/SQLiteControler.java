package com.lotus.shopping.module;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.lotus.shopping.R;
import com.lotus.shopping.activity.MainActivity;
import com.lotus.shopping.data.ShopData;
import com.lotus.shopping.recycler.ShoppingRecyclerViewAdapter;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;

public class SQLiteControler {

    public static void loadShop(Context context) {
        DialogHelper.WaitProgressDialog(context,"讀取資料","讀取資料中，請稍後...");
        ((Activity)context).runOnUiThread(new Runnable() {
            @Override
            public void run() {
                SQLiteHelper sqLiteHelper=new SQLiteHelper(context);
                SQLiteDatabase sql=sqLiteHelper.getWritableDatabase();
                String cmd="select * from shopinfo";
                Cursor cursor=sql.rawQuery(cmd,null);
                ArrayList<ShopData> arrayList=new ArrayList<>();
                while (cursor.moveToNext()){
                    String id = cursor.getString(0);
                    String item = cursor.getString(1);
                    String count = cursor.getString(2);
                    String money=cursor.getString(3);
                    String check_buy=cursor.getString(4);
                    String create_at=cursor.getString(5);
                    arrayList.add(new ShopData(item,count,money,check_buy,create_at));
                }
                cursor.close();
                sqLiteHelper.close();
                DialogHelper.waitProgressDialog.dismiss();
                setRecyclerView(arrayList,context);
            }
        });
    }
    public static void setRecyclerView(ArrayList<ShopData> data, Context context){
        DialogHelper.WaitProgressDialog(context,"顯示資料","顯示資料中，請稍後...");
        ((Activity)context).runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Collections.reverse(data);
                Config.BuyMoney=0;
                RecyclerView recyclerView=((Activity)context).findViewById(R.id.main_recyclerview);
                ShoppingRecyclerViewAdapter recyclerViewAdapter;
                recyclerView.setHasFixedSize(true);
                recyclerView.setLayoutManager(new LinearLayoutManager(context));
                recyclerViewAdapter = new ShoppingRecyclerViewAdapter(data, context);
                recyclerView.setAdapter(recyclerViewAdapter);
                DialogHelper.waitProgressDialog.dismiss();
                if (data.size()==0){
                    MainActivity.estimatedTextView.setText("0");
                    Config.BuyMoney=0;
                    MainActivity.mainActivityControl.countSpread();
                }
            }
        });
    }
    public static void saveShop(Context context,String item,String count,String money){
        DialogHelper.WaitProgressDialog(context,"儲存資料","儲存資料中，請稍後...");
        ((Activity)context).runOnUiThread(new Runnable() {
            @Override
            public void run() {
                SQLiteHelper sqLiteHelper=new SQLiteHelper(context);
                SQLiteDatabase sql=sqLiteHelper.getWritableDatabase();
                ContentValues values=new ContentValues();
                values.put("item",item);
                values.put("count",count);
                values.put("money",money);
                values.put("check_buy","false");
                Date date = new Date();
                @SuppressLint("SimpleDateFormat") SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
                values.put("created_at",dateFormat.format(date));
                sql.insert("shopinfo",null,values);
                sqLiteHelper.close();
                DialogHelper.waitProgressDialog.dismiss();
                loadShop(context);
            }
        });

    }
    public static void updateShop(Context context,String oldDate,String item,String count,String money){
        DialogHelper.WaitProgressDialog(context,"更新資料","更新資料中，請稍後...");
        ((Activity)context).runOnUiThread(new Runnable() {
            @Override
            public void run() {
                SQLiteHelper sqLiteHelper=new SQLiteHelper(context);
                SQLiteDatabase sql=sqLiteHelper.getWritableDatabase();
                ContentValues values=new ContentValues();
                values.put("item",item);
                values.put("count",count);
                values.put("money",money);
                Date date = new Date();
                @SuppressLint("SimpleDateFormat") SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
                values.put("created_at",dateFormat.format(date));
                sql.update("shopinfo",values,"created_at='"+oldDate+"'",null);
                sqLiteHelper.close();
                DialogHelper.waitProgressDialog.dismiss();
                loadShop(context);
            }
        });
    }
    public static void updateCheckShop(Context context,String date,String check_buy){
        DialogHelper.WaitProgressDialog(context,"更新資料","更新資料中，請稍後...");
        ((Activity)context).runOnUiThread(new Runnable() {
            @Override
            public void run() {
                SQLiteHelper sqLiteHelper=new SQLiteHelper(context);
                SQLiteDatabase sql=sqLiteHelper.getWritableDatabase();
                ContentValues values=new ContentValues();
                values.put("check_buy",check_buy);
                sql.update("shopinfo",values,"created_at='"+date+"'",null);
                sqLiteHelper.close();
                DialogHelper.waitProgressDialog.dismiss();
                loadShop(context);
            }
        });
    }
    public static void deleteShop(Context context,String date){
        DialogHelper.WaitProgressDialog(context,"刪除資料","刪除資料中，請稍後...");
        ((Activity)context).runOnUiThread(new Runnable() {
            @Override
            public void run() {
                SQLiteHelper sqLiteHelper=new SQLiteHelper(context);
                SQLiteDatabase sql=sqLiteHelper.getWritableDatabase();
                sql.delete("shopinfo","created_at='"+date+"'",null);
                sqLiteHelper.close();
                DialogHelper.waitProgressDialog.dismiss();
                loadShop(context);
            }
        });
    }
    public static void deleteSelect(Context context){
        DialogHelper.WaitProgressDialog(context,"刪除資料","刪除資料中，請稍後...");
        ((Activity)context).runOnUiThread(new Runnable() {
            @Override
            public void run() {
                SQLiteHelper sqLiteHelper=new SQLiteHelper(context);
                SQLiteDatabase sql=sqLiteHelper.getWritableDatabase();
                sql.delete("shopinfo","check_buy='"+"true"+"'",null);
                sqLiteHelper.close();
                DialogHelper.waitProgressDialog.dismiss();
                loadShop(context);
            }
        });
    }
    public static void deleteAll(Context context){
        DialogHelper.WaitProgressDialog(context,"刪除資料","刪除資料中，請稍後...");
        ((Activity)context).runOnUiThread(new Runnable() {
            @Override
            public void run() {
                SQLiteHelper sqLiteHelper=new SQLiteHelper(context);
                SQLiteDatabase sql=sqLiteHelper.getWritableDatabase();
                sql.execSQL("DELETE FROM "+"shopinfo");
                sqLiteHelper.close();
                sql.close();
                DialogHelper.waitProgressDialog.dismiss();
                loadShop(context);
            }
        });
    }
}
