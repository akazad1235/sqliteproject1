package com.example.akazad.sqliteproject1;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText nameET, ageET, genderET, idET;
    MyDBhelper myDBhelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nameET=findViewById(R.id.nameId);
        ageET=findViewById(R.id.ageId);
        genderET=findViewById(R.id.genderId);
        idET=findViewById(R.id.idED);

        myDBhelper =new MyDBhelper(this);
        SQLiteDatabase sqLiteDatabase=myDBhelper.getReadableDatabase();
    }


    public void SaveButton(View view){

            String name=nameET.getText().toString();
            String age=ageET.getText().toString();
            String gender=genderET.getText().toString();
           String id=idET.getText().toString();

           long rowId= myDBhelper.insertData(name, age, gender);
           if (rowId==-1){

               Toast.makeText(getApplicationContext(), "Insert Faild", Toast.LENGTH_LONG).show();

           }else {
               Toast.makeText(getApplicationContext(), "Row "+ rowId +"inserted successfully ", Toast.LENGTH_LONG).show();
           }


    }


    public void ShowData(View view) {
           Cursor cursor= myDBhelper.displayAllData();

           if (cursor.getCount()==0){
               //no data in this table
               showdata("Empty", "No data in this dtabase");
               AlertDialog.Builder builder=new AlertDialog.Builder(this);
               builder.setIcon(R.drawable.ic_hourglass_empty_black_24dp);
               builder.show();
               return;
           }

           StringBuffer stringBuffer=new StringBuffer();
           while (cursor.moveToNext()){

               stringBuffer.append("DI: "+cursor.getInt(0)+"\n");
               stringBuffer.append("Name: "+cursor.getString(1)+"\n");
               stringBuffer.append("Age: "+cursor.getString(2)+"\n");
               stringBuffer.append("Gender: "+cursor.getString(3)+"\n");
           }

           showdata("ResultSet", stringBuffer.toString());

    }
    public void showdata(String title, String message){

        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.setCancelable(true);
        builder.setIcon(R.drawable.ic_hourglass_empty_black_24dp);
        builder.show();
    }

    public void UpdateData(View view) {
        String name=nameET.getText().toString();
        String age=ageET.getText().toString();
        String gender=genderET.getText().toString();
        String id=idET.getText().toString();
       boolean isUpdate= myDBhelper.updateData(name, age, gender, id );

       if (isUpdate==true){

           Toast.makeText(getApplicationContext(), "Data update Successfully", Toast.LENGTH_LONG).show();
       }else{
           Toast.makeText(getApplicationContext(), "Data not Update", Toast.LENGTH_LONG).show();
       }
    }

    public void DeleteData(View view) {

        String id=idET.getText().toString();
        int value = myDBhelper.deletedata(id);

        if (value>0){
            Toast.makeText(getApplicationContext(), "Data Delete Successfully", Toast.LENGTH_SHORT).show();

        }else{
            Toast.makeText(getApplicationContext(), "Data not deleted", Toast.LENGTH_LONG).show();
        }




    }
}
