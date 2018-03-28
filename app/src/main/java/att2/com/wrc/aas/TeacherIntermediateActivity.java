package att2.com.wrc.aas;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class TeacherIntermediateActivity extends AppCompatActivity {

    EditText periodField;
    EditText dateField;
    EditText classField;
    Button submitBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_intermediate);

        periodField = findViewById(R.id.teacher_period_field);
        classField = findViewById(R.id.teacher_class_field);
        dateField = findViewById(R.id.teacher_date_field);
        submitBtn = findViewById(R.id.submit_teacher_intermediate);
    }

    @Override
    protected void onStart() {
        super.onStart();

        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TeacherIntermediateActivity.this, AttendanceActivity.class);
                intent.putExtra("period", periodField.getText().toString());
                intent.putExtra("date", dateField.getText().toString());
                intent.putExtra("class", classField.getText().toString());
                startActivity(intent);
            }
        });

    }
}
