package sg.edu.np.mad.madpractical5;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private SQLiteDatabase db;
    DataBaseHandler dbHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

//        dbHandler = new DataBaseHandler(this, null, null, 1);
//        db = dbHandler.getWritableDatabase();
//        dbHandler.onReset(db);
//        dbHandler.createNewUserData();
    }

    @Override
    protected void onStart() {
        super.onStart();

        dbHandler = new DataBaseHandler(this, null, null, 1);
        db = dbHandler.getWritableDatabase();

        TextView tvName = findViewById(R.id.profileName);
        TextView tvDescription = findViewById(R.id.profileDescription);
        Button btnFollow = findViewById(R.id.profileFollow);
        Button btnMessage = findViewById(R.id.profileMessage);

        String[] Data = getIntent().getExtras().getStringArray("userData");
        User userData = new User(Data[0], Data[1], Boolean.parseBoolean(Data[3]));
        userData.setId(Integer.parseInt(Data[2]));

        tvName.setText(userData.getName());
        tvDescription.setText(userData.getDescription());
        //btnFollow.setText(Boolean.toString(userData.getFollowed()));

        if (userData.getFollowed()){ //TRUE means already followed means text is UNFOLLOW
            btnFollow.setText("Unfollow");
        }
        else { btnFollow.setText("Follow"); } //NOT TRUE means not followed means text is FOLLOW
        btnFollow.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                if (userData.getFollowed()){ //TRUE means change to FALSE means text set to FOllOW
                    btnFollow.setText("Follow");
                    userData.setFollowed(false);
                    Toast.makeText(getApplicationContext(), "Unfollowed", Toast.LENGTH_SHORT).show();
                } else {
                    btnFollow.setText("Unfollow");
                    userData.setFollowed(true); //Function not called????? TODO
                    Toast.makeText(getApplicationContext(), "Followed", Toast.LENGTH_SHORT).show();
                }
                dbHandler.updateUser(userData);
            }
        });
    }
}