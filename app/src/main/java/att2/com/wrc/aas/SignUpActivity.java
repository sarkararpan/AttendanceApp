package att2.com.wrc.aas;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SignUpActivity extends AppCompatActivity {
    private static final String TAG = "SignUpActivity";
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        mAuth = FirebaseAuth.getInstance();
    }

    private void startSignUp(final User user, String password) {
        mAuth.createUserWithEmailAndPassword(user.getEmail(), password).addOnCompleteListener(SignUpActivity.this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()) {
                    if(mAuth.getCurrentUser() != null) {
                        String uid = mAuth.getCurrentUser().getUid();
                        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("Users");

                        databaseReference.child(uid).setValue(user);
                    } else {
                        Log.w(TAG, "createUserWithEmail:failure", task.getException());
                        Toast.makeText(SignUpActivity.this, "Account Creation Failed", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
}
