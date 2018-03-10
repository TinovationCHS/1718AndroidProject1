package com.example.anjanbharadwaj.androidapp;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SignupActivity extends AppCompatActivity {
    EditText email;
    EditText password;
    EditText name;
    EditText grade;
    EditText id;
    Button signup;
    FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        mAuth = FirebaseAuth.getInstance();
        email = (EditText)findViewById(R.id.email);
        password = (EditText)findViewById(R.id.password);
        name = (EditText)findViewById(R.id.name);
        id = (EditText)findViewById(R.id.id);
        grade = (EditText)findViewById(R.id.grade);
        signup = (Button)findViewById(R.id.signup);

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email1 = email.getText().toString();
                String password1 = password.getText().toString();
                final String name1 = name.getText().toString();
                final String id1 = id.getText().toString();
                final String grade1 = grade.getText().toString();
                if(!name1.isEmpty() && !id1.isEmpty() && !grade1.isEmpty()) {
                    mAuth.createUserWithEmailAndPassword(email1, password1)
                            .addOnCompleteListener(SignupActivity.this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {
                                        Toast.makeText(SignupActivity.this, "Signup successful.",
                                                Toast.LENGTH_SHORT).show();
                                        DatabaseReference ref = FirebaseDatabase.getInstance().getReference();
                                        ref.child("Users").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("Name").setValue(name1);
                                        ref.child("Users").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("ID").setValue(id1);
                                        ref.child("Users").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("Grade").setValue(grade1);
                                        startActivity(new Intent(getApplicationContext(),HomeScreen.class));
                                        //startActivity(new Intent(getApplicationContext(), ClassDetailActivity.class));
                                        // Sign in success, update UI with the signed-in user's information
                                    } else {
                                        // If sign in fails, display a message to the user.
                                        Toast.makeText(SignupActivity.this, "Signup failed.",
                                                Toast.LENGTH_SHORT).show();
                                    }

                                }
                            });
                }

            }
        });
    }
}
