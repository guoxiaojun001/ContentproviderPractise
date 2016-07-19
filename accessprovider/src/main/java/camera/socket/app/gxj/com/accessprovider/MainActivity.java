package camera.socket.app.gxj.com.accessprovider;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    TestProvider testProvider;

    Button insert;
    Button updata;
    Button delete;
    Button query;
    TextView result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
        testProvider = new TestProvider(MainActivity.this, result);
    }

    private void initView() {
        insert = (Button) findViewById(R.id.insert);
        updata = (Button) findViewById(R.id.updata);
        delete = (Button) findViewById(R.id.delete);
        query = (Button) findViewById(R.id.query);
        result = (TextView) findViewById(R.id.result);

        setListener();
    }

    private void setListener() {
        insert.setOnClickListener(this);
        updata.setOnClickListener(this);
        delete.setOnClickListener(this);
        query.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.insert:
                testProvider.testInsertRandom();
                //testProvider.testInsertSpecify("乔丹",23);
                break;
            case R.id.updata:
                //testProvider.testUpdate();
                //testProvider.testUpdateById(5);
                testProvider.testUpdateByArea();
                break;
            case R.id.delete:
                //testProvider.testDeleteById(6);
                //testProvider.testDeleteAll();
                testProvider.testDeleteByArea();
                break;
            case R.id.query:
                //testProvider.testQueryAll();
                //testProvider.testQueryById(8);
                //testProvider.testQueryByName("WWQQ4");
                testProvider.testQueryArea();
                break;

            default:
                break;

        }

    }
}
