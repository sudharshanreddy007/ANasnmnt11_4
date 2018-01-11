package com.example.user.anasnmnt11_4;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    //created the variables
    TextView textView;
    String[]  firstname, lastname,idnum;
    Students students;
    Dbhandler dbhandler;

    //oncreate method is called with the main method
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //created object for the dbhandler
        dbhandler = new Dbhandler(this);
        // creating object for firstname  and lastname and passing the string values in it
        firstname = new String[]{"Sachin", "MahendraSingh", "Virat", "Rohit", "Suresh"};
        lastname = new String[]{"Tendulkar","Dhoni","Kohli","Sharma","Raina"};

        //and also for idnumber passing the id values
        idnum = new String[]{"1","2","3","4","5"};
        //calling textview from the .xml file
        textView = (TextView) findViewById(R.id.textView);
        //declaring the two methods to inset the values



        loaddatafromarray();

    }
    //method declaration is defining with setters
    public boolean insertvalue(){
        for(int i = 0;i < idnum.length;i++){
            students = new Students();
            students.setid("Id: ");
            students.setidnum(idnum[i]);
            students.setfirstname("First Name: ");
            students.setfirstnamevalue(firstname[i]);
            students.setlastname("Last Name: ");
            students.setlastnamevalue(lastname[i]);
            dbhandler.addvalues(students);


        }
        // by using log checking the activity whether the values are inserted if inserted it returns true
        Log.e("Mainactivity","Values Inserted ");
        return true;

    }
    //declaring the method load data from array
    // This method executes within a transaction. If an exception is thrown, all changes will automatically be rolled back.
    Boolean loaddatafromarray() {
        try {
            ArrayList array_list = dbhandler.databasetoarray();
            Log.e("student List Size ", String.valueOf(array_list.size()));

            if(!array_list.isEmpty()){

                StringBuilder stringBuilderFull,stringBuilderstudents;
                stringBuilderFull = new StringBuilder();


                for (int i=0;i<array_list.size();i++){
                    Students students1 = (Students) array_list.get(i);
                    stringBuilderstudents=  new StringBuilder()
                            .append(students1.getid())
                            .append(students1.getidnum()).append(" ,")
                            .append(students1.getfirstname())
                            .append(students1.getfirstnamevalue()).append(" ,")
                            .append(students1.getlastname())
                            .append(students1.getlastnamevalue())
                            .append("\n").append("\n");
                    stringBuilderFull.append(stringBuilderstudents);

                }

                textView.setText(stringBuilderFull);
                Log.e("MainActivity", "The Student List is displayed.");

            }else {
                Log.e("MainActivity", "No Students available.");
            }

            return true;
        } catch (Exception e) {
            Log.e("MainActivity"," Error is " + e.getLocalizedMessage());

            return false;
        }
    }




}