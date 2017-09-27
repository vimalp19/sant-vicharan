package com.projects.vimal.santvicharan;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.projects.vimal.santvicharan.Adapter.HaribhaktasAdapter;
import com.projects.vimal.santvicharan.Adapter.UpdatesAdapter;
import com.projects.vimal.santvicharan.data.DataConstants;
import com.projects.vimal.santvicharan.data.ProfileInfo;
import com.projects.vimal.santvicharan.data.Update;

import java.util.ArrayList;
import java.util.List;

public class HaribhaktaUpdate extends AppCompatActivity {

    private final String CLASS_NAME = this.getClass().getSimpleName();

    private FloatingActionButton addUpdate;
    private String systemId;
    private TextView firstLastName;
    private TextView center;

    private ListView updatesListView;

    private UpdatesAdapter updatesAdapter;

    private FirebaseDatabase database;
    private DatabaseReference vicharanUpdateTable;
    private DatabaseReference profileInfoTable;
    private Query matchingUpdates;

    /**
     * Executed on creation of the activity
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_haribhakta_update);

        //Get the Haribhakta's system ID
        getSystemId();

        //Initialize the DB and table references
        initializeDBReferences();

        //List view that will display the list of updates for the haribhakta
        updatesListView = findViewById(R.id.updatesListView);

        //Text View fields to hold the Haribhakta name and center
        firstLastName = findViewById(R.id.updateFirstLastName);
        center = findViewById(R.id.updateCenter);

        //Button to add a new update
        addUpdate = findViewById(R.id.addUpdate);

        //Initialize the adapter
        initializeAdapter();

        //Retrieve information from the DB
        performRetrieveFromDB();

        //Navigate to adding a new update for the Haribhakta when the button is clicked
        addUpdate.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                Intent intent = new Intent(HaribhaktaUpdate.this, AddUpdate.class);
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
     * VICHARAN_UPDATE_TABLE, PROFILE_INFO_TABLE
     */
    private void initializeDBReferences() {

        database = FirebaseDatabase.getInstance();
        vicharanUpdateTable = database.getReference().child(DataConstants.VICHARAN_UPDATE_TABLE);
        profileInfoTable = database.getReference().child(DataConstants.PROFILE_INFO_TABLE).child(systemId);

        //TODO - Possibly might need to change the order of the updates
        matchingUpdates = vicharanUpdateTable.orderByChild("systemId").equalTo(systemId);

        Log.d(CLASS_NAME, "Got DB instance and initialized the VICHARAN_UPDATE_TABLE and PROFILE_INFO_TABLE");
    }


    /**
     * Method for initializing the adapter that will be used to fill the ListView with the
     * updates for the haribhakta
     */
    private void initializeAdapter () {

        final List<Update> updates = new ArrayList<>();
        updatesAdapter = new UpdatesAdapter(this, R.layout.haribhakta_upate,
                updates);
        updatesListView.setAdapter(updatesAdapter);

        Log.d(CLASS_NAME, "Completed initializing the adapter that will fill the List View");
    }


    /**
     * Method to retrieve Haribhakta profile info and his updates from the DB
     */
    private void performRetrieveFromDB () {

        //Retrieve the Haribhakta's profile Info
        profileInfoTable.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                Log.i(CLASS_NAME, dataSnapshot.getKey());

                ProfileInfo profileInfo = dataSnapshot.getValue(ProfileInfo.class);
                firstLastName.setText(profileInfo.getFirstName() + " " + profileInfo.getLastName());
                center.setText(profileInfo.getCenter());

                Log.i(CLASS_NAME, "Setting name to: " + firstLastName.getText().toString());
                Log.i(CLASS_NAME, "Setting center to: " + center.getText().toString());
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        //Retrieve all of the Haribhakta's updates
        matchingUpdates.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                Log.i(CLASS_NAME, dataSnapshot.getKey());

                for (DataSnapshot next : dataSnapshot.getChildren()) {

                    Update update = next.getValue(Update.class);
                    updatesAdapter.add(update);

                    Log.i(CLASS_NAME, "Adding to List View: " + update.toString());
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}
