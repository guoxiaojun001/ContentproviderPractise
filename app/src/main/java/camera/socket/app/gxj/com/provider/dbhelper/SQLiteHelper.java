package camera.socket.app.gxj.com.provider.dbhelper;

import java.util.ArrayList;
import java.util.List;


import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import camera.socket.app.gxj.com.provider.Person;

public class SQLiteHelper {

	private Context context;
	private DbOpenHelper  helper = null;
	public SQLiteHelper(Context context){
		helper = new DbOpenHelper(context);
	}
	
	public void save(Person person){//增
		SQLiteDatabase db = helper.getWritableDatabase();
		db.execSQL("insert into person(name,personid) values(?,?)",new Object[]{person.getName(),person.getPersonid()});
		db.close();
	}
	
	public void delete(int personid){//删
		SQLiteDatabase db = helper.getWritableDatabase();
		db.execSQL("delete from person where personid=?", new Integer[]{personid});
		db.close();
	}
	
	public void update(Person person){//改
		SQLiteDatabase db = helper.getWritableDatabase();
		db.execSQL("update person set name=?where personid=?", new Object[]{person.getName(),person.getPersonid()});
		db.close();
	}
	
	public Person find(int personid){//查
		SQLiteDatabase db = helper.getReadableDatabase();
		Cursor cursor = db.rawQuery("select * from person where personid=?", new String[]{personid+""});
		if(cursor.moveToFirst()){
			int id = cursor.getInt(cursor.getColumnIndex("personid"));
			String name = cursor.getString(cursor.getColumnIndex("name"));
			return new Person(name,id);
		}
		cursor.close();
		return null;
	}
	
	public List<Person> getScrollData(int offset,int result){//分页获取
		SQLiteDatabase db = helper.getReadableDatabase();
		List<Person> persons = new ArrayList<Person>();
		Cursor cursor = db.rawQuery("select * from person limit ?,?", new String[]{offset+"",result+""});
		if(cursor.moveToNext()){
			int personid = cursor.getInt(cursor.getColumnIndex("personid"));
			String name = cursor.getString(cursor.getColumnIndex("name"));
			persons.add(new Person(name,personid));
		}
		cursor.close();
		return persons;
	}
	
	public int getCount(){
		SQLiteDatabase db = helper.getReadableDatabase();
		Cursor cursor = db.rawQuery("select count(*) from person", null);
		cursor.moveToFirst();
		
		int dex = cursor.getInt(0);
		cursor.close();
		return dex;
	}
}
