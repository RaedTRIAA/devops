package com.poly.gestioncatalogue5gr1.security.repository;

import com.poly.gestioncatalogue5gr1.security.entities.AppRole;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<AppRole,String > {
}
