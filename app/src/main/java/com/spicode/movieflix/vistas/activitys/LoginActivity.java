package com.spicode.movieflix.vistas.activitys;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.spicode.movieflix.vistas.fragments.CustomLoading;
import com.spicode.movieflix.R;

import io.github.muddz.styleabletoast.StyleableToast;


public class LoginActivity extends AppCompatActivity {


    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener firebaseAuthListener;
    Button loginBtn;
    EditText correo,contraseña;
    TextView txtCrearCuenta;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        /* Custom Loading es un progressbar customizado :D*/
        CustomLoading dialog= new CustomLoading(LoginActivity.this);
        mAuth = FirebaseAuth.getInstance();
        correo = (EditText) findViewById(R.id.txtUser);
        contraseña = (EditText) findViewById(R.id.txtPass);
        loginBtn = (Button) findViewById(R.id.button);
        txtCrearCuenta= (TextView) findViewById(R.id.txtCreateAccount);


        txtCrearCuenta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this, SignUp.class));
                finish();
            }
        });

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    dialog.show();
                    String email = correo.getText().toString();
                    String Pass= contraseña.getText().toString();
                    if(correo.length()>=1 && Pass.length()>=1){
                        mAuth.signInWithEmailAndPassword(email,Pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()){
                                    dialog.hide();
                                    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                                    if (user != null) {
                                        // Name, email address, and profile photo Url

                                        // Check if user's email is verified
                                        boolean emailVerified = user.isEmailVerified();

                                        // The user's ID, unique to the Firebase project. Do NOT use this value to
                                        // authenticate with your backend server, if you have one. Use
                                        // FirebaseUser.getIdToken() instead.
                                        String uid = user.getUid();
                                        FirebaseFirestore db;
                                        db= FirebaseFirestore.getInstance();

                                        DocumentReference docRef = db.collection("users").document(uid);
                                        docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                                            @Override
                                            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                                                if (task.isSuccessful()) {
                                                    DocumentSnapshot document = task.getResult();
                                                    if (document.exists()) {
                                                       String name=task.getResult().getString("Nombre");
                                                        StyleableToast.makeText(LoginActivity.this, "Bienvenido! "+name, Toast.LENGTH_LONG, R.style.ToastBievenida).show();
                                                        try{
                                                            startActivity(new Intent(LoginActivity.this, MainActivity.class));
                                                            finish();
                                                        }catch (Exception ex){
                                                            Toast.makeText(LoginActivity.this,  ex+" Error", Toast.LENGTH_SHORT).show();

                                                        }
                                                    } else {
                                                        StyleableToast.makeText(LoginActivity.this, "Bienvenido! ", Toast.LENGTH_LONG, R.style.ToastBievenida).show();

                                                        try{
                                                            startActivity(new Intent(LoginActivity.this, MainActivity.class));
                                                            finish();
                                                        }catch (Exception ex){
                                                            Toast.makeText(LoginActivity.this,  ex+" Error", Toast.LENGTH_SHORT).show();

                                                        }
                                                    }
                                                } else {
                                                    Log.d(TAG, "get failed with ", task.getException());
                                                }
                                            }
                                        });

                                    }


                                }else{
                                    dialog.hide();
                                    Toast.makeText(LoginActivity.this, "Error", Toast.LENGTH_SHORT).show();

                                }
                            }
                        });
                    }else{
                        dialog.hide();
                        Toast.makeText(LoginActivity.this, "Debes rellenar todos los campos vacios", Toast.LENGTH_LONG).show();

                    }

                }catch (Exception e){
                    dialog.hide();
                    Toast.makeText(LoginActivity.this, e+ "  Error", Toast.LENGTH_SHORT).show();

                }



            }
        });





    }
}