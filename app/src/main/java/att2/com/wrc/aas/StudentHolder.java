package att2.com.wrc.aas;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

/**
 * Created by Aurghya on 24-03-2018.
 */

public class StudentHolder extends RecyclerView.ViewHolder {

    private final TextView name;
    private final TextView sid;
    private final CheckBox check;
    private final TextView countView;


    public StudentHolder(View itemView) {
        super(itemView);

        name = itemView.findViewById(R.id.student_name);
        sid = itemView.findViewById(R.id.student_id);
        check = itemView.findViewById(R.id.attendance_check);
        countView = itemView.findViewById(R.id.student_attendance_count);

    }

    public CheckBox getCheck() {
        return check;
    }

    public void setName(String n) {
        name.setText(n);
    }

    public void setSid(String i) {
        sid.setText(i);
    }

    public void setCountView(String c) {
        countView.setText(c);
    }

    // get and set the status of attendance of that student
    public void setCheck(final String roll) {
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Attendance");
        reference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if(dataSnapshot.hasChild(roll)) {
                    if(dataSnapshot.child(roll).hasChild("status")) {
                        check.setChecked((boolean) dataSnapshot.child(roll).child("status").getValue());
                    }
                    else {
                        check.setChecked(false);
                    }
                    Log.d("TAG", "onDataChange: this is done");
                }
                else {
                    check.setChecked(false);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.d("TAG", "onCancelled: Database error");

            }
        });
    }

    // Update the attendance when the checkbox status is changed
    public void updateCheck(final String roll, final boolean status) {
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Attendance");
        reference.child(roll).child("status").setValue(status);
    }
}
