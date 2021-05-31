package com.icommerce.nab.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "user_info")
public class UserEntity extends BaseEntity {

    @Column(name = "uername")
    private String userName;

    @Column
    private String password;

    @Column(name = "fullname")
    private String fullName;

    @Column
    private String status;

    @ManyToMany
    @JoinTable(name = "user_role",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private List<RoleEntity> roles = new ArrayList();
}
