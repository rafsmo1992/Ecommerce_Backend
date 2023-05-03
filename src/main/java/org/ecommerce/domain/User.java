package org.ecommerce.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "USERS")
public class User {
    @Id
    @NotNull
    @GeneratedValue(generator="increment")
    @GenericGenerator(name="increment", strategy = "increment")
    @Column(name = "USER_ID", nullable = false, unique = true)
    private Long id;

    @NotNull
    @Column(name = "LOGIN")
    private String userLogin;

    @NotNull
    @Column(name = "PASSWORD")
    private String userPassword;

    @NotNull
    @Column(name = "EMAIL")
    private String userEmail;

    @NotNull
    @Column(name = "SIGN_UP_DATE")
    private LocalDate signUpDate;

    @Column(name = "IS_ACTIVE")
    private boolean isActive = false;

    @OneToMany(
            mappedBy = "userLogin",
            cascade = CascadeType.REMOVE,
            fetch = FetchType.LAZY
    )
    private List<Cart> carts = new ArrayList<>();

    public User() {
        signUpDate = LocalDate.now();
    }

    public User(String userLogin, String userEmail, String userPassword) {
        this.userLogin = userLogin;
        this.userEmail = userEmail;
        this.userPassword = userPassword;
        signUpDate = LocalDate.now();
    }
}