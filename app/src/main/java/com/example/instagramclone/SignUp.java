package com.example.instagramclone;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.SaveCallback;
import com.shashank.sony.fancytoastlib.FancyToast;

public class SignUp extends AppCompatActivity implements View.OnClickListener {

    private Button btnSubmit;
    private EditText edtKickboxerName, edtPunchSpeed, edtPunchPower, edtKickSpeed, edtKickPower;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        btnSubmit = findViewById(R.id.btnSubmit);
        btnSubmit.setOnClickListener(SignUp.this);

        edtKickboxerName = findViewById(R.id.edtKickboxerName);
        edtPunchSpeed = findViewById(R.id.edtPunchSpeed);
        edtPunchPower = findViewById(R.id.edtPunchPower);
        edtKickSpeed = findViewById(R.id.edtKickSpeed);
        edtKickPower = findViewById(R.id.edtKickPower);
    }


    @Override
    public void onClick(View v) {

        try {
            final ParseObject kickboxer = new ParseObject("Kickboxer");
            kickboxer.put("name", edtKickboxerName.getText().toString());
            kickboxer.put("punch_speed", Integer.parseInt(edtPunchPower.getText().toString()));
            kickboxer.put("punch_power", Integer.parseInt(edtPunchPower.getText().toString()));
            kickboxer.put("kick_speed", Integer.parseInt(edtKickSpeed.getText().toString()));
            kickboxer.put("kick_power", Integer.parseInt(edtKickPower.getText().toString()));
            kickboxer.saveInBackground(new SaveCallback() {
                @Override
                public void done(ParseException e) {
                    if (e == null) {
                        FancyToast.makeText(SignUp.this, kickboxer.get("name") + " saved successfully", FancyToast.LENGTH_SHORT, FancyToast.SUCCESS, false).show();
                    } else {
                        FancyToast.makeText(SignUp.this, e.getMessage(), Toast.LENGTH_LONG, FancyToast.ERROR, false).show();
                    }
                }

            });
        } catch (Exception e){
            FancyToast.makeText(SignUp.this, e.getMessage(), Toast.LENGTH_LONG, FancyToast.ERROR, false).show();
        }
    }
}
