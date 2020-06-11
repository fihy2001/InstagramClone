package com.example.instagramclone;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

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

        btnSignUp = findViewById(R.id.btnSignUpLogin);
        btnLogin = findViewById(R.id.btnLogin);

        btnLogin.setOnClickListener(this);
        btnSignUp.setOnClickListener(this);

        if (ParseUser.getCurrentUser() != null){
            ParseUser.getCurrentUser().logOut();
        }

    }

    @Override
    public void onClick(View view) {

        switch (view.getId()){
            case R.id.btnLogin:
                ParseUser.logInInBackground(edtEmail.getText().toString(), edtPassword.getText().toString(), new LogInCallback() {
                    @Override
                    public void done(ParseUser user, ParseException e) {
                        if (user != null && e == null){
                            FancyToast.makeText(Login.this, user.get("username") + " logged in successfully", FancyToast.LENGTH_SHORT, FancyToast.SUCCESS, false).show();
//                            Intent intent = new Intent(SignUpLoginActivity.this, WelcomeActivity.class);
//                            startActivity(intent);
                        }else {
                            FancyToast.makeText(Login.this, e.getMessage(), Toast.LENGTH_LONG, FancyToast.ERROR, false).show();
                        }
                    }


                });
                break;
            case R.id.btnSignUpLogin:

                finish();
                break;
        }

    }
}
