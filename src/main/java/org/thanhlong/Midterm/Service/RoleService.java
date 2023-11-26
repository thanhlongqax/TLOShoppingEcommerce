package org.thanhlong.Midterm.Service;

import org.springframework.stereotype.Service;
import org.thanhlong.Midterm.Models.Role;

import java.util.List;
import java.util.Optional;

@Service
public interface RoleService {
    List<Role> getAllRole();
    Optional<Role> findRoleById(long id);
}
