package com.palle.annu.b33_listviewslow;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    EditText etActorName, etMovieName;
    Button bInsert;
    ListView list;
    ArrayList<Moview> al;
    MyAdapter aa;
    int count = 0; //will keep count of serial number

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        etActorName = (EditText) findViewById(R.id.etName);
        etMovieName = (EditText) findViewById(R.id.et_moviename);
        bInsert = (Button) findViewById(R.id.b_click);
        list = (ListView) findViewById(R.id.list1);

        al = new ArrayList<Moview>();
        aa = new MyAdapter();
        list.setAdapter(aa);

        bInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                count++;
                String actor = etActorName.getText().toString();
                String movie = etMovieName.getText().toString();
                //create an empty movie object
                Moview moview = new Moview();
                //Insert data into moview object using setter

                moview.setSno(""+count);
                moview.setActorName(actor);
                moview.setMoviewName(movie);
                //Add this moview object to arraylist
                al.add(moview);
                //tell to adapter
                aa.notifyDataSetChanged();
                //clean edittext fields and put cursor on first edit text
                etActorName.setText("");
                etMovieName.setText("");

                etActorName.requestFocus();

            }
        });

    }

    public class MyAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return al.size();
        }

        @Override
        public Object getItem(int position) {
            return al.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            Log.d("ADAPTER","POSITION-" +position);

            //load row.xml and all views
            View v = getLayoutInflater().inflate(R.layout.row, null);
            TextView tvSNo = (TextView) v.findViewById(R.id.tv_sno);
            TextView tvActorName = (TextView) v.findViewById(R.id.tv_actor_name);
            TextView tvMovieName = (TextView) v.findViewById(R.id.tv_moview_name);

            Moview moview = al.get(position); //reading moview objetc
            //apply data -using getters
            tvSNo.setText(moview.getSno());
            tvActorName.setText(moview.getActorName());
            tvMovieName.setText(moview.getMoviewName());




           /* //Add this moview object to arraylist
            al.add(moview);
            //Tell to Adapter
            myAdapter.notifyDataSetChangerd();
            //clean edittext field and put cursor on first edit text
            etActorName.setText("");
            etMovieName.setText("");
            etActorName.requestFocus();*/

            return v;
        }
    }
}
