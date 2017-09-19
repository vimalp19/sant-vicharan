package com.projects.vimal.santvicharan;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.projects.vimal.santvicharan.data.DataConstants;
import com.projects.vimal.santvicharan.data.HomeAddress;
import com.projects.vimal.santvicharan.data.ProfileInfo;

public class AddHaribhakta extends AppCompatActivity {

    private final String CLASS_NAME = this.getClass().getSimpleName();

    private EditText firstName;
    private EditText lastName;
    private Spinner center;
    private EditText emailAddress;
    private EditText phoneNumber;
    private EditText streetAddress;
    private EditText city;
    private Spinner state;
    private EditText zipCode;
    private Button submit;

    private FirebaseDatabase database;
    private DatabaseReference profileInfoTable;
    private DatabaseReference homeAddressTable;


    /**
     * Executed on creation of the acitivity
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_haribhakta);

        //Attach variables to the edit fields on the activity layout
        firstName = findViewById(R.id.addFirstName);
        lastName = findViewById(R.id.addLastName);
        center = findViewById(R.id.addCenter);
        emailAddress = findViewById(R.id.addEmailAddress);
        phoneNumber = findViewById(R.id.addPhoneNumber);
        streetAddress = findViewById(R.id.addStreetAddress);
        city = findViewById(R.id.addCity);
        state = findViewById(R.id.addState);
        zipCode = findViewById(R.id.addZipCode);
        submit = findViewById(R.id.submitButton);

        //Initialize the spinners
        initializeSpinners();

        //Initialize the DB and table references
        initializeDBReferences();


        //Perform the following when submit button is clicked
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //Add the inputted information to add new haribhakta
                addNewHaribhakta();

                Log.i(CLASS_NAME, "Successfully added new haribhakta, transitioning back to main activity");

                //Then, transition back to the Main Activity
                startActivity(new Intent(AddHaribhakta.this, MainActivity.class));
            }
        });
    }


    /**
     * Method to initialize the spinners with the drop-down menu values
     */
    private void initializeSpinners() {

        //Initialize the spinner for the available centers
        ArrayAdapter<CharSequence> centersAadapter = ArrayAdapter.createFromResource(
                this, R.array.centers_array, android.R.layout.simple_spinner_item
        );

        centersAadapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        center.setAdapter(centersAadapter);

        Log.d(CLASS_NAME, "Initialized spinner to populate with the list of centers");

        //Initialize the spinner for the available states
        ArrayAdapter<CharSequence> statesAdapter = ArrayAdapter.createFromResource(
                this, R.array.us_states_array, android.R.layout.simple_spinner_item
        );

        statesAdapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        state.setAdapter(statesAdapter);

        Log.d(CLASS_NAME, "Initialized spinner to populate with the list of US states");
    }


    /**
     * Method for containing the steps for initializing the DB
     * and the tables needed in this activity:
     * PROFILE_INFO_TABLE, HOME_ADDRESS_TABLE
     */
    private void initializeDBReferences() {
        database = FirebaseDatabase.getInstance();

        profileInfoTable = database.getReference().child(DataConstants.PROFILE_INFO_TABLE);
        homeAddressTable = database.getReference().child(DataConstants.HOME_ADDRESS_TABLE);

        Log.d(CLASS_NAME, "Got DB instance and initialized the PROFILE_INFO_TABLE and HOME_ADDRESS_TABLE");
    }


    /**
     * Method to add new haribhakta to the DB
     * using the provided user's inputted information
     */
    private void addNewHaribhakta () {

        //Obtain the inputted values for each of the input fields
        String aFirstName = firstName.getText().toString();
        String aLastName = lastName.getText().toString();
        String aCenter = center.getSelectedItem().toString().toUpperCase();
        String aEmailAddress = emailAddress.getText().toString();
        String aPhoneNumber = phoneNumber.getText().toString();
        String aStreetAddress = streetAddress.getText().toString();
        String aCity = city.getText().toString();
        String aState = state.getSelectedItem().toString();
        int aZipCode = Integer.parseInt(zipCode.getText().toString());

        //Get the new Haribhakta's system ID
        String systemId = profileInfoTable.push().getKey();

        Log.i(CLASS_NAME, "Generated new Haribhakta System ID: " + systemId);

        //TODO - Create utility class to determine the center, region, and country number

        //Add data to the PROFILE_INFO_TABLE
        ProfileInfo profileInfo = new ProfileInfo(systemId, aFirstName,
                aLastName, aCenter, aEmailAddress, aPhoneNumber,
                1, 1, 1);

        profileInfoTable.child(systemId).setValue(profileInfo);

        Log.i(CLASS_NAME, "Adding to PROFILE_INFO_TABLE: " + profileInfo.toString());

        //Add data to the HOME_ADDRESS_TABLE
        HomeAddress homeAddress = new HomeAddress(systemId, aStreetAddress,
                aCity, aState, aZipCode, "US");

        homeAddressTable.push().setValue(homeAddress);

        Log.i(CLASS_NAME, "Adding to HOME_ADDRESS_TABLE: " + homeAddress.toString());
    }
}
