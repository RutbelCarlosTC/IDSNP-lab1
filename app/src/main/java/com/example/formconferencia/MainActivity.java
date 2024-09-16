package com.example.formconferencia;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.formconferencia.databinding.ActivityMainBinding;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class MainActivity extends AppCompatActivity {

    private EditText edtFirstName, edtLastName, edtEmail, edtPhone, edtBloodGroup;
    private Button btnSave, btnRead;
    private final String FILE_NAME = "attendees.txt";
    private static String TAG = "MainActivity";
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        EditText edtFirstName = binding.edtFirstName;
        EditText edtLastName = binding.edtLastName;
        EditText edtEmail = binding.edtEmail;
        EditText edtPhone = binding.edtPhone;
        EditText edtBloodGroup = binding.edtBloodGroup;
        EditText edtInstitution = binding.edtInstitution;

        Button btnRead = binding.btnRead;
        Button btnSave = binding.btnSave;

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String firstName = edtFirstName.getText().toString();
                String lastName = edtLastName.getText().toString();
                String email = edtEmail.getText().toString();
                String phone = edtPhone.getText().toString();
                String bloodGroup = edtBloodGroup.getText().toString();
                String institution = edtInstitution.getText().toString();

                if (firstName.isEmpty() || lastName.isEmpty() || email.isEmpty() || phone.isEmpty() || bloodGroup.isEmpty()){
                    Toast.makeText(MainActivity.this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
                    Log.d(TAG,"Please fill in all fields");
                }else{
                    saveData(firstName, lastName, email, phone, bloodGroup, institution);
                }
            }
        });
        btnRead.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                readData();
            }
        });
    }

    //Guardar los datos en un archivo de texto
    private void saveData(String firstName, String lastName, String email, String phone, String bloodGroup, String institution){
        String data  =  "First Name: " + firstName + "\n" +
                        "Last Name: " + lastName + "\n" +
                        "Email: " + email + "\n" +
                        "Phone: " + phone + "\n" +
                        "Blood Group: " + bloodGroup + "\n" +
                        "Institution: " + institution + "\n";

        try{
            FileOutputStream fos  = openFileOutput(FILE_NAME, MODE_APPEND);
            fos.write(data.getBytes());
            fos.close();
            Toast.makeText(this, "Data saved successufully! ",Toast.LENGTH_SHORT).show();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    private void readData() {
        try {
            FileInputStream fis = openFileInput(FILE_NAME);
            BufferedReader reader = new BufferedReader(new InputStreamReader(fis));
            String line;
            while ((line = reader.readLine()) != null) {
                Log.d(TAG, line);
            }
            reader.close();
            Toast.makeText(this, "Data read successfully! Check log", Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(this, "Error reading data", Toast.LENGTH_SHORT).show();
        }
    }

}