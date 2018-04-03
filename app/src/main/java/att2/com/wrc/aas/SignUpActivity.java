package att2.com.wrc.aas;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.basgeekball.awesomevalidation.AwesomeValidation;
import com.basgeekball.awesomevalidation.ValidationStyle;
import com.basgeekball.awesomevalidation.utility.RegexTemplate;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.regex.Pattern;

/**
 * Activity for SignUp, Created by Aurghya, 30-03-2018
 * Handles the SignUp for teachers, on successful sign up
 * redirects to Teacher Activity
 */
public class SignUpActivity extends AppCompatActivity {
    private static final String TAG = "SignUpActivity";
    private FirebaseAuth mAuth;

    private EditText nameField;
    private EditText emailField;
    private EditText passwordField;
    private EditText confirmPasswordField;
    private EditText teacherIdfield;
    private AwesomeValidation awesomeValidation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        mAuth = FirebaseAuth.getInstance();

        awesomeValidation = new AwesomeValidation(ValidationStyle.COLORATION);
        teacherIdfield = findViewById(R.id.user_emp_id_field);
        nameField = findViewById(R.id.user_name_field);
        emailField = findViewById(R.id.user_email_field);
        passwordField = findViewById(R.id.user_password_field);
        confirmPasswordField = findViewById(R.id.user_confirm_password_field);

        Button submitBtn = findViewById(R.id.submit_sign_up);
        Button signUpToLoginBtn = findViewById(R.id.sign_up_to_login);

        awesomeValidation.addValidation(nameField, RegexTemplate.NOT_EMPTY, "Name should not be empty");
        awesomeValidation.addValidation(emailField, Patterns.EMAIL_ADDRESS, "Should be a valid email");
        awesomeValidation.addValidation(passwordField, RegexTemplate.NOT_EMPTY, "Password should not be empty");
        awesomeValidation.addValidation(confirmPasswordField, RegexTemplate.NOT_EMPTY, "Should not be empty");
        awesomeValidation.addValidation(teacherIdfield, RegexTemplate.NOT_EMPTY, "Teacher ID should not be empty");
        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(awesomeValidation.validate()) {
                    User user = new User(
                            nameField.getText().toString(),
                            emailField.getText().toString(),
                            "Not Available Yet",
                            teacherIdfield.getText().toString(),
                            false,
                            "teacher");

                    if(passwordField.getText().toString().equals(confirmPasswordField.getText().toString())) {
                        startSignUp(user, passwordField.getText().toString());
                    } else {
                        Toast.makeText(SignUpActivity.this,
                                "Password and Confirm Password should match",
                                Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        signUpToLoginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SignUpActivity.this, LoginActivity.class);
                //Clear the back stack when logging in
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
            }
        });
    }

    /**
     * SignUp method. Calls .createUserWithEmailAndPassword() from FirebaseAuth
     * @param user the User object, contains name, email, teacher ID, access level and verified status
     * @param password the password for the user email
     */
    private void startSignUp(final User user, String password) {
        mAuth.createUserWithEmailAndPassword(user.getEmail(), password).addOnCompleteListener(SignUpActivity.this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()) {
                    if(mAuth.getCurrentUser() != null) {
                        String uid = mAuth.getCurrentUser().getUid();
                        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("Users");

                        databaseReference.child(uid).setValue(user);

                        Intent intent = new Intent(SignUpActivity.this, TeacherActivity.class);
                        startActivity(intent);
                    } else {
                        Log.w(TAG, "createUserWithEmail:failure", task.getException());
                        Toast.makeText(SignUpActivity.this, "Account Creation Failed", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
}
