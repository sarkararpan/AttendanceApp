package att2.com.wrc.aas;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

/**
 * The Landing activity after a teacher is logged in.
 */
public class TeacherActivity extends AppCompatActivity {

    private Button teacherIntermediateBtn;
    private Button generateReportBtn;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher);
        mAuth = FirebaseAuth.getInstance();
        teacherIntermediateBtn = findViewById(R.id.teacher_intermediate_btn);
        generateReportBtn = findViewById(R.id.generate_report_btn);
    }

    @Override
    protected void onStart() {
        super.onStart();
        if(mAuth.getCurrentUser() != null) {
            Toast.makeText(TeacherActivity.this, mAuth.getCurrentUser().getEmail(), Toast.LENGTH_SHORT).show();
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
    }
}
