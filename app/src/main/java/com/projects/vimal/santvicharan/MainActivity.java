package com.projects.vimal.santvicharan;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.firebase.ui.auth.AuthUI;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.Arrays;
import java.util.List;

import static com.firebase.ui.auth.AuthUI.*;

public class MainActivity extends AppCompatActivity {

    public static final String EXTRA_MESSAGE = "com.projects.vimal.santvicharan.MESSAGE";

    private static final String NOT_AUTHENTICATED_HRBKT = "Unknown - Not signed in";

    private final String CLASS_NAME = this.getClass().getSimpleName();

    public static final int RC_SIGN_IN = 1;

    private String haribhaktaName;

    private FirebaseAuth firebaseAuth;
    private FirebaseAuth.AuthStateListener authStateListener;

    /**
     * Executed on creation of the acitivity
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Use Google Firebase to perform app authentication
        firebaseAuth = FirebaseAuth.getInstance();

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
            }
            //When the user opts to cancel signing in
            else if (resultCode == RESULT_CANCELED) {
                Toast.makeText(this, "Sign in canceled", Toast.LENGTH_SHORT).show();
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
     * Method to navigate to the Display Message activity
     * after the user enters in the message and hits the send button
     * @param view
     */
    public void sendMessage (View view) {

        Intent intent = new Intent(this, DisplayMessageActivity.class);
        EditText editText = findViewById(R.id.editText);
        String message = editText.getText().toString();
        intent.putExtra(EXTRA_MESSAGE, message);
        startActivity(intent);
    }
}
