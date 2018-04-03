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

import java.text.DateFormat;

/**
 * StudentHolder, Created by Aurghya, 24-03-2018.
 * ViewHolder for student_view
 * Handle with care, this class handles the count of attendance
 * and also the checkbox for the attendance, messing this up will
 * result in a lot of unwanted bugs.
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

    /**
     * @return The checkbox for setting the status.
     */
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

    /**
     * Handles the checkbox checked status when first loading the view
     * or after checking an attendance.
     * @param roll The Roll number or the Unique ID of the student.
     * @param date The date of the attendance to be added or edited.
     * @param classId The class ID of the class / the subject.
     * @param periodId The period ID, normally starting with P, like P1, P2 etc.
     */
    public void setCheck(final String roll, final String date, final String classId, final String periodId) {
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // Order roll -> classId -> date -> periodId
                if (dataSnapshot.hasChild(roll)) {
                    if (dataSnapshot.child(roll).hasChild(classId)) {
                        if (dataSnapshot.child(roll).child(classId).hasChild(date)) {
                            if (dataSnapshot.child(roll).child(classId).child(date).hasChild(periodId)) {
                                check.setChecked((boolean) dataSnapshot
                                        .child(roll)
                                        .child(classId)
                                        .child(date)
                                        .child(periodId)
                                        .getValue());
                            } else {
                                check.setChecked(false);
                            }
                        } else {
                            check.setChecked(false);
                        }
                    } else {
                        check.setChecked(false);
                    }
                    Log.d("TAG", "onDataChange: this is done");
                } else {
                    check.setChecked(false);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.d("TAG", "onCancelled: Database error");

            }
        });
    }

    /**
     * Updates the attendance and the attendance count in the database.
     * DO NOT CHANGE THIS METHOD, it will mess up the whole system of how
     * attendance is counted and will probably result in an infinite loop.
     * @param roll The Roll number or the Unique ID of the student.
     * @param date The date of the attendance to be added or edited.
     * @param classId The class ID of the class / the subject.
     * @param periodId The period ID, normally starting with P, like P1, P2 etc.
     * @param status Status of the checkbox, boolean.
     */
    public void updateCheck(final String roll, final String date, final String classId, final String periodId, final boolean status) {
        reference.child(roll)
                .child(classId)
                .child(date)
                .child(periodId)
                .setValue(status);


        // Use single value event listener here, important.
        // Otherwise this will update infinite times.
        reference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.hasChild(roll)) {
                    if (dataSnapshot.child(roll).hasChild("count")) {
                        count = (long) dataSnapshot.child(roll).child("count").getValue();
                        if (status) {
                            reference.child(roll).child("count").setValue(count + 1);
                        } else {
                            if ((count - 1) < 0) {
                                reference.child(roll).child("count").setValue(0);
                            } else {
                                reference.child(roll).child("count").setValue(count - 1);
                            }
                        }
                    } else {
                        if (status) {
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
