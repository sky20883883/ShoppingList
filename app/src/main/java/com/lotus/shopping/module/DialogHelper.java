package com.lotus.shopping.module;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.util.DisplayMetrics;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.lotus.shopping.R;
import com.lotus.shopping.activity.MainActivity;


public class DialogHelper {
    public static ProgressDialog waitProgressDialog;
    public static void AddDialog(Context context){
        AlertDialog.Builder builder=new AlertDialog.Builder(context);
        LayoutInflater inflater=(LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View dialog=inflater.inflate(R.layout.dialog_add,null);
        Button okBtn,notBtn,piusBtn,reduceBtn;
        EditText itemEdittext,countEdittext,moneyEdittext;
        okBtn=dialog.findViewById(R.id.dialog_add_ok_btn);
        notBtn=dialog.findViewById(R.id.dialog_add_back_btn);
        reduceBtn=dialog.findViewById(R.id.dialog_add_count_reduce_bnt);
        piusBtn=dialog.findViewById(R.id.dialog_add_count_plus_bnt);
        itemEdittext=dialog.findViewById(R.id.dialog_add_item_name_edittext);
        countEdittext=dialog.findViewById(R.id.dialog_add_count_edittext);
        moneyEdittext=dialog.findViewById(R.id.dialog_add_money_edittext);


        builder.setView(dialog).setCancelable(false);
        AlertDialog showDialog=builder.create();
        showDialog.setOnKeyListener((dialog1, keyCode, event) -> {
            return keyCode == KeyEvent.KEYCODE_SEARCH;
        });
        showDialog.show();
        okBtn.setOnClickListener(view -> {
            if (!itemEdittext.getText().toString().trim().equals("")&&
                    !countEdittext.getText().toString().trim().equals("")&&
                    !moneyEdittext.getText().toString().trim().equals("")) {
                SQLiteControler.saveShop(context,itemEdittext.getText().toString(),countEdittext.getText().toString(),moneyEdittext.getText().toString());
                showDialog.dismiss();
            }else {
                Toast.makeText(context,"欄位不可為空",Toast.LENGTH_LONG).show();}
        });
        notBtn.setOnClickListener(v -> {
            showDialog.dismiss();
        });
        piusBtn.setOnClickListener(view -> {
            if (!countEdittext.getText().toString().equals("")){
                int count1 =Integer.parseInt(countEdittext.getText().toString())+1;
                countEdittext.setText(String.valueOf(count1));
            }else {
                countEdittext.setText("1");
            }
        });
        reduceBtn.setOnClickListener(view -> {
            if (!countEdittext.getText().toString().equals("") && Integer.parseInt(countEdittext.getText().toString())>0){
                int count12 =Integer.parseInt(countEdittext.getText().toString())-1;
                countEdittext.setText(String.valueOf(count12));
            }else {
                countEdittext.setText("0");
            }
        });
        DisplayMetrics dm = new DisplayMetrics();//取得螢幕解析度
        ((Activity)context).getWindowManager().getDefaultDisplay().getMetrics(dm);//取得螢幕寬度值
        showDialog.getWindow().setLayout(dm.widthPixels-230, ViewGroup.LayoutParams.WRAP_CONTENT);//設置螢幕寬度值
        showDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));//將原生AlertDialog的背景設為透明
    }
    public static void ChangeDialog(Context context,String item,String count,String money,String date){
        AlertDialog.Builder builder=new AlertDialog.Builder(context);
        LayoutInflater inflater=(LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View dialog=inflater.inflate(R.layout.dialog_choose,null);
        Button okBtn,notBtn,piusBtn,reduceBtn;
        EditText itemEdittext,countEdittext,moneyEdittext;
        okBtn=dialog.findViewById(R.id.dialog_change_ok_btn);
        notBtn=dialog.findViewById(R.id.dialog_change_back_btn);
        reduceBtn=dialog.findViewById(R.id.dialog_change_count_reduce_bnt);
        piusBtn=dialog.findViewById(R.id.dialog_change_count_plus_bnt);
        itemEdittext=dialog.findViewById(R.id.dialog_change_item_name_edittext);
        countEdittext=dialog.findViewById(R.id.dialog_change_count_edittext);
        moneyEdittext=dialog.findViewById(R.id.dialog_change_money_edittext);
        itemEdittext.setText(item);
        countEdittext.setText(count);
        moneyEdittext.setText(money);

        builder.setView(dialog).setCancelable(false);
        AlertDialog showDialog=builder.create();
        showDialog.setOnKeyListener((dialog1, keyCode, event) -> {
            return keyCode == KeyEvent.KEYCODE_SEARCH;
        });
        showDialog.show();
        okBtn.setOnClickListener(view -> {
            if (!itemEdittext.getText().toString().trim().equals("")&&
                    !countEdittext.getText().toString().trim().equals("")&&
                    !moneyEdittext.getText().toString().trim().equals("")) {
                SQLiteControler.updateShop(context,date,itemEdittext.getText().toString(),countEdittext.getText().toString(),moneyEdittext.getText().toString());
                showDialog.dismiss();
            }else {
                Toast.makeText(context,"欄位不可為空",Toast.LENGTH_LONG).show();}
        });
        notBtn.setOnClickListener(v -> {
            showDialog.dismiss();
        });
        piusBtn.setOnClickListener(view -> {
            if (!countEdittext.getText().toString().equals("")){
                int count1 =Integer.parseInt(countEdittext.getText().toString())+1;
                countEdittext.setText(String.valueOf(count1));
            }else {
                countEdittext.setText("1");
            }
        });
        reduceBtn.setOnClickListener(view -> {
            if (!countEdittext.getText().toString().equals("") && Integer.parseInt(countEdittext.getText().toString())>0){
                int count12 =Integer.parseInt(countEdittext.getText().toString())-1;
                countEdittext.setText(String.valueOf(count12));
            }else {
                countEdittext.setText("0");
            }
        });
        DisplayMetrics dm = new DisplayMetrics();//取得螢幕解析度
        ((Activity)context).getWindowManager().getDefaultDisplay().getMetrics(dm);//取得螢幕寬度值
        showDialog.getWindow().setLayout(dm.widthPixels-230, ViewGroup.LayoutParams.WRAP_CONTENT);//設置螢幕寬度值
        showDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));//將原生AlertDialog的背景設為透明
    }
    public static void DeleteDialog(Context context,String item,String count,String money,String date){
        AlertDialog.Builder builder=new AlertDialog.Builder(context);
        LayoutInflater inflater=(LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View dialog=inflater.inflate(R.layout.dialog_dlete,null);
        Button okBtn=dialog.findViewById(R.id.dialog_delete_ok_btn);
        Button notBtn=dialog.findViewById(R.id.dialog_delete_back_btn);
        TextView itemTextview,countTextview,moneyTextview;
        itemTextview=dialog.findViewById(R.id.dialog_delete_item_name_textview);
        countTextview=dialog.findViewById(R.id.dialog_delete_count_textview);
        moneyTextview=dialog.findViewById(R.id.dialog_delete_money_textview);
        itemTextview.setText(item);
        countTextview.setText(count);
        moneyTextview.setText(money);
        builder.setView(dialog).setCancelable(false);
        AlertDialog showDialog=builder.create();
        showDialog.setOnKeyListener((dialog1, keyCode, event) -> {
            return keyCode == KeyEvent.KEYCODE_SEARCH;
        });
        showDialog.show();
        okBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SQLiteControler.deleteShop(context,date);
                showDialog.dismiss();
            }
        });
        notBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialog.dismiss();
            }
        });
        DisplayMetrics dm = new DisplayMetrics();//取得螢幕解析度
        ((Activity)context).getWindowManager().getDefaultDisplay().getMetrics(dm);//取得螢幕寬度值
        showDialog.getWindow().setLayout(dm.widthPixels-230, ViewGroup.LayoutParams.WRAP_CONTENT);//設置螢幕寬度值
        showDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));//將原生AlertDialog的背景設為透明
    }
    public static void QuestionDeletAllDialog(Context context){
        AlertDialog.Builder builder=new AlertDialog.Builder(context);
        LayoutInflater inflater=(LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View dialog=inflater.inflate(R.layout.dialog_question,null);
        Button okBtn=dialog.findViewById(R.id.dialog_question_ok_btn);
        Button notBtn=dialog.findViewById(R.id.dialog_question_back_btn);
        builder.setView(dialog).setCancelable(false);
        AlertDialog showDialog=builder.create();
        showDialog.setOnKeyListener((dialog1, keyCode, event) -> {
            return keyCode == KeyEvent.KEYCODE_SEARCH;
        });
        showDialog.show();
        okBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SQLiteControler.deleteAll(context);
                MainActivity.estimatedTextView.setText("0");
                Config.BuyMoney=0;
                MainActivity.mainActivityControl.countSpread();
                showDialog.dismiss();
            }
        });
        notBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialog.dismiss();
            }
        });
        DisplayMetrics dm = new DisplayMetrics();//取得螢幕解析度
        ((Activity)context).getWindowManager().getDefaultDisplay().getMetrics(dm);//取得螢幕寬度值
        showDialog.getWindow().setLayout(dm.widthPixels-230, ViewGroup.LayoutParams.WRAP_CONTENT);//設置螢幕寬度值
        showDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));//將原生AlertDialog的背景設為透明
    }
    public static void QuestionDeletSelectDialog(Context context){
        AlertDialog.Builder builder=new AlertDialog.Builder(context);
        LayoutInflater inflater=(LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View dialog=inflater.inflate(R.layout.dialog_question_dleteselect,null);
        Button okBtn=dialog.findViewById(R.id.dialog_question_ok_btn);
        Button notBtn=dialog.findViewById(R.id.dialog_question_back_btn);
        builder.setView(dialog).setCancelable(false);
        AlertDialog showDialog=builder.create();
        showDialog.setOnKeyListener((dialog1, keyCode, event) -> {
            return keyCode == KeyEvent.KEYCODE_SEARCH;
        });
        showDialog.show();
        okBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SQLiteControler.deleteSelect(context);
                showDialog.dismiss();
            }
        });
        notBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialog.dismiss();
            }
        });
        DisplayMetrics dm = new DisplayMetrics();//取得螢幕解析度
        ((Activity)context).getWindowManager().getDefaultDisplay().getMetrics(dm);//取得螢幕寬度值
        showDialog.getWindow().setLayout(dm.widthPixels-230, ViewGroup.LayoutParams.WRAP_CONTENT);//設置螢幕寬度值
        showDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));//將原生AlertDialog的背景設為透明
    }

    public static void WaitProgressDialog(Context Context, String Title, String Msg) {
        int timer = Config.dialogTime;

        waitProgressDialog = ProgressDialog.show(Context, Title, Msg, true);
        waitProgressDialog.setCancelable(false);
//		waitProgressDialog.setOnKeyListener(onKeyListener);
        waitProgressDialog.setOnKeyListener(new DialogInterface.OnKeyListener() {
            @Override
            public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_SEARCH) {
                    return true;
                } else {
                    return false;
                }
            }
        });

        Config.mRunnable = new Runnable() {
            public void run() {
                Config.isDamand = false;
                waitProgressDialog.dismiss();
            }
        };

        Config.mHandler.postDelayed(Config.mRunnable, timer);
    }
}

