package att2.com.wrc.aas;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

/**
 * The Landing activity after a teacher is logged in.
 */
public class TeacherActivity extends AppCompatActivity {

    private TextView userNameView;
    private TextView userEmailView;
    private TextView userLastClassView;
    private TextView userVerifiedView;
    private TextView userAccessLevelView;

    private Button addStudentBtn;
    private Button teacherIntermediateBtn;
    private Button generateReportBtn;
    private Button signOutBtn;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher);
        mAuth = FirebaseAuth.getInstance();
        addStudentBtn= findViewById(R.id.add_student_btn);
        userNameView = findViewById(R.id.user_name_view);
        userEmailView = findViewById(R.id.user_email_view);
        userLastClassView = findViewById(R.id.last_class_view);
        userVerifiedView = findViewById(R.id.user_verification_status_view);
        userAccessLevelView = findViewById(R.id.access_level_view);

        teacherIntermediateBtn = findViewById(R.id.teacher_intermediate_btn);
        generateReportBtn = findViewById(R.id.generate_report_btn);
        signOutBtn = findViewById(R.id.sign_out_btn);
    }

    @Override
    protected void onStart() {
        super.onStart();
        if(mAuth.getCurrentUser() != null) {
            final String uid = mAuth.getCurrentUser().getUid();

            DatabaseReference userRef = FirebaseDatabase.getInstance().getReference("Users");
            userRef.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    if(dataSnapshot.hasChild(uid)) {
                        User user = dataSnapshot.child(uid).getValue(User.class);

                        if(user != null) {
                            userNameView.setText(user.getName());
                            userEmailView.setText(user.getEmail());
                            userLastClassView.setText(
                                    String.format(getResources().getString(R.string.last_class_hint),
                                    user.getLastClass()));
                            userAccessLevelView.setText(
                                    String.format(getResources().getString(R.string.access_level_hint),
                                    user.getAccessLevel()));
                            if(user.isVerified()) {
                                userVerifiedView.setText(getResources().getString(R.string.verified_text));
                                userVerifiedView.setVisibility(View.VISIBLE);
                            }
                            else
                            {
                                teacherIntermediateBtn.setEnabled(false);
                            }
                            findViewById(R.id.teacher_progress_bar).setVisibility(View.GONE);
                        }
                    }
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {
                    Toast.makeText(TeacherActivity.this, databaseError.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });


        }
        teacherIntermediateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(TeacherActivity.this, TeacherIntermediateActivity.class));
            }
        });

        generateReportBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(TeacherActivity.this, ReportActivity.class));
            }
        });

        signOutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAuth.signOut();
                Intent intent = new Intent(TeacherActivity.this, MainActivity.class);
                //Clear the back stack when logging in
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
            }
        });
        addStudentBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(TeacherActivity.this, AddStudentActivity.class));
            }
        });
    }
}
