package vn.edu.t3h.controller.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.edu.t3h.entity.ProductEntity;
import vn.edu.t3h.entity.UserEntity;
import vn.edu.t3h.model.ProductDTO;
import vn.edu.t3h.model.UserDTO;
import vn.edu.t3h.service.UserService;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserResource {
    @Autowired
    private UserService userService;

    @GetMapping
    public List<UserDTO> allUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> getProduct(@PathVariable("id") int id) {
        return ResponseEntity.ok(userService.getUserById(id));
    }

    @GetMapping("/search")
    public ResponseEntity<List<UserDTO>> searchUsers(@RequestParam(name = "keyword") String keyword) {
        List<UserDTO> users = userService.findUser(keyword);
        return ResponseEntity.ok(users);
    }

    @PostMapping
    public ResponseEntity<String> createUser(@RequestBody UserDTO userDTO) {
        userService.addUser(userDTO);
        return ResponseEntity.ok("da them user" + userDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateUser(@PathVariable("id") int id, @RequestBody UserDTO userDTO) {
        userDTO.setId(id);
        UserDTO user = userService.getUserById(id);
        if (user != null) {
            userService.updateUser(userDTO);
            return ResponseEntity.ok("da update user" + user);
        }else {
            return ResponseEntity.ok("User khong ton tai");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable(name = "id") int id) {
        userService.deleteUserById(id);
        return ResponseEntity.ok("Xoa user thanh cong");
    }



}
