package camera.socket.app.gxj.com.contentproviderpractise;

import android.app.Application;
import android.test.ApplicationTestCase;

import java.util.List;

import camera.socket.app.gxj.com.provider.Person;
import camera.socket.app.gxj.com.provider.dbhelper.DbOpenHelper;
import camera.socket.app.gxj.com.provider.dbhelper.SQLiteHelper;

/**
 * <a href="http://d.android.com/tools/testing/testing_android.html">Testing Fundamentals</a>
 */
public class ApplicationTest extends ApplicationTestCase<Application> {
    public ApplicationTest() {
        super(Application.class);
    }




    public void create(){
        DbOpenHelper helper = new DbOpenHelper(getContext());
        helper.getReadableDatabase();
    }

    public void testSave(){
        SQLiteHelper sqLiteHelper = new SQLiteHelper(this.getContext());
        Person person = new Person("hanfei",13);
        sqLiteHelper.save(person);
    }

    public void testDelete(){
        SQLiteHelper sqLiteHelper = new SQLiteHelper(this.getContext());
        sqLiteHelper.delete(1);
    }

    public void testUpdate(){
        SQLiteHelper sqLiteHelper = new SQLiteHelper(this.getContext());
        Person person = new Person("xiaoliu",13);
        sqLiteHelper.update(person);
    }

    public void testFind(){
        SQLiteHelper sqLiteHelper = new SQLiteHelper(this.getContext());
        Person person = sqLiteHelper.find(1);
    }

    public void testScrollData(){
        SQLiteHelper sqLiteHelper = new SQLiteHelper(this.getContext());
        List<Person> persons = sqLiteHelper.getScrollData(0, 1);
        System.out.println(persons.size());
    }

    public void testGetcount(){
        SQLiteHelper sqLiteHelper = new SQLiteHelper(this.getContext());
        int count = sqLiteHelper.getCount();
        System.out.println("======"+count);
    }

}