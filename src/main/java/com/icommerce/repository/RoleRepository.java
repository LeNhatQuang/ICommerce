package com.icommerce.repository;

import com.icommerce.entity.RoleEntity;
import com.icommerce.enumtype.RoleEnum;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<RoleEntity, Long> {
    Optional<RoleEntity> findByName(RoleEnum user);
}
