package com.example.padcc.visitorsapplication;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

public class DetailsActivity extends AppCompatActivity {
TextView id2,fname2,lname2,phone2,email2,tech2,gender2;
    DatabaseHandler db;
//Button submit;
Visitor visitor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

//        submit=(Button)findViewById(R.id.submit1);

       id2=(TextView)findViewById(R.id.Did);
        fname2=(TextView)findViewById(R.id.Dtextfname);
        lname2=(TextView)findViewById(R.id.Dtextlname);
        phone2=(TextView)findViewById(R.id.Dtextphone);
        email2=(TextView)findViewById(R.id.Dtextemail);
        tech2=(TextView)findViewById(R.id.Dtexttechnique);
        gender2=(TextView)findViewById(R.id.Dtextgender);

        db = new DatabaseHandler(DetailsActivity.this);

        Intent intent=getIntent();
         visitor=(Visitor)intent.getSerializableExtra("visitor");


//        String FName=intent.getStringExtra("firstname");
//        String LName=intent.getStringExtra("lastname");
//        String PHONE=intent.getStringExtra("phonenumber");
//        String EMAIL=intent.getStringExtra("emailaddress");
//        String TECH=intent.getStringExtra("techni");
//        String GENDER=intent.getStringExtra("gend");

        id2.setText(String.valueOf(visitor.getVisitorId()));
        fname2.setText(visitor.getVfirstnName());
        lname2.setText(visitor.getVLastName());
        phone2.setText(visitor.getVPhone());
        email2.setText(visitor.getVEmail());
        tech2.setText(visitor.getVTechnique());
        gender2.setText(visitor.getVgender());

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.details_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {

            case R.id.itm_update:

                String val = "intent";
                Intent intent=new Intent(DetailsActivity.this,RegistrationActivity.class);
                intent.putExtra("visitor",visitor);
                startActivity(intent);
                break;
            case R.id.item_detete:
                alert();




        }
        return super.onOptionsItemSelected(item);
    }

    public void alert(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setMessage("Do you want to delete the record")
                .setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {

                       db.deleteVisitors(visitor.getVisitorId());
                        Toast.makeText(getApplicationContext(),"Deleted",Toast.LENGTH_SHORT).show();

                        id2.setText("");
                        fname2.setText("");
                        lname2.setText("");
                        phone2.setText("");
                        email2.setText("");
                        tech2.setText("");
                        gender2.setText("");
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        //  Action for 'NO' Button
                        dialog.cancel();
                    }
                });
        //Creating dialog box
        AlertDialog alert = builder.create();
        //Setting the title manually
        alert.setTitle("AlertDialogExample");
        alert.show();
    }
}
