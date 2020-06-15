package com.example.instagramclone;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;

import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SaveCallback;
import com.shashank.sony.fancytoastlib.FancyToast;

import static androidx.core.content.ContextCompat.getSystemService;


/**
 * A simple {@link Fragment} subclass.
 */
public class ProfileTab extends Fragment {

    private EditText edtProfileName, edtProfileBio, edtProfileProfession, edtProfileHobbies, edtProfileFavoriteSport;
    private Button btnUpdateInfo;


    public ProfileTab() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_profile_tab, container, false);
        edtProfileName = view.findViewById(R.id.edtProfileName);
        edtProfileBio = view.findViewById(R.id.edtProfileBio);
        edtProfileProfession = view.findViewById(R.id.edtProfileProfession);
        edtProfileHobbies = view.findViewById(R.id.edtProfileHobbies);
        edtProfileFavoriteSport = view.findViewById(R.id.edtProfileFavoriteSport);
        btnUpdateInfo = view.findViewById(R.id.btnUpdateInfo);

        final ParseUser parseUser = ParseUser.getCurrentUser();

        if (parseUser.get("profileName") == null){
            edtProfileName.setText("");
        }
        else {
            edtProfileName.setText(parseUser.get("profileName") + "");
        }
        if (parseUser.get("profileBio") == null){
            edtProfileBio.setText("");
        }
        else {
            edtProfileBio.setText(parseUser.get("profileBio") + "");
        }
        if (parseUser.get("profileProfession") == null) {
            edtProfileProfession.setText("");
        }
        else {
            edtProfileProfession.setText(parseUser.get("profileProfession") + "");
        }
        if (parseUser.get("profileHobbies") == null){
            edtProfileHobbies.setText("");
        }
        else {
            edtProfileHobbies.setText(parseUser.get("profileHobbies") + "");
        }
        if (parseUser.get("profileFavoriteSport") == null){
            edtProfileFavoriteSport.setText("");
        }
        else {
            edtProfileFavoriteSport.setText(parseUser.get("profileFavoriteSport") + "");
        }

        btnUpdateInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                parseUser.put("profileName", edtProfileName.getText().toString());
                parseUser.put("profileBio", edtProfileBio.getText().toString());
                parseUser.put("profileProfession", edtProfileProfession.getText().toString());
                parseUser.put("profileHobbies", edtProfileHobbies.getText().toString());
                parseUser.put("profileFavoriteSport", edtProfileFavoriteSport.getText().toString());

                parseUser.saveInBackground(new SaveCallback() {
                    @Override
                    public void done(ParseException e) {
                        if (e == null){
                            FancyToast.makeText(getContext(), "Profile Updated", FancyToast.LENGTH_SHORT, FancyToast.INFO, false).show();
                        }
                        else {
                            FancyToast.makeText(getContext(), e.getMessage(), FancyToast.LENGTH_SHORT, FancyToast.ERROR, false).show();
                        }

                    }
                });

            }
        });

        return view;

    }
}
