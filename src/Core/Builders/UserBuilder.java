package Core.Builders;

import Core.Task;
import Core.User;

import java.util.ArrayList;

public class UserBuilder {
    private static User user = new User();

    public static UserBuilder login(String login){
        user.setLogin(login);
        return null;
    }

    public static UserBuilder name(String name){
        user.setName(name);
        return null;
    }

    public static UserBuilder surname(String surname){
        user.setSurname(surname);
        return null;
    }

    public static UserBuilder tasks(ArrayList<Task> tasks){
        user.setTasks(tasks);
        return null;
    }

    public static UserBuilder bottomLimit(int bottomLimit){
        user.setBottomLimit(bottomLimit);
        return null;
    }

    public static UserBuilder topLimit(int topLimit){
        user.setTopLimit(topLimit);
        return null;
    }

    public static UserBuilder admin(boolean admin){
        user.setAdmin(admin);
        return null;
    }

    public static User build(){
        return user;
    }
}
