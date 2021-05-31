package com.icommerce.nab.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "role")
public class RoleEntity extends BaseEntity {

    @Column
    private String code;

    @Column
    private String name;

    @ManyToMany(mappedBy = "roles")
    private List<UserEntity> users = new ArrayList();
}
