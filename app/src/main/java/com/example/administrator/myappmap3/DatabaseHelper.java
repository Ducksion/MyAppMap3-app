package com.example.administrator.myappmap3;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Administrator on 2017/08/23.
 */

public class DatabaseHelper extends SQLiteOpenHelper
{
    /**
     * @param context  上下文环境（例如，一个 Activity）
     * @param name   数据库名字
     * @param factory  一个可选的游标工厂（通常是 Null）
     * @param version  数据库模型版本的整数
     *
     * 会调用父类 SQLiteOpenHelper的构造函数
     */
    public DatabaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version)
    {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase)
    {
        //"/data/data/com.example.administrator.myappmap3/databases/" 中间的必须是程序的包名【com.example.administrator.myappmap3】
        sqLiteDatabase.openOrCreateDatabase("/data/data/com.example.administrator.myappmap3/databases/MapData.db",null);

        String sql = "create table PointHistory(id int,createdate date,latitude double,longitude double,comment varchar(40))";
        sqLiteDatabase.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1)
    {

    }

    @Override
    public void onOpen(SQLiteDatabase db)
    {
        super.onOpen(db);
    }
}
