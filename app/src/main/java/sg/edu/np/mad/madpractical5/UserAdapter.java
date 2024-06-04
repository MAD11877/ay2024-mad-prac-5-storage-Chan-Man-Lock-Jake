package sg.edu.np.mad.madpractical5;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Parcelable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.UserViewHolder> {

    private ArrayList<User> userRecyclerList;
    private Context context;

    public UserAdapter(ArrayList<User> input_userRecyclerList, Context input_context) {
        userRecyclerList = input_userRecyclerList;
        context = input_context;
    }

    @Override
    public UserViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        return new UserViewHolder(inflater.inflate(R.layout.custom_activity_list, parent, false));
    }

    @Override
    public void onBindViewHolder(UserViewHolder holder, int position) {
        User user = userRecyclerList.get(position);
        //holder.bind(user);
        holder.userName.setText(user.getName());
        holder.userDescription.setText(user.getDescription());

        holder.userDisplayPicture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
                builder.setTitle("Profile")
                        .setMessage(user.getName())
                        .setPositiveButton("View", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Intent toMainActivity = new Intent(view.getContext(), MainActivity.class);
                                String[] data = {user.getName(), user.getDescription(), String.format("%s",user.getId()), String.format("%s",user.getFollowed())};
                                toMainActivity.putExtra("userData", data);
                                view.getContext().startActivity(toMainActivity);
                                dialog.cancel();
                            }
                        })
                        .setNegativeButton("Close", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) { dialog.cancel(); }
                        });
                builder.create();
                builder.show();
            }
        });
    }

    @Override
    public int getItemCount() { return userRecyclerList.size(); }

    public class UserViewHolder extends RecyclerView.ViewHolder {

        ImageView userDisplayPicture;
        TextView userName, userDescription;

        public UserViewHolder(View view) {
            super(view);
            userDisplayPicture = view.findViewById(R.id.imageView01);
            userName = view.findViewById(R.id.name);
            userDescription = view.findViewById(R.id.descrption);
        }
    }
}
