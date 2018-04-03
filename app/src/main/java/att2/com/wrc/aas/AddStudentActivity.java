package att2.com.wrc.aas;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.basgeekball.awesomevalidation.AwesomeValidation;
import com.basgeekball.awesomevalidation.ValidationStyle;
import com.basgeekball.awesomevalidation.utility.RegexTemplate;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AddStudentActivity extends AppCompatActivity {
    AwesomeValidation awesomeValidation;
    DatabaseReference reference;

    private EditText nameField;
    private EditText idField;
    private EditText cidField;
    private EditText semField;
    private Button addstudentbtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_student);

        awesomeValidation = new AwesomeValidation(ValidationStyle.COLORATION);
        reference = FirebaseDatabase.getInstance().getReference("Students");

        nameField = findViewById(R.id.student_name_field);
        idField = findViewById(R.id.student_id_field);
        cidField = findViewById(R.id.college_id_field);
        semField = findViewById(R.id.semester_field);
        Button addstudentbtn = findViewById(R.id.add_student_submit_btn);

        awesomeValidation.addValidation(nameField, RegexTemplate.NOT_EMPTY, "Name should not be empty");
        awesomeValidation.addValidation(idField, RegexTemplate.NOT_EMPTY, "Should be a valid Id");
        awesomeValidation.addValidation(cidField, RegexTemplate.NOT_EMPTY, "College Id should not be empty");
        awesomeValidation.addValidation(semField, RegexTemplate.NOT_EMPTY, "Semester should not be empty");

        addstudentbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(awesomeValidation.validate())
                {
                    Student student = new Student(nameField.getText().toString(),Long.valueOf(semField.getText().toString()),cidField.getText().toString());
                    reference.child(idField.getText().toString()).setValue(student);
                }
            }
        });

    }
}
