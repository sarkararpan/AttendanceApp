package att2.com.wrc.aas;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

/**
 * DatabaseActivity, Edited by Aurghya, 01-04-2018
 * This activity is almost same as the Attendance Activity
 * the only main difference being the view is not a card view
 * and no checkboxes are there
 * */
public class ReportActivity extends AppCompatActivity {
    //TODO: Fix code issues, optimize database connection

    DatabaseReference studentRef;
    FirebaseRecyclerOptions<Student> options;
    RecyclerView reportView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report);

        reportView = findViewById(R.id.report_attendance_view);
        studentRef = FirebaseDatabase.getInstance().getReference("Students");

        // RecyclerView with LinearLayout Manager is same as list view.
        // Where the items are appended at the bottom of previous item
        // based on the orientation of the device or the application.
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(this, layoutManager.getOrientation());
        reportView.addItemDecoration(dividerItemDecoration);
        reportView.setLayoutManager(layoutManager);

        Query query = studentRef;

        options = new FirebaseRecyclerOptions.Builder<Student>()
                .setQuery(query, Student.class).build();
    }

    @Override
    protected void onStart() {
        super.onStart();

        FirebaseRecyclerAdapter<Student, ReportHolder> adapter = new FirebaseRecyclerAdapter<Student, ReportHolder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull final ReportHolder holder, int position, @NonNull Student model) {
                final String studentIdKey = getRef(position).getKey();

                holder.setNameView(model.getName());
                holder.setSidView(studentIdKey);

                Query reference = FirebaseDatabase.getInstance().getReference("Attendance");

                // Use normal value event listener here,
                // otherwise updated count will not properly show.
                reference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        if (dataSnapshot.hasChild(studentIdKey)) {
                            if (dataSnapshot.child(studentIdKey).hasChild("count")) {
                                holder.setCountView((long) dataSnapshot.child(studentIdKey)
                                        .child("count").getValue());
                            } else {
                                holder.setCountView(0);
                            }
                        } else {
                            holder.setCountView(0);
                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        Toast.makeText(ReportActivity.this, databaseError.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
            }

            @Override
            public ReportHolder onCreateViewHolder(ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.student_report_item, parent, false);
                return new ReportHolder(view);
            }
        };
        reportView.setAdapter(adapter);
        adapter.startListening();
    }
}
