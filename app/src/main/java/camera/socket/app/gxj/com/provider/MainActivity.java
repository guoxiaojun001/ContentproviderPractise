package camera.socket.app.gxj.com.provider;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import camera.socket.app.gxj.com.provider.dbhelper.DbOpenHelper;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        DbOpenHelper helper = new DbOpenHelper(this);
    }
}
