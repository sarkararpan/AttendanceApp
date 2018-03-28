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
    private DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Attendance");
    private final TextView name;
    private final TextView sid;
    private final CheckBox check;
    private final TextView countView;
    private long count;


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
        String text = "Total : " + c;
        countView.setText(text);
    }

    // get and set the status of attendance of that student
    public void setCheck(final String roll) {
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
    // TODO: Maybe pass the date string and the period here
    public void updateCheck(final String roll, final boolean status) {
        reference.child(roll).child("status").setValue(status);

        // TODO: Add date string option here

        // Use single value event listener here, important.
        // Otherwise this will update infinite times.
        reference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if(dataSnapshot.hasChild(roll)) {
                    if(dataSnapshot.child(roll).hasChild("count")) {
                        // TODO: Optimize
                        count = (long) dataSnapshot.child(roll).child("count").getValue();
                        if(status) {
                            reference.child(roll).child("count").setValue(count + 1);
                        } else {
                            if((count - 1) < 0) {
                                reference.child(roll).child("count").setValue(0);
                            } else {
                                reference.child(roll).child("count").setValue(count - 1);
                            }
                        }
                    } else {
                        if(status) {
                            reference.child(roll).child("count").setValue(1);
                        } else {
                            reference.child(roll).child("count").setValue(0);
                        }
                    }
                }
            }

            // TODO: Use proper logging here
            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}
