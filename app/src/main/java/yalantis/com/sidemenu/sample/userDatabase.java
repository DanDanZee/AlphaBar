package yalantis.com.sidemenu.sample;


import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase.CursorFactory;


public class userDatabase extends SQLiteOpenHelper {

    userDatabase(Context context, String name, CursorFactory cursorFactory, int version)
    {
        super(context, name, cursorFactory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // TODO 创建数据库后，对数据库的操作
        db.execSQL("CREATE TABLE usertable (_id INTEGER PRIMARY KEY AUTOINCREMENT, username varchar(20), order_num int);");
        db.execSQL("insert into usertable(username,order_num) values('chunwei',5)");
        db.execSQL("insert into usertable(username,order_num) values('zijie',8)");
        db.execSQL("insert into usertable(username,order_num) values('yihan',10)");
        db.execSQL("insert into usertable(username,order_num) values('mengxin',4)");
        db.execSQL("insert into usertable(username,order_num) values('datui',3)");
        db.execSQL("insert into usertable(username,order_num) values('diege',6)");
        db.execSQL("insert into usertable(username,order_num) values('tangtang',10)");
        db.execSQL("insert into usertable(username,order_num) values('xiancong',4)");
        db.execSQL("insert into usertable(username,order_num) values('bangyuan',5)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // TODO 更改数据库版本的操作
    }

    @Override
    public void onOpen(SQLiteDatabase db) {
        super.onOpen(db);
        // TODO 每次成功打开数据库后首先被执行
    }
}
