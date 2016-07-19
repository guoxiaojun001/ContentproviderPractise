package camera.socket.app.gxj.com.provider;


import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.util.Log;

import camera.socket.app.gxj.com.provider.dbhelper.DbOpenHelper;

public class PersonProvider extends ContentProvider {

	private DbOpenHelper openHelper;
	private static final UriMatcher MATCHER = new UriMatcher(UriMatcher.NO_MATCH);
	private static final int PERSONS = 1;
	private static final int PERSON = 2;
	static{
		MATCHER.addURI("com.example.providers.personprovider", "person", PERSONS);
		
		//* 根据pesonid来删除记录
		MATCHER.addURI("com.example.providers.personprovider", "person/#", PERSON);
	}
	@Override
	public boolean onCreate() {
		openHelper = new DbOpenHelper(this.getContext());
		return false;
	}

	@Override
	public Cursor query(Uri uri, String[] projection, String selection,
			String[] selectionArgs, String sortOrder) {
		Log.d("Provider","query == ");
		SQLiteDatabase sqLiteDatabase = openHelper.getReadableDatabase();
		switch (MATCHER.match(uri)) {
		case 1:
			return sqLiteDatabase.query("person", projection, selection,
					selectionArgs, null, null, sortOrder);
		case 2:
			long rowid = ContentUris.parseId(uri);
			String where = "personid="+rowid;
			if(selection != null && "".equals(selection.trim())){
				where = selection+"and"+where;
			}
			return sqLiteDatabase.query("person", projection, where,
					selectionArgs, null, null, sortOrder);
		}
		return null;
	}

	@Override
	public String getType(Uri uri) {
		Log.d("Provider","getType == ");
		switch (MATCHER.match(uri)) {
		case 1:
			return "vnd.android.cursor.dir/person";
		case 2:
			return "vnd.android.cursor.item/person";
		}
		return null;
	}

	@Override
	public Uri insert(Uri uri, ContentValues values) {
		Log.d("Provider","insert == ");
		SQLiteDatabase sqLiteDatabase = openHelper.getWritableDatabase();
		switch (MATCHER.match(uri)) {
		case 1:
			long rowid = sqLiteDatabase.insert("person", "name", values);
			return ContentUris.withAppendedId(uri, rowid);

		default:
			break;
		}
		return null;
	}

	//* 删除特定personid行的记录
	@Override
	public int delete(Uri uri, String selection, String[] selectionArgs) {
		Log.d("Provider","delete == " + uri);
		SQLiteDatabase sqLiteDatabase = openHelper.getWritableDatabase();
		switch (MATCHER.match(uri)) {
		case 1:
			Log.d("Provider"," selection == "  + selection);
			Log.d("Provider"," selectionArgs == "  + selectionArgs.toString());
			return sqLiteDatabase.delete("person", selection, selectionArgs);
		case 2:
			long rowid = ContentUris.parseId(uri);
			String where = "personid="+rowid;
			if(selection != null && "".equals(selection.trim())){
				where = selection+"and"+where;

			}
			return sqLiteDatabase.delete("person", where, selectionArgs);
		}
		return 0;
	}

	@Override
	public int update(Uri uri, ContentValues values, String selection,
			String[] selectionArgs) {
		Log.d("Provider","update == ");
		SQLiteDatabase sqLiteDatabase = openHelper.getWritableDatabase();
		switch (MATCHER.match(uri)) {
		case 1:
			return sqLiteDatabase.update("person", values, selection, selectionArgs);
		case 2:
			long rowid = ContentUris.parseId(uri);
			String where = "personid="+rowid;
			if(selection != null && "".equals(selection.trim())){
				where = selection+"and"+where;
			}
			return sqLiteDatabase.update("person", values, where, selectionArgs);
		}
		return 0;
	}
}
