package camera.socket.app.gxj.com.accessprovider;

import android.app.Application;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.test.ApplicationTestCase;

/**
 * <a href="http://d.android.com/tools/testing/testing_android.html">Testing Fundamentals</a>
 */
public class ApplicationTest extends ApplicationTestCase<Application> {
    public ApplicationTest() {
        super(Application.class);
    }


    public void testInsert(){
        Uri uri = Uri.parse("content://com.example.providers.personprovider/person");
        ContentResolver resolver = getContext().getContentResolver();
        ContentValues values = new ContentValues();
        values.put("name", "阿斯蒂芬");
        values.put("personid", 15);
        resolver.insert(uri, values);
    }

    public void testDelete(){
        Uri uri = Uri.parse("content://com.example.providers.personprovider/person/2");
        ContentResolver resolver = getContext().getContentResolver();
        resolver.delete(uri, null, null);
    }

    public void testUpdate(){
        Uri uri = Uri.parse("content://com.example.providers.personprovider/person/3");
        ContentResolver resolver = getContext().getContentResolver();
        ContentValues values = new ContentValues();
        values.put("name", "nbqq");
        values.put("personid", 99);
        resolver.update(uri, values, null, null);
    }

    public void testQuery(){
        Uri uri = Uri.parse("content://com.example.providers.personprovider/person");
        ContentResolver resolver = getContext().getContentResolver();
        Cursor cursor = resolver.query(uri, new String[]{"name","personid"}, null, null, null);
        while(cursor.moveToNext()){
            String name = cursor.getString(cursor.getColumnIndex("name"));
            String personid = cursor.getString(cursor.getColumnIndex("personid"));
            System.out.println("name="+name+" "+"personid="+personid);
        }
    }
}