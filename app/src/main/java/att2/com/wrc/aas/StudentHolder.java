package att2.com.wrc.aas;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

/**
 * Created by Aurghya on 24-03-2018.
 */

public class StudentHolder extends RecyclerView.ViewHolder {

    private final TextView name;
    private final TextView sid;

    public StudentHolder(View itemView) {
        super(itemView);

        name = itemView.findViewById(R.id.student_name);
        sid = itemView.findViewById(R.id.student_id);
    }

    public void setName(String n) {
        name.setText(n);
    }

    public void setSid(long i) {
        sid.setText(String.valueOf(i));
    }
}
