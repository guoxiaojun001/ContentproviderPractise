package camera.socket.app.gxj.com.accessprovider;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.util.Log;
import android.widget.TextView;

import java.util.Random;

/**
 * Created by gxj on 2016/7/19.
 */
public class TestProvider {
    Context context;
    TextView result;

    public TestProvider(Context context,TextView result){
        this.context = context;
        this.result = result;
    }

    public void testInsertRandom(){
        //随机插入
        Uri uri = Uri.parse("content://com.example.providers.personprovider/person");
        ContentResolver resolver = context.getContentResolver();
        ContentValues values = new ContentValues();

        for (int a = 5; a< 10; a++){
            values.put("name", "newqq" + a);
            values.put("personid", a);
            values.put("sex", "f");
            resolver.insert(uri, values);
        }
    }

    public void testInsertSpecify(String name, int id){
        //插入指定数据
        Uri uri = Uri.parse("content://com.example.providers.personprovider/person");
        ContentResolver resolver = context.getContentResolver();
        ContentValues values = new ContentValues();
        values.put("name", name);
        values.put("personid", id);
        resolver.insert(uri, values);
    }

    public void testDeleteById(int id){
        //删除 指定 personid 的数据
        String uri_id = "content://com.example.providers.personprovider/person/" + id;
        Uri uri = Uri.parse(uri_id);
        ContentResolver resolver = context.getContentResolver();
        resolver.delete(uri, null, null);
    }

    public void testDeleteByArea(){
        //删除 表中指定区间的 的数据
        Uri uri = Uri.parse("content://com.example.providers.personprovider/person/");
        ContentResolver resolver = context.getContentResolver();

        //db.delete("person", "personid<?", new String[]{"2"});
        resolver.delete(uri, "personid > ? and personid < ?", new String[]{"3","6"});
    }

    public void testDeleteAll(){
        //删除 person表中所有 的数据
        Uri uri = Uri.parse("content://com.example.providers.personprovider/person/");
        ContentResolver resolver = context.getContentResolver();
        resolver.delete(uri, null, null);
    }

    public void testUpdateById(int id){
        //修改 指定 personid 的数据
        String uri_id = "content://com.example.providers.personprovider/person/" + id;
        Uri uri = Uri.parse(uri_id);
        ContentResolver resolver = context.getContentResolver();
        ContentValues values = new ContentValues();
        values.put("name", "萨达姆");
        resolver.update(uri, values, null, null);
    }

    public void testUpdateByArea(){
        Uri uri = Uri.parse("content://com.example.providers.personprovider/person/");
        ContentResolver resolver = context.getContentResolver();
        //resolver.update(uri, null, "where personid > ? and personid < ?", null);

        ContentValues values = new ContentValues();
        values.put("name","aaqq");
        //这样会把  4 5 6条数据 更新成一条
        resolver.update(uri, values, " personid > ? and personid < ?", new String[]{"3","7"});
    }

    public void testQueryAll(){
        StringBuilder stringBuilder = new StringBuilder();
        Uri uri = Uri.parse("content://com.example.providers.personprovider/person");
        ContentResolver resolver = context.getContentResolver();
        Cursor cursor = resolver.query(uri, new String[]{"name","personid"}, null, null, null);
        while(cursor.moveToNext()){
            String name = cursor.getString(cursor.getColumnIndex("name"));
            String personid = cursor.getString(cursor.getColumnIndex("personid"));

            stringBuilder = stringBuilder.append("name="+name+" "+"personid="+personid).append("\n");
        }
        result.setText(stringBuilder.toString());
    }

    public void testQueryById(int id){
        String uri_id = "content://com.example.providers.personprovider/person/" + id;
        Uri uri = Uri.parse(uri_id);
        ContentResolver resolver = context.getContentResolver();
        Cursor cursor = resolver.query(uri, new String[]{"name","personid"}, null, null, null);

        StringBuilder stringBuilder = new StringBuilder();
        while(cursor.moveToNext()){
            String name = cursor.getString(cursor.getColumnIndex("name"));
            String personid = cursor.getString(cursor.getColumnIndex("personid"));

            stringBuilder = stringBuilder.append("name="+name+" "+"personid="+personid).append("\n");
        }
        result.setText(stringBuilder.toString());
    }


    public void testQueryByName(String  iname){
        Uri uri = Uri.parse("content://com.example.providers.personprovider/person/");
        ContentResolver resolver = context.getContentResolver();
        Cursor cursor = resolver.query(uri, new String[]{"name","personid"}, "name = ?", new String[]{iname}, null);

        StringBuilder stringBuilder = new StringBuilder();
        while(cursor.moveToNext()){
            String name = cursor.getString(cursor.getColumnIndex("name"));
            String personid = cursor.getString(cursor.getColumnIndex("personid"));

            stringBuilder = stringBuilder.append("name="+name+" "+"personid="+personid).append("\n");
        }
        result.setText(stringBuilder.toString());
    }


    public void testQueryArea(){
        Uri uri = Uri.parse("content://com.example.providers.personprovider/person/");
        ContentResolver resolver = context.getContentResolver();
        Cursor cursor = resolver.query(uri, new String[]{"name","personid"}, "personid > ? and personid < ?", new String[]{"5","8"}, null);

        StringBuilder stringBuilder = new StringBuilder();
        while(cursor.moveToNext()){
            String name = cursor.getString(cursor.getColumnIndex("name"));
            String personid = cursor.getString(cursor.getColumnIndex("personid"));

            stringBuilder = stringBuilder.append("name="+name+" "+"personid="+personid).append("\n");
        }
        result.setText(stringBuilder.toString());
    }
}
