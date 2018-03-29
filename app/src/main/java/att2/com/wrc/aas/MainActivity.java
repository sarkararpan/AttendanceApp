package att2.com.wrc.aas;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

// TODO: Add proper navigation

public class MainActivity extends AppCompatActivity {

    private TextView teacherViewLink;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        teacherViewLink = findViewById(R.id.teacher_view_link);
    }

    @Override
    protected void onStart() {
        super.onStart();

        teacherViewLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, TeacherIntermediateActivity.class));
            }
        });
    }
}
