package com.project.Vodkabulary.services;

import java.util.List;

import com.project.Vodkabulary.models.Role;

public interface RoleService  {
    List<Role> getAllRole();
    void saveRole(Role role);
   Role getRoleById(long id);
    void deleteRoleById(long id);
}
