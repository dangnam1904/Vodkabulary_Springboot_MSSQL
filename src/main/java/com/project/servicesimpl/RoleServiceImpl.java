package com.project.servicesimpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.models.Role;
import com.project.repositories.RoleRepository;
import com.project.services.RoleService;

@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    private RoleRepository roleRepository;
    @Override
    public List<Role> getAllRole() {
        return roleRepository.findAll();
    }

    @Override
    public void saveRole(Role role) {
        roleRepository.save(role);
    }

    @Override
    public Role getRoleById(long id) {
        Optional<Role> optional = roleRepository.findById(id);
       Role role=null;

        if( optional.isPresent()) {
           role = optional.get();
        } else {
            throw new RuntimeException("word not found for id: " + id);
        }

        return role;
    }

    @Override
    public void deleteRoleById(long id) {
        roleRepository.deleteById(id);
    }
}
