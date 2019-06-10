package com.example.firstaid;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.app.LoaderManager.LoaderCallbacks;

import android.content.CursorLoader;
import android.content.Loader;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;

import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.text.TextUtils;
import android.util.Patterns;import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.EditorInfo;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;
import java.util.List;

import static android.Manifest.permission.READ_CONTACTS;

//A login screen that offers login via email/password.

public class LoginActivity extends AppCompatActivity implements View.OnClickListener  {

    private Button buttonSignIn;
    private EditText editTextEmail;
    private EditText editTextPassword;
    private TextView textViewSignUp;
    private ProgressDialog progressDialog;

    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        firebaseAuth = FirebaseAuth.getInstance();
       /* if(firebaseAuth.getCurrentUser()!= null){
            // redirecting to Activityrecycler.
            startActivity(new Intent(this,ActivityRecycler.class));
        }
      */

        progressDialog = new ProgressDialog(this);
        buttonSignIn =  findViewById(R.id.buttonSignIn);
        editTextEmail =  findViewById(R.id.editTextEmail);
        editTextPassword =  findViewById(R.id.editTexPassword);
        textViewSignUp =  findViewById(R.id.textViewSignUp);

        buttonSignIn.setOnClickListener(this);
        textViewSignUp.setOnClickListener(this);

    }
    private void userLogin(){

        String email = editTextEmail.getText().toString().trim();
        String password =editTextPassword.getText().toString().trim();

        if (TextUtils.isEmpty(email)){
            //email is empty
            Toast.makeText(getApplicationContext(),"email required",Toast.LENGTH_SHORT).show();
           // editTextEmail.setError("Email is required");
            //Stopping the function from executing further
            //editTextEmail.requestFocus();
            return;
        }

        /*if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            editTextEmail.setError("Please enter a valid email");
            editTextEmail.requestFocus();

        }*/

        if (TextUtils.isEmpty(password)){
            //password is empty
            Toast.makeText(getApplicationContext(),"email is required",Toast.LENGTH_SHORT).show();
            //editTextPassword.setError("Password is required");
            //stops the function from executing further
            //editTextPassword.requestFocus();
            return;
        }
        //If validations are okay
        //we will start with showing the progress bar
        progressDialog.setMessage("Signing in,please wait...");
        progressDialog.show();

        firebaseAuth.signInWithEmailAndPassword(email,password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        progressDialog.dismiss();
                        if (task.isSuccessful()){
                            //Start the recyclerview activity
                            finish();
                            //startActivity(new Intent(getApplicationContext(),ActivityRecycler.class));
                            Toast.makeText(getApplicationContext(),"Login Successful",Toast.LENGTH_SHORT).show();

                        }
                        else
                        {
                            Toast.makeText(getApplicationContext(),"login failed!",Toast.LENGTH_SHORT).show();

                        }
                    }
                });
    }

    @Override
    public void onClick(View v) {
        if (v == buttonSignIn){

            userLogin();
        }
        if (v== textViewSignUp){
            finish();
            //will open register activity here
            startActivity(new Intent(this,MainActivity.class));
        }
    }

}

