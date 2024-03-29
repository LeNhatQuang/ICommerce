package com.icommerce.entity;

import com.icommerce.enumtype.RoleEnum;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(name = "role")
public class RoleEntity extends BaseEntity {

    @Enumerated(EnumType.STRING)
    @Column
    private RoleEnum role;

    @ManyToMany(mappedBy = "roles")
    private List<UserEntity> users = new ArrayList();
}
