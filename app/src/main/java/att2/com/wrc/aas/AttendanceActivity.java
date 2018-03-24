package att2.com.wrc.aas;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

public class AttendanceActivity extends AppCompatActivity {

    DatabaseReference studentRef;
    FirebaseRecyclerOptions<Student> options

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attendance);

        studentRef = FirebaseDatabase.getInstance().getReference("Students");

        Query query = studentRef;

        options = new FirebaseRecyclerOptions.Builder<Student>()
                .setQuery(query, Student.class).build();
    }

    @Override
    protected void onStart() {
        super.onStart();

        FirebaseRecyclerAdapter<Student, StudentHolder> adapter = new FirebaseRecyclerAdapter<Student, StudentHolder>() {
            @Override
            protected void onBindViewHolder(@NonNull StudentHolder holder, int position, @NonNull Student model) {

            }

            @Override
            public StudentHolder onCreateViewHolder(ViewGroup parent, int viewType) {
                return null;
            }
        };
    }
}
