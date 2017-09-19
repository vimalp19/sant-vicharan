package com.projects.vimal.santvicharan;

import android.content.Intent;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.firebase.ui.auth.AuthUI;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.projects.vimal.santvicharan.Adapter.HaribhaktasAdapter;
import com.projects.vimal.santvicharan.data.DataConstants;
import com.projects.vimal.santvicharan.data.HomeAddress;
import com.projects.vimal.santvicharan.data.ProfileInfo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import static com.firebase.ui.auth.AuthUI.*;

public class MainActivity extends AppCompatActivity {

    private static final String NOT_AUTHENTICATED_HRBKT = "Unknown - Not signed in";

    private final String CLASS_NAME = this.getClass().getSimpleName();

    public static final int RC_SIGN_IN = 1;

    private String haribhaktaName;

    private ListView profileInfoListView;
    private FloatingActionButton addHaribhakta;

    private HaribhaktasAdapter profileInfoAdapter;

    private FirebaseAuth firebaseAuth;
    private FirebaseAuth.AuthStateListener authStateListener;

    private FirebaseDatabase database;
    private DatabaseReference profileInfoTable;


    /**
     * Executed on creation of the acitivity
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Use Google Firebase to perform app authentication and database work
        firebaseAuth = FirebaseAuth.getInstance();

        //Initialize the DB and table references
        initializeDBReferences();

        //List view that will display the list of haribhaktos' profile info
        profileInfoListView = findViewById(R.id.haribhaktasListView);

        //Button to add a new haribhakta
        addHaribhakta = findViewById(R.id.addHaribhakta);

        addHaribhakta.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, AddHaribhakta.class));
            }
        });

        //Initialize the adapter
        initializeAdapter();

        //Retrieve list of profile infos from the DB
        performRetrieveFromDB();


        //Call auth state listener to determine the authentication status
        authStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {

                FirebaseUser haribhakta = firebaseAuth.getCurrentUser();

                //When the authentication is successful and user has signed in
                if (haribhakta != null) {

                    //Get the user's name
                    haribhaktaName = haribhakta.getDisplayName();
                    Log.i(CLASS_NAME, haribhaktaName + " has signed in successfully");
                }
                //Otherwise when authentication is not done
                else {

                    //Set the user's name back to unknown
                    haribhaktaName = NOT_AUTHENTICATED_HRBKT;

                    Log.i(CLASS_NAME, "Not signed in, redirecting to sign in page");

                    //For now, only allow sign in through Google account
                    List<IdpConfig> providers = Arrays.asList(
                            new AuthUI.IdpConfig.Builder(AuthUI.EMAIL_PROVIDER).build(),
                            new IdpConfig.Builder(GOOGLE_PROVIDER).build()
                    );

                    //Display the login options for authenticating
                    startActivityForResult(
                            AuthUI.getInstance()
                                .createSignInIntentBuilder()
                                .setIsSmartLockEnabled(false)
                                .setAvailableProviders(providers)
                                .build(),
                            RC_SIGN_IN
                    );
                }
            }
        };
    }


    /**
     *
     * @param requestCode
     * @param resultCode
     * @param data
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        //Proceed here when the activity change is user signing in
        if (requestCode == RC_SIGN_IN) {

            //When the user completes signing in
            if (requestCode == RESULT_OK) {
                Toast.makeText(this, "Signed in!", Toast.LENGTH_SHORT).show();
                Log.d(CLASS_NAME, "User has signed in successfully");
            }
            //When the user opts to cancel signing in
            else if (resultCode == RESULT_CANCELED) {
                Toast.makeText(this, "Sign in canceled", Toast.LENGTH_SHORT).show();
                Log.d(CLASS_NAME, "User has cancelled from signing in");
            }
        }
    }


    /**
     * Perform when the app transitions back to resume state
     */
    @Override
    protected void onResume() {
        super.onResume();

        Log.d(CLASS_NAME, "App transitioning to resume state");
        firebaseAuth.addAuthStateListener(authStateListener);
    }


    /**
     * perform when the app transitions to pause state
     */
    @Override
    protected void onPause() {
        super.onPause();

        Log.d(CLASS_NAME, "App transitioning to pause state");

        if (authStateListener == null) {
            Log.d(CLASS_NAME, "Removing Firebase auth state listener");
            firebaseAuth.removeAuthStateListener(authStateListener);
        }
    }


    /**
     * Method for containing the steps for initializing the DB
     * and the tables needed in this activity:
     * PROFILE_INFO_TABLE
     */
    private void initializeDBReferences() {

        database = FirebaseDatabase.getInstance();
        profileInfoTable = database.getReference().child(DataConstants.PROFILE_INFO_TABLE);

        Log.d(CLASS_NAME, "Got DB instance and initialized the PROFILE_INFO_TABLE");
    }


    /**
     * Method for initializing the adapter that will be used to fill the ListView with the
     * profile info for the haribhaktos
     */
    private void initializeAdapter () {

        final List<ProfileInfo> profileInfos = new ArrayList<>();
        profileInfoAdapter = new HaribhaktasAdapter(this, R.layout.haribhakta_profile_info,
                profileInfos);
        profileInfoListView.setAdapter(profileInfoAdapter);

        Log.d(CLASS_NAME, "Completed initializing the adapter that will fill the List View");
    }


    /**
     * Method to retrieve the list of haribhaktos' profile info from the DB
     */
    private void performRetrieveFromDB () {

        profileInfoTable.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                Log.i(CLASS_NAME, dataSnapshot.getKey());

                for (DataSnapshot next : dataSnapshot.getChildren()) {

                    ProfileInfo profile = next.getValue(ProfileInfo.class);
                    profileInfoAdapter.add(profile);

                    Log.i(CLASS_NAME, "Adding to List View: " + profile.toString());
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}
