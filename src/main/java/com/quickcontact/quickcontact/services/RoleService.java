package com.quickcontact.quickcontact.services;

import com.quickcontact.quickcontact.entities.Role;
import com.quickcontact.quickcontact.repositories.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RoleService {
    private final RoleRepository roleRepository;

    public Role getRoleByName(String name) {
        return roleRepository.findRoleByName(name);
    }
}