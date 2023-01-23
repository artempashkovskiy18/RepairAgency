package models;

import constants.OrderStatus;
import constants.Role;

import java.util.Objects;

public class User{
    private int id;
    private String name;
    private String phone;
    private String email;
    private String password;
    private Role role;

    public User(String name, String phone, String email, String password, Role role) {
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.password = password;
        this.role = role;
        this.id = -1;
    }

    public User(int id, String name, String phone, String email, String password, Role role) {
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.password = password;
        this.role = role;
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id == user.id && name.equals(user.name) && Objects.equals(phone, user.phone) && email.equals(user.email) && password.equals(user.password) && role == user.role;
    }


    @Override
    public int hashCode() {
        return Objects.hash(id, name, phone, email, password, role);
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public Role getRole() {
        return role;
    }
}
