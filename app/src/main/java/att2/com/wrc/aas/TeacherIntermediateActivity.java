package att2.com.wrc.aas;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

import java.util.Calendar;

public class TeacherIntermediateActivity extends AppCompatActivity {

    private static final String TAG = "TeacherIntermediate";

    EditText periodField;
    EditText dateField;
    EditText classField;
    Button submitBtn;

    private DatePickerDialog.OnDateSetListener onDateSetListener;
    String date;

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

        dateField.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(
                        TeacherIntermediateActivity.this,
                        android.R.style.Theme_Material_Dialog_MinWidth,
                        onDateSetListener,
                        year, month, day);
                dialog.show();
            }
        });

        onDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                month = month + 1;
                date = dayOfMonth + "-" + month + "-" + year;
                dateField.setText(date);
            }
        };

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
