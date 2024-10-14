package etec.app.api.service;

import etec.app.api.entity.User;
import etec.app.api.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository repository;

    public User saveUser(User user){
        return repository.save(user);
    }

    public User findUserById(int id){
        Optional<User> user = repository.findById(id);
        /*
            if (user.isEmpty()) {
                return null;
            }
            return user.get();
        */
        return user.orElse(null);
    }

    public List<User> findAllUser() {
        return repository.findAll();
    }

    public User updateUser(User user){
        Optional<User> dbUser = repository.findById(user.getId());
        if (dbUser.isEmpty()) {
            return null;
        }
        User existUser = dbUser.get();
        existUser.setUsername(user.getUsername());
        existUser.setPassword(user.getPassword());
        existUser.setEmail(user.getEmail());
        return repository.save(existUser);
    }

    public void deleteUser(int id){
        Optional<User> dbUser = repository.findById(id);
        dbUser.ifPresent(value -> repository.delete(value));
    }

}
