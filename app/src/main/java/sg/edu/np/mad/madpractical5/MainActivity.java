package sg.edu.np.mad.madpractical5;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private SQLiteDatabase db;
    private DataBaseHandler dbHandler;

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

        TextView tvName = findViewById(R.id.profileName);
        TextView tvDescription = findViewById(R.id.profileDescription);
        Button btnFollow = findViewById(R.id.profileFollow);
        Button btnMessage = findViewById(R.id.profileMessage);
    }

    /*public void newUser (View view) {
        DataBaseHandler dbHandler = new DataBaseHandler(this, null, null, 1);
        int quantity = Integer.parseInt(quantityBox.getText().toString());
        User user = new User(productBox.getText().toString(), quantity);
        dbHandler.addProduct(product);
        productBox.setText("");
        quantityBox.setText("");*/
}