package att2.com.wrc.aas;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

public class ReportHolder extends RecyclerView.ViewHolder{
    private TextView sidView;
    private TextView countView;
    private TextView nameView;


    public ReportHolder(View itemView) {
        super(itemView);

        sidView = itemView.findViewById(R.id.report_student_id);
        countView = itemView.findViewById(R.id.report_attendance_count);
        nameView = itemView.findViewById(R.id.report_student_name);
    }

    public void setSidView(String sid) {
        sidView.setText(sid);
    }

    public void setCountView(long count) {
        countView.setText(String.valueOf(count));
    }

    public void setNameView(String name) {
        nameView.setText(name);
    }
}
