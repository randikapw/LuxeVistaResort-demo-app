package com.example.luxevistaresort.views.signin;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.luxevistaresort.R;
import com.example.luxevistaresort.helpers.DBHelper;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Calendar;

public class SignUpActivity extends AppCompatActivity {

    private EditText editNIC, editName, editEmail, editPhone, editTextDate, editPassword;
    private RadioGroup rgGender;
    private Spinner spRole;
    private ImageView imgProfile;
    private TextView dpLogin;
    private Button buttonSign;
    private DBHelper dbHelper;
    private byte[] imageBytes;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_sign_up);

        //declare variables
        TextView text = findViewById(R.id.signUp);
        imgProfile = findViewById(R.id.imgProfile);
        editNIC = findViewById(R.id.editNIC);
        editName = findViewById(R.id.editName);
        editEmail = findViewById(R.id.editEmail);
        editPhone = findViewById(R.id.editPhone);
        editTextDate = findViewById(R.id.editTextDate);
        rgGender = findViewById(R.id.rgGender);
        editPassword = findViewById(R.id.editPassword);
        buttonSign = findViewById(R.id.buttonSign);

        dpLogin = findViewById(R.id.dpLogin);


        dbHelper = new DBHelper(this);

        // Set DatePicker for DOB
        editTextDate.setOnClickListener(v -> showDatePicker());

        // Set Image Picker for profile image
        imgProfile.setOnClickListener(v -> selectImageFromGallery());

        // Set up Register button action
        buttonSign.setOnClickListener(v -> registerUser());

        // Navigate to Login activity
        dpLogin.setOnClickListener(v -> {
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
        });
    }

        private void showDatePicker() {
            Calendar calendar = Calendar.getInstance();
            int year = calendar.get(Calendar.YEAR);
            int month = calendar.get(Calendar.MONTH);
            int day = calendar.get(Calendar.DAY_OF_MONTH);

            DatePickerDialog datePickerDialog = new DatePickerDialog(this, (view, year1, month1, dayOfMonth) -> {
                String selectedDate = dayOfMonth + "/" + (month1 + 1) + "/" + year1;
                editTextDate.setText(selectedDate);
            }, year, month, day);
            datePickerDialog.show();
        }

        private void selectImageFromGallery() {
            Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            startActivityForResult(intent, 100);
        }

        @Override
        protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
            super.onActivityResult(requestCode, resultCode, data);
            if (requestCode == 100 && resultCode == RESULT_OK && data != null) {
                Uri imageUri = data.getData();
                imgProfile.setImageURI(imageUri);
                try {
                    Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), imageUri);
                    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
                    bitmap.compress(Bitmap.CompressFormat.JPEG, 100, outputStream);
                    imageBytes = outputStream.toByteArray();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        private void registerUser() {
            String name = editName.getText().toString();
            String email = editEmail.getText().toString();
            String phone = editPhone.getText().toString();
            String birth= editTextDate.getText().toString();
            String password = editPassword.getText().toString();

            int genderId = rgGender.getCheckedRadioButtonId();
            String gender = genderId != -1 ? ((RadioButton) findViewById(genderId)).getText().toString() : "";

            if (name.isEmpty() || email.isEmpty() || phone.isEmpty() || birth.isEmpty() || password.isEmpty() || gender.isEmpty() || imageBytes == null) {
                Toast.makeText(this, "All fields are required", Toast.LENGTH_SHORT).show();
                return;
            }



            boolean result = dbHelper.insertUser(name, email, phone, birth, gender,"client", password, imageBytes);
            if (result) {
                Toast.makeText(this, "Registration successful!", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(this, LoginActivity.class));
            } else {
                Toast.makeText(this, "Registration failed", Toast.LENGTH_SHORT).show();
            }
        }
    }
