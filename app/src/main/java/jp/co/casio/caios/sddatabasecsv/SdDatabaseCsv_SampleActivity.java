package jp.co.casio.caios.sddatabasecsv;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;


/**
 * SD card database (SUMMARY.DB) convert to csv file specified table name and condition.
 */
public class SdDatabaseCsv_SampleActivity extends Activity implements OnClickListener {

    //Explicit
    private EditText mEditText1;    // รับค่า Table
    private EditText mEditText2;    // รับค่า BIZDATE
    //private TextView mTextView;
    private ListView myListView;

    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        //Bind Widget
        myListView = (ListView) findViewById(R.id.listView);

        // การทำ onClickListener
        Button button;
        button = (Button) findViewById(R.id.button1);
        button.setOnClickListener(this);
        button = (Button) findViewById(R.id.button2);
        button.setOnClickListener(this);

        mEditText1 = (EditText) findViewById(R.id.editText1);
        mEditText2 = (EditText) findViewById(R.id.editText2);
        //mTextView = (TextView) findViewById(R.id.textView1);

    }

    @Override
    public void onClick(View arg0) {

        switch (arg0.getId()) {

            case R.id.button1:    // SEARCH

                //Get Value From Edittext
                String dbName = "SUMMARY.DB";        // DB file name
                String table = mEditText1.getText().toString(); // Table name (CSS012)
                String condition = mEditText2.getText().toString(); // Condition (BIZDATE='20130701')
                String csvName = table + ".CSV";        // CSV file name


                ConvertDatabaseToCsv convert = new ConvertDatabaseToCsv();
                String data = convert.convertDatabaseToCsv(dbName, table, condition, csvName);

                //Create myListView
                createMyListView(dbName, table, condition);

                //mTextView.setText(table+":"+condition+"\n"+data);
                break;

            case R.id.button2:    // Close
                finish();
                break;
        }
    }    // onClick

    private void createMyListView(String strDatabaseName, String strTableName, String strWhereName) {

        //Get Data from SQLite
        String[] itemNameStrings, qtyStrings, amtStrings;


        //Tester
        itemNameStrings = new String[4];
        itemNameStrings[0] = "Name1";
        itemNameStrings[1] = "Name2";
        itemNameStrings[2] = "Name3";
        itemNameStrings[3] = "Name4";

        qtyStrings = new String[4];
        qtyStrings[0] = "1";
        qtyStrings[1] = "2";
        qtyStrings[2] = "3";
        qtyStrings[3] = "4";

        amtStrings = new String[4];
        amtStrings[0] = "a1";
        amtStrings[1] = "a2";
        amtStrings[2] = "a3";
        amtStrings[3] = "a4";

        MyAdapter objMyAdapter = new MyAdapter(SdDatabaseCsv_SampleActivity.this, itemNameStrings, qtyStrings, amtStrings);
        myListView.setAdapter(objMyAdapter);

    }    // createMyListView


}
