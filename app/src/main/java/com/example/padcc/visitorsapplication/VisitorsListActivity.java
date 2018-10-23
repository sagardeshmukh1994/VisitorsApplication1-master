package com.example.padcc.visitorsapplication;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class VisitorsListActivity extends AppCompatActivity {
    DatabaseHandler db;
    ListView listView;
    Button btnview;
    TextView fn,ln,ph,em,tech,gen;
    List<Visitor> visitorList = new ArrayList<>();
    ProgressDialog pd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_visitors_list);

        pd = new ProgressDialog(VisitorsListActivity.this);
        pd.setMessage("Please wait");
        pd.setCancelable(true);
        pd.show();

      //  btnview=(Button) findViewById(R.id.button_view);

        listView = (ListView) findViewById(R.id.listview1);
        db=new DatabaseHandler(VisitorsListActivity.this);

        visitorList=db.getAllVisitors();
        CustomAdapter customAdapter=new CustomAdapter();
        listView.setAdapter(customAdapter);
        pd.dismiss();

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int pos, long l) {
                Intent intent=new Intent(VisitorsListActivity.this,DetailsActivity.class);

                intent.putExtra("visitor",visitorList.get(pos));
                startActivity(intent);


            }
        });
    }


    class CustomAdapter extends BaseAdapter {
        Visitor visitor=new Visitor();
        @Override
        public int getCount() {
            return visitorList.size();
        }

        @Override
        public Object getItem(int i) {
            return null;
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @Override
        public View getView(int position, View view, ViewGroup viewGroup) {

            view = getLayoutInflater().inflate(R.layout.visitorslist, null);

             TextView textviewid=(TextView) view.findViewById(R.id.textid);
            final TextView textViewfname1 = (TextView) view.findViewById(R.id.textfname);
            final TextView textViewtechnique1 = (TextView) view.findViewById(R.id.texttechnique);




            textviewid.setText(String.valueOf(visitorList.get(position).getVisitorId()));
            textViewfname1.setText(visitorList.get(position).getVfirstnName());
            textViewtechnique1.setText(visitorList.get(position).getVTechnique());

            visitor.setVisitorId(visitorList.get(position).getVisitorId());
            visitor.setVfirstnName(visitorList.get(position).getVfirstnName());
            visitor.setVLastName(visitorList.get(position).getVLastName());
            visitor.setVPhone(visitorList.get(position).getVPhone());
            visitor.setVEmail(visitorList.get(position).getVEmail());
            visitor.setVTechnique(visitorList.get(position).getVTechnique());
            visitor.setVgender(visitorList.get(position).getVgender());


            return view;
        }
    }
}
