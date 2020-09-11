package com.example.firebase;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {
    EditText textID, textName, textAddress, textContact;
    Button btnSave, btnShow, btnUpdate, btnDelete;
    DatabaseReference dbRef;
    Student student;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textID = findViewById(R.id.userID);
        textName = findViewById(R.id.userName);
        textAddress = findViewById(R.id.userAddress);
        textContact = findViewById(R.id.userContact);

        btnSave = findViewById(R.id.saveBtn);
        btnShow = findViewById(R.id.showBtn);
        btnUpdate = findViewById(R.id.updateBtn);
        btnDelete = findViewById(R.id.deleteBtn);

        student = new Student();

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dbRef = FirebaseDatabase.getInstance().getReference().child("Student");

                try {
                    if(TextUtils.isEmpty(textID.getText().toString())) {
                        Toast.makeText(MainActivity.this, "Please enter an ID", Toast.LENGTH_SHORT).show();
                    } else if(TextUtils.isEmpty(textName.getText().toString())) {
                        Toast.makeText(MainActivity.this, "Please enter a Name", Toast.LENGTH_SHORT).show();
                    } else if(TextUtils.isEmpty(textAddress.getText().toString())) {
                        Toast.makeText(MainActivity.this, "Please enter an Address", Toast.LENGTH_SHORT).show();
                    } else {
                        student.setID(textID.getText().toString().trim());
                        student.setName(textName.getText().toString().trim());
                        student.setAddress(textAddress.getText().toString().trim());
                        student.setContactNo(Integer.parseInt(textContact.getText().toString().trim()));

                        dbRef.push().setValue(student);
                        Toast.makeText(MainActivity.this, "Data Saved Successfully", Toast.LENGTH_SHORT).show();
                        clearControls();
                    }
                } catch (NumberFormatException e) {
                    e.printStackTrace();
                    Toast.makeText(MainActivity.this, "Invalid Contact Number", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnShow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }

    private void clearControls() {
        textID.setText("");
        textName.setText("");
        textAddress.setText("");
        textContact.setText("");
    }
}