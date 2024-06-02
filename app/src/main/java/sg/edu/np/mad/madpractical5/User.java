package sg.edu.np.mad.madpractical5;

public class User {
    public String name;
    public String description;
    public int id;
    public boolean followed;

    public User(String input_name, String input_description, int input_id, boolean input_followed){
        name = input_name;
        description = input_description;
        id = input_id;
        followed = input_followed;
    }

    public User(String input_name, String input_description, boolean input_followed){
        name = input_name;
        description = input_description;
        followed = input_followed;
    }

    public User() {}

    public String getName() { return name; }
    public void setName(String input_name) { name = input_name; }

    public String getDescription() { return description; }
    public void setDescription(String input_description) { description = input_description; }

    public int getId() { return id; }
    public void setId(int input_id) { id = input_id; }

    public boolean getFollowed() { return followed; }
    public void setFollowed(boolean input_followed) { followed = input_followed; }
}
