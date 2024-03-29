package co.istad.jbsdemo.mobilebanking.feature.user;

import co.istad.jbsdemo.mobilebanking.domain.Role;
import co.istad.jbsdemo.mobilebanking.domain.User;
import co.istad.jbsdemo.mobilebanking.feature.roles.RoleRepository;
import co.istad.jbsdemo.mobilebanking.feature.user.dto.UserRequest;
import co.istad.jbsdemo.mobilebanking.feature.user.dto.UserResponse;
import co.istad.jbsdemo.mobilebanking.feature.user.dto.UserUpdateRequest;
import co.istad.jbsdemo.mobilebanking.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.HashSet;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final UserMapper userMapper;

    @Override
    public List<UserResponse> getALlUsers() {
        return userRepository.findAll().stream().map(userMapper::toUserResponse).toList();
    }

    @Override
    public UserResponse createUser(UserRequest userRequest) {
        //check if email and username already exist
        if (userRepository.existsByUsername(userRequest.username())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Username Already Exist.");
        }
        if (userRepository.existsByEmail(userRequest.email())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Email Already Exist.");
        }
        Set<Role> roles = new HashSet<>();
        for (String role : userRequest.roles()) {
            Role roleObject = roleRepository.findByName(role).orElseThrow(() -> new NoSuchElementException("Role: <" + role + "> could not found!"));
            roles.add(roleObject);
        }
        User user = userMapper.toUser(userRequest);
        user.setBlocked(false);
        user.setDeleted(false);
        user.setRoles(roles);
        userRepository.save(user);

        return userMapper.toUserResponse(user);
    }

    @Override
    public UserResponse getUserById(String id) {
        User user = userRepository.findById(id).orElseThrow(() -> new NoSuchElementException("There is no user with id =<" + id + ">"));
        return userMapper.toUserResponse(user);
    }

    @Override
    public void deleteByUserId(String id) {
        User user = userRepository.findById(id).orElseThrow(() -> new NoSuchElementException("There is no user with id =<" + id + ">"));
            userRepository.delete(user);
    }

    @Override
    public UserResponse updateUserById(String id, UserUpdateRequest userUpdateRequest) {
        User updateUser = userRepository.findById(id).orElseThrow(() -> new NoSuchElementException("There is no user with " + id));
        System.out.println(updateUser.getGender());
        userMapper.updateUserFromRequest(updateUser, userUpdateRequest);
        System.out.println(updateUser.getGender());
        var updatedUser = userRepository.save(updateUser);
        return userMapper.toUserResponse(updatedUser);
    }

    @Override
    public UserResponse disableUserById(String id) {
        int affectedRow = userRepository.updateBlockedStatusById(id, true);
        if (affectedRow > 0) {
            return userMapper.toUserResponse(userRepository.findById(id).orElse(null));
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User with id = " + id + " doesn't exits!");
    }

    @Override
    public UserResponse enableUserById(String id) {
        int affectedRow = userRepository.updateBlockedStatusById(id, false);
        if (affectedRow > 0) {
            return userMapper.toUserResponse(userRepository.findById(id).orElse(null));
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User with id = " + id + " doesn't exits!");
    }
}
