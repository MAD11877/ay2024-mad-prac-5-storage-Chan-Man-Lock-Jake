package sg.edu.np.mad.madpractical5;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
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
        holder.userName.setText(user.getName());
        holder.userDeveloper.setText(user.getDescription());
    }

    @Override
    public int getItemCount() { return userRecyclerList.size(); }

    public class UserViewHolder extends RecyclerView.ViewHolder {

        ImageView userDisplayPicture;
        TextView userName, userDeveloper;

        public UserViewHolder(View view) {
            super(view);
            userDisplayPicture = view.findViewById(R.id.imageView01);
            userName = view.findViewById(R.id.name);
            userDeveloper = view.findViewById(R.id.developer);
        }
    }
}
