package com.example.instagramclone;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;
import com.shashank.sony.fancytoastlib.FancyToast;

public class SignUp extends AppCompatActivity implements View.OnClickListener{

    private EditText edtEmail, edtPasswordSignUp, edtUserNameSignUp;
    private Button btnSignUp;
    private Button btnLogin;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup_activity);
        setTitle(R.string.signup_title);

        edtEmail = findViewById(R.id.edtEmailSignUp);
        edtUserNameSignUp = findViewById(R.id.edtUsernameSignUp);
        edtPasswordSignUp = findViewById(R.id.edtPasswordSignUp);

        btnSignUp = findViewById(R.id.btnSignUp);
        btnLogin = findViewById(R.id.btnLoginSignUp);

        btnSignUp.setOnClickListener(this);
        btnLogin.setOnClickListener(this);

        if (ParseUser.getCurrentUser() != null){
            ParseUser.getCurrentUser().logOut();
        }


    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {

            case R.id.btnSignUp:
                final ParseUser appUser = new ParseUser();
                appUser.setEmail(edtEmail.getText().toString());
                appUser.setUsername(edtUserNameSignUp.getText().toString());
                appUser.setPassword(edtPasswordSignUp.getText().toString());

                final ProgressDialog progressDialog = new ProgressDialog(this);
                progressDialog.setMessage("Signing up " + edtUserNameSignUp.getText().toString());
                progressDialog.show();

                appUser.signUpInBackground(new SignUpCallback() {
                    @Override
                    public void done(ParseException e) {
                        if (e == null){
                            FancyToast.makeText(SignUp.this, appUser.get("username") + " added successfully", FancyToast.LENGTH_SHORT, FancyToast.SUCCESS, false).show();
//                            Intent intent = new Intent(SignUpLoginActivity.this, WelcomeActivity.class);
//                            startActivity(intent);
                        } else {
                            FancyToast.makeText(SignUp.this, e.getMessage(), Toast.LENGTH_LONG, FancyToast.ERROR, false).show();
                        }
                        progressDialog.dismiss();

                    }
                });
                break;
            case R.id.btnLoginSignUp:
                Intent intent = new Intent(SignUp.this, Login.class);
                startActivity(intent);
                break;
        }

    }
}
