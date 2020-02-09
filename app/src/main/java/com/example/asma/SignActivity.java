package com.example.asma;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.text.DateFormat;
import java.util.Calendar;
import java.util.regex.Pattern;

import de.hdodenhof.circleimageview.CircleImageView;

public class SignActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {
    boolean validateprofileimg ;
    TextView log , Sign_Birth ;
    String SignpassInput,SignConpassInput,SignPhoneInput,SignBirthInput,SignCountryInput;
    Button Sign_btn;
    AutoCompleteTextView Sign_Country;
    private TextInputLayout Sign_Email;
    private TextInputLayout Sign_Pass;
    private TextInputLayout Sign_Confirm_Pass;
    private TextInputLayout Sign_Phone;
    private TextInputLayout Sign_Birth2;
    private CircleImageView Sign_Img;
    private static final int PICK_IMAGE = 1;
    Uri imageuri;
    private static final String[] COUNTRIES = new String[]{
            "@string/Afghanistan","@string/Albania","@string/Algeria","@string/Andorra","@string/Angola" };
    private static final Pattern PASSWORD_PATTERN = Pattern.compile("^" +
                                                                    "(?=.*[0-9])" +
                                                                    "(?=.*[a-z])" +
                                                                    "(?=.*[A-Z])" +
                                                                    "(?=.*[@#$%^&+=])" +
                                                                    ".{6,}" +
                                                                    "$");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign);

        validateprofileimg = false;
        Sign_Email = findViewById(R.id.sign_email);
        Sign_Pass = findViewById(R.id.sign_pass);
        Sign_Confirm_Pass = findViewById(R.id.sign_pass_confirm);
        Sign_btn = findViewById(R.id.sign_btn);
        Sign_Phone = findViewById(R.id.sign_phone);
        Sign_Birth = findViewById(R.id.sign_birth);
        Sign_Birth2 = findViewById(R.id.sign_birth2);
        Sign_Img = findViewById(R.id.sign_profile_image);
        Sign_Country = findViewById(R.id.sign_country);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,COUNTRIES);
        Sign_Country.setAdapter(adapter);
        /*
        SignCountryInput=Sign_Country.getEditableText().toString().trim();
         */
        log = findViewById(R.id.sign_login);
        log.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent tohome = new Intent(SignActivity.this,HomeActivity.class);
                startActivity(tohome);
            }
        });
        Sign_Birth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment datePicker = new DatePickerFragment();
                datePicker.show(getSupportFragmentManager(), "date picker");
            }
        });
        Sign_Img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gallery = new Intent();
                gallery.setType("image/*");
                gallery.setAction(Intent.ACTION_GET_CONTENT);

                startActivityForResult(Intent.createChooser(gallery,"Sellect picture"),PICK_IMAGE);

            }
        });

        Sign_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(validateEmail() && validatePass() && validateConPass() && validatePhone() && validatebirth() && validateprofileimg2()
                ){
                    Intent thome = new Intent(SignActivity.this,HomeActivity.class);
                    startActivity(thome);
                    finish();
                }

            }
        });
    }

    private boolean validateEmail() {
        String SignemailInput = Sign_Email.getEditText().getText().toString().trim();
        if (SignemailInput.isEmpty()) {
            Sign_Email.setError( getString(R.string.Failed_canot_be_empty) );
            return false;
        } else if (!Patterns.EMAIL_ADDRESS.matcher(SignemailInput).matches()) {
            Sign_Email.setError(getString(R.string.Please_Enter_valid_Email));
            return false;
        } else {
            Sign_Email.setError(null);
            return true;
        }
    }

    private boolean validatePass() {
        SignpassInput = Sign_Pass.getEditText().getText().toString().trim();
        if (SignpassInput.isEmpty()) {
            Sign_Pass.setError( getString(R.string.Failed_canot_be_empty));
            return false;
        }else if(!PASSWORD_PATTERN.matcher(SignpassInput).matches()){
           Sign_Pass.setError( getString(R.string.Password_too_weak));
           return false;
        }else {
            Sign_Pass.setError(null);
            return true;
        }
    }

    private boolean validateConPass() {
        SignConpassInput = Sign_Confirm_Pass.getEditText().getText().toString().trim();
        if (SignConpassInput.isEmpty()) {
            Sign_Confirm_Pass.setError( getString(R.string.Failed_canot_be_empty));
            return false;
        }else if(!SignConpassInput.equals(SignpassInput)){
            Sign_Confirm_Pass.setError( getString(R.string.Passwords_are_not_same));
            return false;
        }else {
            Sign_Pass.setError(null);
            return true;
        }
    }

    private boolean validatePhone() {
        SignPhoneInput = Sign_Phone.getEditText().getText().toString().trim();
        if (SignPhoneInput.isEmpty()) {
            Sign_Phone.setError( getString(R.string.Failed_canot_be_empty));
            return false;
        }else if(SignPhoneInput.length()!=11){
            Sign_Phone.setError( getString(R.string.Phone_number_not_valid));
            return false;
        }else {
            Sign_Phone.setError(null);
            return true;
        }
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        Calendar c = Calendar.getInstance();
        c.set(Calendar.YEAR , year);
        c.set(Calendar.MONTH , month);
        c.set(Calendar.DAY_OF_MONTH , dayOfMonth);
        String currentDateString = DateFormat.getDateInstance(DateFormat.FULL).format(c.getTime());
        Sign_Birth2.getEditText().setText(currentDateString);
    }
    private boolean validatebirth(){
        SignBirthInput = Sign_Birth2.getEditText().getText().toString().trim();
        if(SignBirthInput.isEmpty()){
            Sign_Birth2.setError( getString(R.string.Failed_canot_be_empty));
            return false;
        }else {
            Sign_Birth2.setError(null);
            return true;
        }
    }
    private boolean validateprofileimg2(){
        if(!validateprofileimg){
            Toast.makeText(this ,getString(R.string.Please_take_profile),Toast.LENGTH_SHORT).show();
            return false;
        }else {
            return true;
        }
    }
    private boolean validatecountry(){
        if(SignCountryInput.isEmpty()){
            Toast.makeText(this , getString(R.string.Please_select_Country),Toast.LENGTH_SHORT).show();
            return false;
        }else {
            return true;
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == PICK_IMAGE && resultCode == RESULT_OK){
            imageuri = data.getData();
            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(),imageuri);
                Sign_Img.setImageBitmap(bitmap);
                validateprofileimg = true;
            }catch (IOException e){
                e.printStackTrace();
            }
        }
    }
}
