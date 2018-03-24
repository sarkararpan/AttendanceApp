package att2.com.wrc.aas;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class TeacherIntermidiateActivity extends AppCompatActivity {

    Button addAttendenceBtn;
    Button viewAttendenceBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_intermidiate);

        addAttendenceBtn = findViewById(R.id.add_attendence_btn);
        viewAttendenceBtn = findViewById(R.id.view_attendence_btn);

    }

    @Override
    protected void onStart() {
        super.onStart();

        addAttendenceBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(TeacherIntermidiateActivity.this, TeacherActivity.class));
            }
        });

        viewAttendenceBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(TeacherIntermidiateActivity.this, TeacherActivity.class));
            }
        });
    }
}
