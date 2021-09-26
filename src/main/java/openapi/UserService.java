package openapi;

import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Service
public class UserService {
    private final List<User> userList = new ArrayList<>();

    @PostConstruct
    public void init() {
        userList.add(new User(1, "Test User", new Date(), "New Type"));
    }

    public User save(User user) {
        userList.add(user);
        return user;
    }

    public List<User> getAll() {
        return userList;
    }

    public void delete(int id) {
        for (int i = 0; i < userList.size(); i++) {
            if (id == userList.get(i).getId()) {
                userList.remove(i);
            }
        }
    }

    public User update(User newUser) {
        int id = newUser.getId();
        for (User user : userList) {
            if (id == user.getId()) {
                if (!Objects.equals(newUser.getName(), user.getName())) {
                    user.setName(newUser.getName());
                }
                if (newUser.getDate() != user.getDate()) {
                    user.setDate(newUser.getDate());
                }
                if (!Objects.equals(newUser.getType(), user.getType())) {
                    user.setType(newUser.getType());
                }
            }
        }
        return newUser;
    }
}
