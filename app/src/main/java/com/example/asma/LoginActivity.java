package com.example.asma;

import android.content.Intent;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    private TextInputLayout EmailInput;
    private TextInputLayout PassInput;
    Button SignUp;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        EmailInput = findViewById(R.id.email_input_edtx);
        PassInput = findViewById(R.id.password_input_edtx);
        SignUp = findViewById(R.id.SignUp);

        SignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent sign = new Intent(LoginActivity.this,SignActivity.class);
                startActivity(sign);
            }
        });

    }
    private boolean validateEmail() {
        String emailInput = EmailInput.getEditText().getText().toString().trim();
        if (emailInput.isEmpty()) {
            EmailInput.setError( getString(R.string.Failed_canot_be_empty));
            return false;
        } else if (!Patterns.EMAIL_ADDRESS.matcher(emailInput).matches()) {
            EmailInput.setError( getString(R.string.Please_Enter_valid_Email));
            return false;
        } else {
            EmailInput.setError(null);
            return true;
        }
    }

    private boolean validatePass() {
        String passInput = PassInput.getEditText().getText().toString().trim();
        if (passInput.isEmpty()) {
            PassInput.setError( getString(R.string.Failed_canot_be_empty));
            return false;
        } else {
            PassInput.setError(null);
            return true;
        }
    }

    public void loginInput(View v){

        if(!validateEmail() || !validatePass()){
            return;
        }
        else if(validatePass() && validateEmail()
                && EmailInput.getEditText().getText().toString().trim().equals("asmaadel053@gmail.com")
                && PassInput.getEditText().getText().toString().trim().equals("12345")){
            Intent home = new Intent(LoginActivity.this,HomeActivity.class);
            startActivity(home);
        }

        else {
         Toast.makeText(this, getString(R.string.Email_or_Password_not_correct), Toast.LENGTH_LONG).show();
         }
    }
}
