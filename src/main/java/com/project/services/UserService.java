package com.project.services;


import java.util.List;

import org.springframework.data.domain.Page;

import com.project.models.User;

public interface UserService {
    List<User> get();
    List<User> getAllUser();
    void saveUser(User user);
    User getUserById(long id);
    User getUserByUsername(String username);
    void deleteUserById(long id);
    Page<User> findPaginated(int pageNo, int PageSize, String sortField, String sortDirection);

//    void customUpdateUser(String username, String name,String password,LocalDate dob, String email,
//                         int point, Long role_id, LocalDateTime timeupdate, Long userid);

    List<User> findByUsernameContaining(String s);
    //List<User> findByRolesRole_ID(Long id);

    List<User> filterUserByRold( Long i);
}
