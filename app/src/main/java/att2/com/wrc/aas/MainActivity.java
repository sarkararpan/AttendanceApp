package att2.com.wrc.aas;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.basgeekball.awesomevalidation.AwesomeValidation;
import com.basgeekball.awesomevalidation.ValidationStyle;
import com.basgeekball.awesomevalidation.utility.RegexTemplate;
import com.google.firebase.auth.FirebaseAuth;

/**
* MainActivity, Edited by Aurghya, 31-03-2018
* This Activity will not be there once the user is logged in,
* if the user is logged in, then an intent to TeacherActivity
* will be started with finish() so that this activity can't be
* accessed via the back stack.
**/
public class MainActivity extends AppCompatActivity {
    //TODO : Fix code issues, some minor issues.

    private TextView teacherViewLink;
    private Button studentDetailsBtn;
    private EditText studentIdField;

    AwesomeValidation awesomeValidation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        if(mAuth.getCurrentUser() != null) {
            Intent intent = new Intent(MainActivity.this, TeacherActivity.class);
            //Clear the back stack when logging in
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
        }

        teacherViewLink = findViewById(R.id.teacher_view_link);
        studentDetailsBtn = findViewById(R.id.student_details_btn);
        studentIdField = findViewById(R.id.student_id_field);

        awesomeValidation = new AwesomeValidation(ValidationStyle.COLORATION);

        awesomeValidation.addValidation(studentIdField, RegexTemplate.NOT_EMPTY, "Student ID can't be empty");
    }

    @Override
    protected void onStart() {
        super.onStart();

        studentDetailsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Validating the data that is entered by the student
                if(awesomeValidation.validate()) {
                    Intent intent = new Intent(MainActivity.this, StudentActivity.class);
                    intent.putExtra("studentId", studentIdField.getText().toString());
                    startActivity(intent);
                }
            }
        });

        // Link to the LoginActivity
        teacherViewLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, LoginActivity.class));
            }
        });
    }
}
