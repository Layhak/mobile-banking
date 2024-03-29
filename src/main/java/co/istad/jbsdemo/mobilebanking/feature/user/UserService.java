package co.istad.jbsdemo.mobilebanking.feature.user;

import co.istad.jbsdemo.mobilebanking.feature.user.dto.UserRequest;
import co.istad.jbsdemo.mobilebanking.feature.user.dto.UserResponse;
import co.istad.jbsdemo.mobilebanking.feature.user.dto.UserUpdateRequest;

import java.util.List;

public interface UserService {
    List<UserResponse> getALlUsers();
    UserResponse createUser(UserRequest userRequest);

    UserResponse getUserById(String id);

    void deleteByUserId(String id);

    UserResponse updateUserById(String id, UserUpdateRequest  userUpdateRequest);

    UserResponse disableUserById(String id);

    UserResponse enableUserById(String id);
}
