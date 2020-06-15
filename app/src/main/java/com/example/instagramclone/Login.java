package com.example.instagramclone;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.shashank.sony.fancytoastlib.FancyToast;

public class Login extends AppCompatActivity implements View.OnClickListener{

    private EditText edtEmail, edtPassword;
    private Button btnSignUp, btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);
        setTitle(R.string.login_title);

        edtEmail = findViewById(R.id.edtEmailLogin);
        edtPassword = findViewById(R.id.edtPasswordLogin);

        edtPassword.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_ENTER && event.getAction() == KeyEvent.ACTION_DOWN) {
                    onClick(btnSignUp);
                }
                return false;
            }
        });

        btnSignUp = findViewById(R.id.btnSignUpLogin);
        btnLogin = findViewById(R.id.btnLogin);

        btnLogin.setOnClickListener(this);
        btnSignUp.setOnClickListener(this);

        if (ParseUser.getCurrentUser() != null){
 //           ParseUser.getCurrentUser().logOut();
            transitionToSocialMediaActivity();
        }

    }

    @Override
    public void onClick(View view) {

        switch (view.getId()){
            case R.id.btnLogin:
                if (edtEmail.getText().toString().equals("") || edtPassword.getText().toString().equals("")) {

                    FancyToast.makeText(Login.this, "Email and Password are required!", FancyToast.LENGTH_SHORT, FancyToast.INFO, false).show();

                }
                else {
                    ParseUser.logInInBackground(edtEmail.getText().toString(), edtPassword.getText().toString(), new LogInCallback() {
                        @Override
                        public void done(ParseUser user, ParseException e) {
                            if (user != null && e == null) {
                                FancyToast.makeText(Login.this, user.get("username") + " logged in successfully", FancyToast.LENGTH_SHORT, FancyToast.SUCCESS, false).show();
                                transitionToSocialMediaActivity();
//                            Intent intent = new Intent(SignUpLoginActivity.this, WelcomeActivity.class);
//                            startActivity(intent);
                            } else {
                                FancyToast.makeText(Login.this, e.getMessage(), Toast.LENGTH_LONG, FancyToast.ERROR, false).show();
                            }
                        }


                    });
                }
                break;
            case R.id.btnSignUpLogin:

                Intent intent = new Intent(Login.this, SignUp.class);
                startActivity(intent);
//                finish();
                break;
        }

    }
    public void rootLayoutTappedLogin(View view){

        try{
            InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
        } catch (Exception e){
            e.printStackTrace();
        }

    }
    private void transitionToSocialMediaActivity(){

        Intent intent = new Intent(Login.this,SocialMediaActivity.class);
        startActivity(intent);
        finish();
    }
}
