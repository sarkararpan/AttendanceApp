package att2.com.wrc.aas;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

import com.basgeekball.awesomevalidation.AwesomeValidation;
import com.basgeekball.awesomevalidation.ValidationStyle;
import com.basgeekball.awesomevalidation.utility.RegexTemplate;

import java.util.Calendar;

public class TeacherIntermediateActivity extends AppCompatActivity {

    EditText periodField;
    EditText dateField;
    EditText classField;
    Button submitBtn;

    private AwesomeValidation awesomeValidation;

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

        awesomeValidation = new AwesomeValidation(ValidationStyle.COLORATION);

        awesomeValidation.addValidation(periodField, RegexTemplate.NOT_EMPTY, "Period should not be empty");
        awesomeValidation.addValidation(dateField, RegexTemplate.NOT_EMPTY, "Date should not be empty");
        awesomeValidation.addValidation(classField, RegexTemplate.NOT_EMPTY, "Class should not be empty");
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
                if(awesomeValidation.validate()) {
                    Intent intent = new Intent(TeacherIntermediateActivity.this, AttendanceActivity.class);
                    intent.putExtra("period", periodField.getText().toString().toUpperCase());
                    intent.putExtra("date", dateField.getText().toString());
                    intent.putExtra("class", classField.getText().toString().toUpperCase());
                    startActivity(intent);
                }
            }
        });

    }
}
