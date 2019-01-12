package com.example.jay.quiz;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.example.jay.quiz.QuizContainer.*;

public class MainActivity extends AppCompatActivity {

    Button LogInButton, RegisterButton;
    EditText Email, Password ;
    String EmailHolder, PasswordHolder, NameHolder;
    Boolean EditTextEmptyHolder;
    SQLiteDatabase sqLiteDatabaseObj;
    SQLiteHelper sqLiteHelper;
    Cursor cursor;
    String TempPassword = "NOT_FOUND" ;
    public static final String UserName = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        LogInButton = findViewById(R.id.buttonLogin);

        RegisterButton = findViewById(R.id.buttonRegister);

        Email = findViewById(R.id.editEmail);
        Password = findViewById(R.id.editPassword);



        sqLiteHelper = new SQLiteHelper(this);


        LogInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                CheckEditTextStatus();


                LoginFunction();


            }
        });



        RegisterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Intent intent = new Intent(MainActivity.this, Register.class);
                startActivity(intent);

            }
        });

    }


    public void LoginFunction(){

        if(EditTextEmptyHolder) {


            sqLiteDatabaseObj = sqLiteHelper.getWritableDatabase();


            cursor = sqLiteDatabaseObj.query(User.TABLE_NAME, null, " " + User.Table_Column_2_Email + "=?", new String[]{EmailHolder}, null, null, null);

            while (cursor.moveToNext()) {

                if (cursor.isFirst()) {

                    cursor.moveToFirst();


                    TempPassword = cursor.getString(cursor.getColumnIndex(User.Table_Column_3_Password));
                    NameHolder = cursor.getString(cursor.getColumnIndex(User.Table_Column_1_Name));



                    cursor.close();
                }
            }


            FinalResult();

        }
        else {

            Toast.makeText(MainActivity.this,"Please Enter UserName or Password.",Toast.LENGTH_LONG).show();

        }

    }

    public void CheckEditTextStatus(){

        EmailHolder = Email.getText().toString();
        PasswordHolder = Password.getText().toString();



        if( TextUtils.isEmpty(EmailHolder) || TextUtils.isEmpty(PasswordHolder)){

            EditTextEmptyHolder = false ;

        }
        else {

            EditTextEmptyHolder = true ;
        }
    }

    public void FinalResult(){

        if(TempPassword.equalsIgnoreCase(PasswordHolder))
        {

            Toast.makeText(MainActivity.this,"Login Successfully",Toast.LENGTH_LONG).show();

            Intent intent = new Intent(MainActivity.this, Main2Activity.class);

            intent.putExtra(UserName,NameHolder);
            EmptyAfterDataInsert();
            startActivity(intent);


        }
        else {

            Toast.makeText(MainActivity.this,"UserName or Password is Wrong, Please Try Again.",Toast.LENGTH_LONG).show();

        }
        TempPassword = "NOT_FOUND" ;

    }
    public void EmptyAfterDataInsert()
    {
        Email.getText().clear();
        Password.getText().clear();
    }

}

