package com.example.jay.quiz;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.example.jay.quiz.QuizContainer.*;

public class Register extends AppCompatActivity {

    EditText Email, Password, Name;
    Button Register;
    String NameHolder, EmailHolder, PasswordHolder;
    Boolean EditTextEmptyHolder=true;
    SQLiteDatabase sqLiteDatabaseObj;

    SQLiteHelper sqLiteHelper;
    Cursor cursor;
    String F_Result = "Not_Found";
    public static final String UserName = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        Register = findViewById(R.id.buttonRegister);


        Email = findViewById(R.id.editEmail);
        Password = findViewById(R.id.editPassword);
        Name = findViewById(R.id.editName);

        sqLiteHelper = new SQLiteHelper(this);


        Register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                SQLiteDataBaseBuild();

                SQLiteTableBuild();

                CheckEditTextStatus();

                CheckingEmailAlreadyExistsOrNot();


                EmptyEditTextAfterDataInsert();

            }
        });

    }

    public void SQLiteDataBaseBuild() {

        sqLiteDatabaseObj = openOrCreateDatabase(SQLiteHelper.DATABASE_NAME, Context.MODE_PRIVATE, null);

    }

    public void SQLiteTableBuild() {

        sqLiteDatabaseObj.execSQL("CREATE TABLE IF NOT EXISTS " + User.TABLE_NAME + "(" + User.Table_Column_ID + " PRIMARY KEY AUTOINCREMENT NOT NULL, " + User.Table_Column_1_Name + " VARCHAR, " + User.Table_Column_2_Email + " VARCHAR, " + User.Table_Column_3_Password + " VARCHAR);");

    }

    public void InsertDataIntoSQLiteDatabase() {
        long a;
        if (EditTextEmptyHolder == true) {
            ContentValues contentValues = new ContentValues();
            contentValues.put("name", NameHolder);
            contentValues.put("email", EmailHolder);
            contentValues.put("password", PasswordHolder);
            a = sqLiteHelper.insert(User.TABLE_NAME, contentValues, User.Table_Column_ID);
            if (a > 0) {
                Toast.makeText(this, "Data Inserted", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(Register.this, Main2Activity.class);

                intent.putExtra(UserName,NameHolder);
                startActivity(intent);

            }
            sqLiteDatabaseObj.close();

        }
    }


    public void EmptyEditTextAfterDataInsert() {

        Name.getText().clear();

        Email.getText().clear();

        Password.getText().clear();

    }

    public void CheckEditTextStatus() {

        NameHolder = Name.getText().toString();
        EmailHolder = Email.getText().toString();
        PasswordHolder = Password.getText().toString();

        if (TextUtils.isEmpty(NameHolder) || TextUtils.isEmpty(EmailHolder) || TextUtils.isEmpty(PasswordHolder)) {

            EditTextEmptyHolder = false;

        } else {

            EditTextEmptyHolder = true;
        }
    }

    public void CheckingEmailAlreadyExistsOrNot() {

        sqLiteDatabaseObj = sqLiteHelper.getWritableDatabase();

        cursor = sqLiteDatabaseObj.query(User.TABLE_NAME, null, " " + User.Table_Column_2_Email + "=?", new String[]{EmailHolder}, null, null, null);

        while (cursor.moveToNext()) {

            if (cursor.isFirst()) {

                cursor.moveToFirst();

                F_Result = "Email Found";

                cursor.close();
            }
        }

        CheckFinalResult();

    }


    public void CheckFinalResult() {

        if (F_Result.equalsIgnoreCase("Email Found")) {

            Toast.makeText(Register.this, "Email Already Exists", Toast.LENGTH_LONG).show();

        } else {

            InsertDataIntoSQLiteDatabase();

        }

        F_Result = "Not_Found";
    }
}
