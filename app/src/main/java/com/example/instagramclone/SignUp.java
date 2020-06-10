package com.example.instagramclone;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.SaveCallback;
import com.shashank.sony.fancytoastlib.FancyToast;

import java.util.List;

public class SignUp extends AppCompatActivity implements View.OnClickListener {

    private Button btnSubmit;
    private Button btnGetAllKickboxers;
    private Button btnTransition;
    private EditText edtKickboxerName, edtPunchSpeed, edtPunchPower, edtKickSpeed, edtKickPower;
    private TextView txtGetData;

    private String allKickBoxers;


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

        txtGetData = findViewById(R.id.txtGetData);
        btnGetAllKickboxers = findViewById(R.id.btnGetAllData);
        btnTransition = findViewById(R.id.btnNextActivity);

        btnGetAllKickboxers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                allKickBoxers = "";

                ParseQuery<ParseObject> queryAll = ParseQuery.getQuery("Kickboxer");

                queryAll.whereLessThan("kick_speed", 400);
                queryAll.setLimit(1);

                queryAll.findInBackground(new FindCallback<ParseObject>() {
                    @Override
                    public void done(List<ParseObject> objects, ParseException e) {
                        if (e == null){
                            if (objects.size() > 0){
                                for (ParseObject kickBoxer : objects){
                                    allKickBoxers = allKickBoxers + kickBoxer.get("name") + kickBoxer.get("punch_power") +
                                            kickBoxer.get("punch_speed")+ "\n";
                                }
                                FancyToast.makeText(SignUp.this,  allKickBoxers, FancyToast.LENGTH_SHORT, FancyToast.SUCCESS, false).show();
                            }
                            else {
                                FancyToast.makeText(SignUp.this, e.getMessage(), Toast.LENGTH_LONG, FancyToast.ERROR, false).show();
                            }
                        }
                    }
                });
            }
        });

        txtGetData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ParseQuery<ParseObject> parseQuery = ParseQuery.getQuery("Kickboxer");
                parseQuery.getInBackground("RXSXcTRpSJ", new GetCallback<ParseObject>() {
                    @Override
                    public void done(ParseObject object, ParseException e) {
                        if (object != null && e == null){
                            txtGetData.setText(object.get("name") + "" + " - " +"Punch Power: " + object.get("punch_power"));
                        }
                    }
                });
            }
        });
        btnTransition.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SignUp.this, SignUpLoginActivity.class);
                startActivity(intent);

            }
        });
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
