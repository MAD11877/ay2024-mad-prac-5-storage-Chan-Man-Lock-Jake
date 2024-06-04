package sg.edu.np.mad.madpractical5;

import android.database.sqlite.SQLiteDatabase;
import android.graphics.drawable.DrawableWrapper;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ListActivity extends AppCompatActivity {

    private SQLiteDatabase db;
    private DataBaseHandler dbHandler;
    private UserAdapter adapter;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_list);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        recyclerView = findViewById(R.id.listRecyclerView);

        dbHandler = new DataBaseHandler(this, null, null, 1);
        db = dbHandler.getWritableDatabase();
        dbHandler.onReset(db);
        dbHandler.createNewUserData();

        db = dbHandler.getWritableDatabase();
        adapter = new UserAdapter(dbHandler.getUsers(), this);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);

        db = dbHandler.getWritableDatabase();
    }
}