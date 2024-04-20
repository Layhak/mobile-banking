package co.istad.jbsdemo.mobilebanking.mapper;

import co.istad.jbsdemo.mobilebanking.domain.Role;
import co.istad.jbsdemo.mobilebanking.domain.User;
import co.istad.jbsdemo.mobilebanking.feature.user.dto.UserRequest;
import co.istad.jbsdemo.mobilebanking.feature.user.dto.UserResponse;
import co.istad.jbsdemo.mobilebanking.feature.user.dto.UserUpdateRequest;
import org.mapstruct.*;

import java.util.Set;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring", nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public interface UserMapper {
    @Named("toUserResponse")
    @Mapping(target = "roles", expression = "java(mapRoles(user.getRoles()))")
//    @Mapping(target = "studentIdCard", source = "studentIdCard")
    UserResponse toUserResponse(User user);

    default Set<String> mapRoles(Set<Role> roles) {
        return roles.stream().map(Role::getName).collect(Collectors.toSet());
    }

    @Mapping(target = "roles", ignore = true)
    User toUser(UserRequest userRequest);

    void updateUserFromRequest(@MappingTarget User updateUser, UserUpdateRequest userUpdateRequest);


    //    @Mapping(target = "user", source = "user")
//    @Mapping(target = "user", source = "user", qualifiedByName = "toUserResponse")
//    void setUserForAccountResponse(@MappingTarget AccountResponse accountResponse, User user);


}
