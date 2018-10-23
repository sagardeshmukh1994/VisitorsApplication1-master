package com.example.padcc.visitorsapplication;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class RegistrationActivity extends AppCompatActivity implements View.OnClickListener {
    EditText Firstname, Lastname, Phone, Email, Technique;
    RadioButton male, female;
    RadioGroup rg;
    Button submit, cancle;
    Spinner spinner;
    DatabaseHandler db;
  //  ListView listView;

    boolean intentFlag = false;
    Visitor intenVvisitor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration2);

        final TextView textViewResult = (TextView) findViewById(R.id.text2);
        Firstname = (EditText) findViewById(R.id.firsname);
        Lastname = (EditText) findViewById(R.id.lastname);
        Phone = (EditText) findViewById(R.id.phone);
        Email = (EditText) findViewById(R.id.email);
        male = (RadioButton) findViewById(R.id.Male);
        female = (RadioButton) findViewById(R.id.Female);
        spinner = (Spinner) findViewById(R.id.spinner1);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.languages, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        submit = (Button) findViewById(R.id.submit1);
        cancle = (Button) findViewById(R.id.cancle1);

        db = new DatabaseHandler(RegistrationActivity.this);
        Intent intent = getIntent();
         intenVvisitor = (Visitor)intent.getSerializableExtra("visitor");

        if (intent.hasExtra("visitor")) {
            intentFlag = true;
            Firstname.setText(intenVvisitor.VfirstnName);
            Lastname.setText(intenVvisitor.VLastName);
            Phone.setText(intenVvisitor.VPhone);
            Email.setText(intenVvisitor.VEmail);
            if(intenVvisitor.getVgender().equals("male")){
                male.setChecked(true);
                female.setChecked(false);
            }else {
                male.setChecked(false);
                female.setChecked(true);
            }
            // set all data from
            submit.setText("Update");
        } else {
            intentFlag = false;
            submit.setText("Submit");
        }
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Visitor visitor = new Visitor();
                String fname, lname, phone, mail, technique;
                String gender = "";
                boolean check = false;

                if (male.isChecked()) {

                    gender = "Male";
                } else {
                    gender = "feMale";
                }

                fname = Firstname.getText().toString();
                lname = Lastname.getText().toString();
                phone = Phone.getText().toString();
                technique = spinner.getSelectedItem().toString();
                mail = Email.getText().toString().trim();

                if (validate(fname, lname, phone, technique, mail)) {

                    visitor.setVfirstnName(fname);
                    visitor.setVLastName(lname);
                    visitor.setVPhone(phone);
                    visitor.setVEmail(mail);
                    visitor.setVTechnique(technique);
                    visitor.setVgender(gender);


                    if (intentFlag) {
                        visitor.setVisitorId(intenVvisitor.getVisitorId());
                        submit.setText("Update");
                        int result = db.UpdateVisitor(visitor);
                        Toast.makeText(getApplicationContext(), "Updated successfully", Toast.LENGTH_SHORT).show();
                        clearData();
                    } else {
                        submit.setText("Register");
                        Long result = db.insertVisitor(visitor);
                        Log.i("result :", String.valueOf(result));
                        Toast.makeText(getApplicationContext(), "Registered successfully", Toast.LENGTH_SHORT).show();
                        clearData();
                    }
                } else {
                    Toast.makeText(getApplicationContext(), "Invalid email address", Toast.LENGTH_SHORT).show();
                }

            }

        });
    }

    private void clearData() {
        Firstname.getText().clear();
        Lastname.getText().clear();
        Phone.getText().clear();
        Email.getText().clear();
        male.setChecked(false);
        female.setChecked(false);
    }


    public Boolean validate(String fname, String lname, String phone, String technique, String mail) {

        Boolean flag = true;
        String emialpattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
        if (fname.equalsIgnoreCase("")) {
            Toast.makeText(getApplicationContext(), "Firstnme Required", Toast.LENGTH_SHORT).show();
            Firstname.setError("Enter First Name");
            flag = false;
        }
        if (lname.equalsIgnoreCase("")) {
            Toast.makeText(getApplicationContext(), "Lasttnme Required", Toast.LENGTH_SHORT).show();
            Lastname.setError("Enter Last Name");
            flag = false;
        }
        if (phone.equalsIgnoreCase("")) {
            Toast.makeText(getApplicationContext(), "Phone number Required", Toast.LENGTH_SHORT).show();
            Lastname.setError("Enter phone number");
            flag = false;
        }
        if (technique.equalsIgnoreCase("")) {
            Toast.makeText(getApplicationContext(), "Enquiry field Required", Toast.LENGTH_SHORT).show();
            flag = false;
        }
        if (mail.matches(emialpattern)) {
            if ((phone.length() < 10 || phone.length() > 10)) {
                Toast.makeText(getApplicationContext(), "Invalid phone number", Toast.LENGTH_SHORT).show();
                flag = false;
            }
        }
        return flag;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {

            case R.id.item_view:



                Intent intent = new Intent(RegistrationActivity.this, VisitorsListActivity.class);
                startActivity(intent);


                Toast.makeText(getApplicationContext(), "Visitors List", Toast.LENGTH_SHORT).show();

        }
        return super.onOptionsItemSelected(item);
    }

    boolean doubleBackToExitPressedOnce = false;

    @Override
    public void onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            super.onBackPressed();
            return;
        }

        this.doubleBackToExitPressedOnce = true;
        Toast.makeText(this, "Please click BACK again to exit", Toast.LENGTH_SHORT).show();

        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                doubleBackToExitPressedOnce = false;
            }
        }, 2000);
    }

    @Override
    public void onClick(View view) {
        Firstname.getText().clear();
        Lastname.getText().clear();
        Phone.getText().clear();
        Email.getText().clear();
        //   spinner.getText().clear();
        male.setChecked(false);
        female.setChecked(false);
    }
}
