package att2.com.wrc.aas;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class StudentActivity extends AppCompatActivity {
    private TextView studentNameView;
    private TextView studentIdView;
    private TextView countView;
    private TextView cidView;
    private TextView semView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student);

        studentNameView = findViewById(R.id.student_name_view);
        studentIdView = findViewById(R.id.student_id_view);
        countView = findViewById(R.id.student_total_attendance_view);
        cidView = findViewById(R.id.student_college_id_view);
        semView = findViewById(R.id.student_semester_view);


    }

    @Override
    protected void onStart() {
        super.onStart();
        final String studentId = getIntent().getStringExtra("studentId");
        DatabaseReference studentRef = FirebaseDatabase.getInstance().getReference("Students");
        DatabaseReference attendRef = FirebaseDatabase.getInstance().getReference("Attendance");
        studentRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.hasChild(studentId)) {
                    Student student = dataSnapshot.child(studentId).getValue(Student.class);
                    if (student != null) {
                        String s;
                        studentNameView.setText(student.getName());
                        studentIdView.setText(studentId);
                        cidView.setText(String.valueOf(student.getCid()));
                        s = "Semester : " + String.valueOf(student.getSemester());
                        semView.setText(s);
                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        attendRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.hasChild(studentId)) {
                    if (dataSnapshot.child(studentId).hasChild("count")) {
                        countView.setText(String.valueOf((long) dataSnapshot.child(studentId).child("count").getValue()));
                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}
