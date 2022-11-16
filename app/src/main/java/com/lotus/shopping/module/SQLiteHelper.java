package com.lotus.shopping.module;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class SQLiteHelper extends SQLiteOpenHelper {
    private static final String DataBaseName = "shop";
    private static final int DataBaseVersion = 1;

    public SQLiteHelper(@Nullable Context context) {
        super(context, DataBaseName, null, DataBaseVersion);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        reset_table(sqLiteDatabase);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        if (oldVersion != newVersion) {
            reset_table(sqLiteDatabase);
        }
    }

    public static void reset_table(SQLiteDatabase sql) {
        String cmd = "DROP TABLE IF EXISTS shopinfo";
        sql.execSQL(cmd);
        String create_markinfo = "CREATE TABLE IF NOT EXISTS shopinfo " +
                "(" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "item VARCHAR(50) not null default '' , " + // 商品
                "count VARCHAR(50) not null default '' , " + // 價格
                "money VARCHAR(50) not null default '' , " + // 數量
                "check_buy VARCHAR(50) not null default '' , " + //確認點選
                "created_at VARCHAR(50) not null default ''" +//創建時間
                ")";
        sql.execSQL(create_markinfo);
    }

}
