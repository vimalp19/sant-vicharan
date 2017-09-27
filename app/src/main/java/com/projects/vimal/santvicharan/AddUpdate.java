package com.projects.vimal.santvicharan;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.projects.vimal.santvicharan.data.DataConstants;
import com.projects.vimal.santvicharan.data.Niyams;
import com.projects.vimal.santvicharan.data.Update;

import java.util.Calendar;
import java.util.Date;

public class AddUpdate extends AppCompatActivity {

    private final String CLASS_NAME = this.getClass().getSimpleName();

    private EditText notes;
    private Button submit;
    private CheckBox puja, cheshta, gujaratiReading, satsangReading;
    private CheckBox noOG, extraMalas, bowToParents, gharSabha;

    private String systemId;

    private FirebaseDatabase database;
    private DatabaseReference vicharanUpdateTable;


    /**
     * Executed on creation of the activity
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_update);

        //Attach variables to field/button in the activity
        notes = findViewById(R.id.updateToAdd);
        submit = findViewById(R.id.addUpdateButton);
        puja = findViewById(R.id.pujaCB);
        cheshta = findViewById(R.id.tilakChandloCB);
        gujaratiReading = findViewById(R.id.gujaratiReadingCB);
        satsangReading = findViewById(R.id.satsangReadingCB);
        noOG = findViewById(R.id.NoOGCB);
        extraMalas = findViewById(R.id.extraMalasCB);
        bowToParents = findViewById(R.id.bowToParentsCB);
        gharSabha = findViewById(R.id.gharSabhaCB);

        //Get the Haribhakta's system ID
        getSystemId();

        //Initialize the DB and table references
        initializeDBReferences();


        //Perform the following when submit button is clicked
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //Add provided notes for new update
                addNewUpdate();

                Log.i(CLASS_NAME, "Successfully added new update," +
                        "transitioning back to the Haribhakta's updates activity");

                //Then, transition back to the Haribhakta Updates activity
                Intent intent = new Intent(AddUpdate.this, HaribhaktaUpdate.class);
                intent.putExtra(MainActivity.EXTRA_MESSAGE, systemId);
                startActivity(intent);
            }
        });
    }


    /**
     * Method to get the Haribhakta's system ID from previous activity
     */
    private void getSystemId() {
        Intent intent = getIntent();
        systemId = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);

        Log.w(CLASS_NAME, "Received Haribhakta: " + systemId);
    }


    /**
     * Method for containing the steps for initializing the DB
     * and the tables needed in this activity:
     * VICHARAN_UPDATE_TABLE
     */
    private void initializeDBReferences() {
        database = FirebaseDatabase.getInstance();
        vicharanUpdateTable = database.getReference().child(DataConstants.VICHARAN_UPDATE_TABLE);

        Log.d(CLASS_NAME, "Got DB instance and initialized the VICHARAN_UPDATE_TABLE");
    }


    /**
     * Method to add a new update for the haribhakta
     * using the inputted data
     */
    private void addNewUpdate() {

        String aNotes = notes.getText().toString();

        //TODO - Implement functionality for user to generate date
        Date aCurrentTime = Calendar.getInstance().getTime();

        //Get the ID for the new update
        String id = vicharanUpdateTable.push().getKey();

        Log.i(CLASS_NAME, "Generated new ID for update: " + id);

        //Get the values for the checkboxes
        Niyams niyams = new Niyams(puja.isChecked(), cheshta.isChecked(),
                gujaratiReading.isChecked(), satsangReading.isChecked(),
                noOG.isChecked(), extraMalas.isChecked(), bowToParents.isChecked(),
                gharSabha.isChecked());

        //Add data to VICHARAN_UPDATE_TABLE
        Update update = new Update(systemId, aCurrentTime, aNotes, id, niyams);
        vicharanUpdateTable.child(id).setValue(update);

        Log.i(CLASS_NAME, update.getSystemId() + " " + aCurrentTime.toString() + " " + aNotes + " " + id);

        Log.i(CLASS_NAME, "Adding to VICHARAN_UPDATE_TABLE: " + update.toString());
    }
}
