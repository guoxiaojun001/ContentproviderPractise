package camera.socket.app.gxj.com.provider.dbhelper;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DbOpenHelper extends SQLiteOpenHelper {

	public static final String ALERT_SQL_CREATE_TABLE =
			"alter table person rename to temp_A";

	private static final String SQL_CREATE_TABLE =
	"create table  if not exists person (personid integer primary key autoincrement,name varchar(20) not null)";

	private String INSERT_DATA = "insert into person select * from temp_A";
	private String DROP_BOOK = "drop table temp_A";

	public DbOpenHelper(Context context) {
		super(context, "demo.db", null, 5);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL(SQL_CREATE_TABLE);
	//"create table if not exists person(personid integer primary key autoincrement,name varchar(20))"
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		//db.execSQL("alter table person add personid varchar(20) null");
		Log.d("Provider3","onUpgrade == "  + newVersion);
//		for (int j = oldVersion; j <= newVersion; j++) {
//			switch (3) {
//				case 3:
					//创建临时表
					db.execSQL(ALERT_SQL_CREATE_TABLE);
					//执行OnCreate方法，这个方法中放的是表的初始化操作工作，比如创建新表之类的
					onCreate(db);

					//将临时表中的数据放入新表
					db.execSQL(INSERT_DATA);

					//将临时表删除掉
					db.execSQL(DROP_BOOK);
//					break;
//
//				default:
//					break;
//			}

//		}
	}
}
