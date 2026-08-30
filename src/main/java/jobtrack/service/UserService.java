package jobtrack.service;

import jobtrack.entity.User;
import jobtrack.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User saveUser(User user) {
        return userRepository.save(user);
    }

    public List<User> getAllusers() {
        return userRepository.findAll();
    }

    public User getUserById(Long id) {
        return userRepository.findById(id).orElseThrow();
    }

    public User updateUser(Long id, User user) {
        User existing_user = userRepository.findById(id).orElseThrow();

        existing_user.setDateOfBirth(user.getDateOfBirth());
        existing_user.setEmail(user.getEmail());
        existing_user.setMobileNo(user.getMobileNo());
        existing_user.setName(user.getName());
        existing_user.setPassword(user.getPassword());

        return userRepository.save(existing_user);

    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

}
