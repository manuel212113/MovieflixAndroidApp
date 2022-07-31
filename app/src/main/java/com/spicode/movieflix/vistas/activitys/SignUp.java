package com.spicode.movieflix.vistas.activitys;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.spicode.movieflix.R;

import java.util.HashMap;
import java.util.Map;

public class SignUp extends AppCompatActivity {

    TextView txtVolverLogin;

    private EditText txtUser;
    private EditText txtMail;
    private EditText txtPhone;
    private EditText txtPassword;
    private Button btnRegister;
    private TextView lblLogin;

    private String userID;
    private FirebaseAuth mAuth;
    private FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        txtVolverLogin= (TextView) findViewById(R.id.textView2);
        txtUser = findViewById(R.id.txtname);
        txtMail = findViewById(R.id.txtEmail);
        txtPhone = findViewById(R.id.txtPhone);
        txtPassword = findViewById(R.id.txtPass);
        btnRegister = findViewById(R.id.button);
        mAuth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();

        txtVolverLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SignUp.this, LoginActivity.class));
                finish();
            }
        });

        btnRegister.setOnClickListener(view -> {
            createuser();
        });


    }

    public void createuser() {

        String name = txtUser.getText().toString();
        String mail = txtMail.getText().toString();
        String phone = txtPhone.getText().toString();
        String password = txtPassword.getText().toString();

        if (TextUtils.isEmpty(name)) {
            txtMail.setError("Ingrese un Nombre");
            txtMail.requestFocus();
        } else if (TextUtils.isEmpty(mail)) {
            txtMail.setError("Ingrese un Correo");
            txtMail.requestFocus();
        } else if (TextUtils.isEmpty(phone)) {
            txtMail.setError("Ingrese un Teléfono");
            txtMail.requestFocus();
        } else if (TextUtils.isEmpty(password)) {
            txtMail.setError("Ingrese una Contraseña");
            txtMail.requestFocus();
        } else {

            mAuth.createUserWithEmailAndPassword(mail, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {
                        userID = mAuth.getCurrentUser().getUid();
                        DocumentReference documentReference = db.collection("users").document(userID);

                        Map<String, Object> user = new HashMap<>();
                        user.put("Nombre", name);
                        user.put("Correo", mail);
                        user.put("Teléfono", phone);
                        user.put("Contraseña", password);

                        documentReference.set(user).addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void unused) {
                                Log.d("TAG", "onSuccess: Datos registrados" + userID);
                            }
                        });
                        Toast.makeText(SignUp.this, "Usuario Registrado", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(SignUp.this, LoginActivity.class));
                    } else {
                        Toast.makeText(SignUp.this, "Usuario no registrado" + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                    }

                }
            });

        }
    }
}