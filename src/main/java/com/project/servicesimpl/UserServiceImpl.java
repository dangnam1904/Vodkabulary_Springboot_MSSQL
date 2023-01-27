package com.project.servicesimpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.project.models.User;
import com.project.repositories.UserRepository;
import com.project.services.UserService;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    public List<User> getUser(){
       return userRepository.findAll();
    }

    @Override
    public List<User> get() {
        return userRepository.get();
    }

    @Override
    public List<User> getAllUser() {
        return userRepository.findAll();
    }

    @Override
    public void saveUser(User user) {
        this.userRepository.save(user);
    }

    @Override
    public User getUserById(long id) {
        Optional<User> optional = userRepository.findById(id);
        User user = null;
        if( optional.isPresent()) {
            user = optional.get();
        } else {
            throw new RuntimeException("User not found for id: " + id);
        }
        return user;
    }

    @Override
    public User getUserByUsername(String username) {
        List<User> userList = userRepository.findAll();
        for (User user: userList) {
            if (user.getUsername().equals(username)) return user;
        }
        return null;
    }

    @Override
    public void deleteUserById(long id) {
        this.userRepository.deleteById(id);
    }
    @Override
    public Page<User> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection) {
        Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending():
                Sort.by(sortField).descending();

        Pageable pageable = PageRequest.of(pageNo-1, pageSize);

        return this.userRepository.findAll(pageable);
    }

    @Override
    public List<User> findByUsernameContaining(String s) {
        return userRepository.findByUsernameContaining(s);
    }

//    @Override
//    public List<User> findByRolesRole_ID(Long id) {
//        return userRepository.findByRolesRole_ID(id);
//    }

//    @Override
//    public void customUpdateUser(String username, String name, String password, LocalDate dob, String email,
//                                 int point, Long role_id, LocalDateTime timeupdate, Long userid) {
//        userRepository.customUpdateUser(username,name,password,dob,email,point,role_id,timeupdate,userid);
//    }

     public  List<User> filterUserByRold( Long i){
        return userRepository.filterUserByRold(i);
     }
}
