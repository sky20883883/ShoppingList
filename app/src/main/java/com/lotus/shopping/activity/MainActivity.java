package com.lotus.shopping.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.lotus.shopping.R;
import com.lotus.shopping.module.Config;
import com.lotus.shopping.module.DialogHelper;
import com.lotus.shopping.module.SQLiteControler;

public class MainActivity extends AppCompatActivity {
    public static MainActivity mainActivityControl;
    public static TextView estimatedTextView;
    TextView spreadTextView;
    EditText budgetEditText;
    Button addBtn,deleteChooseBtn,deleteAllBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mainActivityControl=MainActivity.this;
        setView();
        loadConfig();
        countSpread();
    }


    private void setView() {
        estimatedTextView=findViewById(R.id.main_estimated_textview);
        spreadTextView=findViewById(R.id.main_spread_textview);
        budgetEditText=findViewById(R.id.main_budget_edittext);
        addBtn=findViewById(R.id.main_add_btn);
        deleteChooseBtn=findViewById(R.id.main_delete_choose_btn);
        deleteAllBtn=findViewById(R.id.main_delete_all_btn);

        budgetEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int start, int before, int count) {

            }
            @Override
            public void onTextChanged(CharSequence charSequence, int start, int before, int count) {

            }
            @Override
            public void afterTextChanged(Editable editable) {
                countSpread();
                saveConfig();
            }
        });
        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogHelper.AddDialog(MainActivity.this);
            }
        });
        deleteAllBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogHelper.QuestionDeletAllDialog(MainActivity.this);
            }
        });
        deleteChooseBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogHelper.QuestionDeletSelectDialog(MainActivity.this);
            }
        });
        SQLiteControler.loadShop(MainActivity.this);
    }

    public void saveConfig(){
        SharedPreferences mSharedPreferences = getSharedPreferences("shop", Activity.MODE_PRIVATE);
        SharedPreferences.Editor edit = mSharedPreferences.edit();
        edit.putString("budget", budgetEditText.getText().toString());
        edit.commit();
    }
    public void loadConfig(){
        SharedPreferences SharedPref = getSharedPreferences("shop", Activity.MODE_PRIVATE);
        String budget = SharedPref.getString("budget", "");
        budgetEditText.setText(budget);
    }
    public void countSpread(){
        runOnUiThread(() -> {
            int Estimated=Integer.parseInt(estimatedTextView.getText().toString());
            int Budget=0;
            try {
                Budget=Integer.parseInt(budgetEditText.getText().toString());
            }catch (Exception e){
                Budget=0;
            }
            int plus=Budget-Estimated;
            spreadTextView.setText(String.valueOf(plus));
        });
    }
}