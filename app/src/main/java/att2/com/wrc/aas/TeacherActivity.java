package att2.com.wrc.aas;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

/**
 * The Landing activity after a teacher is logged in.
 */
public class TeacherActivity extends AppCompatActivity {

    Button teacherIntermediateBtn;
    Button generateReportBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher);
        teacherIntermediateBtn = findViewById(R.id.teacher_intermediate_btn);
        generateReportBtn = findViewById(R.id.generate_report_btn);
    }

    @Override
    protected void onStart() {
        super.onStart();

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
