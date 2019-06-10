package com.example.firstaid;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Button buttonRegister;
    private EditText editTextEmail;
    private EditText editTextPassword;
   // private EditText editTextPhone;
   // private EditText editTextName;
    private TextView textViewSignin;
   // private Spinner  blood_group,rhesus,gender;
    private ProgressDialog progressDialog;

    private FirebaseAuth firebaseAuth;
    //private DatabaseReference mDatabase;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

       firebaseAuth = FirebaseAuth.getInstance();
       // mDatabase = FirebaseDatabase.getInstance().getReference();

       /* if(firebaseAuth.getCurrentUser()!= null){
            startActivity(new Intent(this,ActivityRecycler.class));
        }
*/
        progressDialog = new ProgressDialog(this);
        buttonRegister = (Button) findViewById(R.id.buttonRegister);
        editTextEmail = (EditText) findViewById(R.id.editTextEmail);
        //editTextPhone = (EditText) findViewById(R.id.editTextPhone);
        //editTextName = (EditText) findViewById(R.id.editTextName);
        //blood_group = (Spinner) findViewById(R.id.blood_group_spinner);
        //rhesus = (Spinner) findViewById(R.id.rhesus);
        //gender = (Spinner) findViewById(R.id.gender);
        editTextPassword = (EditText) findViewById(R.id.editTexPassword);
        textViewSignin = (TextView) findViewById(R.id.textViewSignIn);

        /*ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,R.array.blood_group,android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        blood_group.setAdapter(adapter);
        blood_group.setOnItemSelectedListener((AdapterView.OnItemSelectedListener) this);*/

        buttonRegister.setOnClickListener(this);
        textViewSignin.setOnClickListener(this);
       // addListenerOnSpinnerItemSelection();

    }
   /* public void addListenerOnSpinnerItemSelection(){
   blood_group.setOnItemClickListener((AdapterView.OnItemClickListener) new CustomOnItemSelectedListener());
    }*/


    @Override
    protected void onStart() {
        super.onStart();
        if(firebaseAuth.getCurrentUser()!= null){
            //Handle the already logged in user
        }
    }

    private void registerUser(){

        //final String name = editTextName.getText().toString().trim();
       // final String phone =editTextPhone.getText().toString().trim();
       final String email =editTextEmail.getText().toString().trim();
        //final String bloodGroup = blood_group.toString().trim();
        String password =editTextPassword.getText().toString().trim();


        /*if(TextUtils.isEmpty(name)){
            //name is empty
            editTextName.setError("Please enter your name");
            //stopping execution
            editTextName.requestFocus();

        }
        if (TextUtils.isEmpty(phone)) {
            //phone is empty
            editTextPhone.setError("Phone number is required");
            //Stopping the function from executing further
            editTextPhone.requestFocus();
            return;
        }
        if(!Patterns.PHONE.matcher(phone).matches()){
            editTextPhone.setError("Enter a valid phone number");
            editTextPhone.requestFocus();


        }*/

        if (TextUtils.isEmpty(email)){
            //email is empty
            Toast.makeText(getApplicationContext(),"field required",Toast.LENGTH_SHORT).show();
            //editTextEmail.setError("Email is required");
          //Stopping the function from executing further
           // editTextEmail.requestFocus();
            return;
        }

        /*if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            editTextEmail.setError("Please enter a valid email");
            editTextEmail.requestFocus();

        }*/

         if (TextUtils.isEmpty(password)){
             //password is empty
             Toast.makeText(getApplicationContext(),"password is required",Toast.LENGTH_SHORT).show();
             //editTextPassword.setError("Password is required");
             //stops the function from executing further
             //editTextPassword.requestFocus();
             return;
         }
        /* if(password.length()>6){
            editTextPassword.setError("Minimum length of password should be 6");
            editTextPassword.requestFocus();

         }*/
         //If validations are okay
        //we will start with showing the progress bar
        progressDialog.setMessage("Registering User...");
         progressDialog.show();

         firebaseAuth.createUserWithEmailAndPassword(email,password)
          .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
              @Override
              public void onComplete(@NonNull Task<AuthResult> task) {
                  if (task.isSuccessful()){
                      finish();
                      //startActivity(new Intent(getApplicationContext(),ActivityRecycler.class));
                       //we will store the additional fields in firebase database
                     /* User user = new User(name,email,phone,bloodGroup);
                      FirebaseDatabase.getInstance().getReference("Users")
                        .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                         .setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                          @Override
                          public void onComplete(@NonNull Task<Void> task) {
                              if (task.isSuccessful()){
                                  Toast.makeText(getApplicationContext(),"User Registered Successfully",Toast.LENGTH_SHORT).show();
                              }
                          }
                      });*/
                      //user is successfully registered and logged in we will start the accidents accitivity here
                      //lets  display a toast
                      Toast.makeText(getApplicationContext(),"User Registered Successfully",Toast.LENGTH_SHORT).show();
                  }
                  else {
                      /*task.getException();
                      Toast.makeText(getApplicationContext(),task.getException().getMessage(),Toast.LENGTH_SHORT).show();
                      */
                      Toast.makeText(getApplicationContext(),"registration failed",Toast.LENGTH_SHORT).show();
                  }
              }
          });
    }

    @Override
    public void onClick(View v) {
        if (v == buttonRegister){

            registerUser();
        }
        if (v== textViewSignin){
            //will open login activity here
            startActivity(new Intent(this,LoginActivity.class));
        }
    }


}
