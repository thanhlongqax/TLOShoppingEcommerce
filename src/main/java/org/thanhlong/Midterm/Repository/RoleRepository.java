package org.thanhlong.Midterm.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.thanhlong.Midterm.Models.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {

}
