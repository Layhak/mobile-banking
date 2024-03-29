package co.istad.jbsdemo.mobilebanking.feature.user;

import co.istad.jbsdemo.mobilebanking.feature.user.dto.UserRequest;
import co.istad.jbsdemo.mobilebanking.feature.user.dto.UserResponse;
import co.istad.jbsdemo.mobilebanking.feature.user.dto.UserUpdateRequest;
import co.istad.jbsdemo.mobilebanking.utilities.BaseResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;


//provide default value for requestBody and param
@RestController
@RequestMapping("api/v1/users")
@RequiredArgsConstructor
public class UserRestController {
    private final UserService userService;

    @GetMapping
    @Operation(summary = "Get all user")
    public BaseResponse<List<UserResponse>> getAllUser() {
        return BaseResponse.<List<UserResponse>>ok().setPayload(userService.getALlUsers());
    }

    @GetMapping("{id}")
    @Operation(summary = "Get user by id")
    public BaseResponse<UserResponse> getUserById(@PathVariable("id") String id) {
        return BaseResponse.<UserResponse>ok().setPayload(userService.getUserById(id));
    }

    @PostMapping
    @Operation(summary = "Register new user", requestBody = @RequestBody(content = @Content(schema = @Schema(implementation = UserRequest.class), examples = @ExampleObject(value = """
            {
              "username": "Layhak",
              "fullName": "Heng Layhak",
              "gender": "male",
              "pin": "123456",
              "email": "layhak@test.com",
              "password": "string",
              "profileImage": "string",
              "phoneNumber": "string",
              "cityOrProvince": "string",
              "khanOrDistrict": "string",
              "sangkatOrCommune": "string",
              "employeeType": "string",
              "companyName": "string",
              "mainSourceOfIncome": "string",
              "monthlyIncomeRange": 0,
              "studentIdCard": "string",
              "roles": [
                "Admin","Stuff","User"
              ]
            }
            """))))
    public BaseResponse<UserResponse> registerUser(@Valid @org.springframework.web.bind.annotation.RequestBody UserRequest userRequest) {
        return BaseResponse.<UserResponse>createSuccess().setPayload(userService.createUser(userRequest));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete User by id")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public  BaseResponse<UserResponse> deleteUserById(@PathVariable String id){
        userService.deleteByUserId(id);
        return BaseResponse.ok();
    }

    @PatchMapping("/{id}")
    @Operation(summary = "Register new user", requestBody = @RequestBody(content = @Content(schema = @Schema(implementation = UserRequest.class), examples = @ExampleObject(value = """
            {
              "fullName": "Heng Layhak",
              "gender": "male",
              "password": "string",
              "profileImage": "string",
              "phoneNumber": "string",
              "cityOrProvince": "string",
              "khanOrDistrict": "string",
              "sangkatOrCommune": "string",
              "employeeType": "string",
              "companyName": "string",
              "mainSourceOfIncome": "string",
              "monthlyIncomeRange": 0,
              "studentIdCard": "string"
            }
            """))))
    @ResponseStatus(HttpStatus.OK)

    public BaseResponse<UserResponse> updateUserById(@PathVariable String id,@org.springframework.web.bind.annotation.RequestBody UserUpdateRequest userUpdateRequest) {
        return BaseResponse.<UserResponse>updateSuccess().setPayload(userService.updateUserById(id,userUpdateRequest));
    }
    @PatchMapping("/{id}/disable")
    @Operation(summary = "Disable User by id")
    @ResponseStatus(HttpStatus.OK)
    public BaseResponse<UserResponse> disableUserById(@PathVariable String id) {
        return BaseResponse.<UserResponse>ok().setPayload(userService.disableUserById(id));
    }
    @PatchMapping("/{id}/enable")
    @Operation(summary = "Enable User by id")
    @ResponseStatus(HttpStatus.OK)
    public BaseResponse<UserResponse> enableUserById(@PathVariable String id) {
        return BaseResponse.<UserResponse>ok().setPayload(userService.enableUserById(id));
    }
}
