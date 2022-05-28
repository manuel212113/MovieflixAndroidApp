package com.spicode.movieflix.vistas.activitys;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.spicode.movieflix.vistas.fragments.CustomLoading;
import com.spicode.movieflix.R;

import io.github.muddz.styleabletoast.StyleableToast;


public class LoginActivity extends AppCompatActivity {


    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener firebaseAuthListener;
    Button loginBtn;
    EditText correo,contraseña;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        /* Apple Loading es un progressbar customizado :D*/
        CustomLoading dialog= new CustomLoading(LoginActivity.this);
        mAuth = FirebaseAuth.getInstance();
        correo = (EditText) findViewById(R.id.txtUser);
        contraseña = (EditText) findViewById(R.id.txtPass);
        loginBtn = (Button) findViewById(R.id.button);



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
                                    StyleableToast.makeText(LoginActivity.this, "Bienvenido!", Toast.LENGTH_SHORT, R.style.mytoast).show();
                                    try{
                                        startActivity(new Intent(LoginActivity.this, MainActivity.class));
                                        finish();
                                    }catch (Exception ex){
                                        Toast.makeText(LoginActivity.this,  ex+" Error", Toast.LENGTH_SHORT).show();

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