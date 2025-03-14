package vn.edu.t3h.model;

import vn.edu.t3h.entity.RoleEntity;
import vn.edu.t3h.entity.UserEntity;

import java.util.Set;
import java.util.stream.Collectors;

public class UserDTO {
    private int id;
    private String username;
    private String password;
    private Set<String> roles;
    private String fullname;

    public UserDTO(UserEntity user) {
        this.id = user.getId();
        this.username = user.getUsername();
        this.password = user.getPassword();
        this.roles = user.getRoles().stream().map(RoleEntity::getRoleName).collect(Collectors.toSet());
        this.fullname = (user.getIdentityCard() != null) ? user.getIdentityCard().getFullName() : null;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public Set<String> getRoles() {
        return roles;
    }

    public void setRoles(Set<String> roles) {
        this.roles = roles;
    }

    public UserDTO() {}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
