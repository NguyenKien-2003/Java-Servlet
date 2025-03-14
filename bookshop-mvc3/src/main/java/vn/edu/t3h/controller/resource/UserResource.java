package vn.edu.t3h.controller.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
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

    @GetMapping("/search")
    public ResponseEntity<List<UserDTO>> searchUsers(@RequestParam(name = "keyword") String keyword) {
        List<UserDTO> users = userService.findUser(keyword);
        return ResponseEntity.ok(users);
    }

}
