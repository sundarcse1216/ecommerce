package com.bzone.ecomm.service;

import com.bzone.ecomm.dto.BZoneResponse;
import com.bzone.ecomm.entiry.Users;
import com.bzone.ecomm.exception.RecordNotFoundException;
import com.bzone.ecomm.repo.UserRepository;
import com.bzone.ecomm.util.CommonConstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

/**
 * @author sundar
 * @since 16-09-2022
 */
@Service
public class UserService {

    @Autowired
    UserRepository userRepo;

    @Autowired
    BZoneResponse response;

    @Transactional
    public BZoneResponse insertUser(Users user) {
        Users savedUser = userRepo.save(user);
        if (Objects.nonNull(savedUser)) {
            response.setCode(CommonConstance.CREATE_SUCCESS);
            response.setData(savedUser);
            response.setMessage(String.format("User '" + savedUser.getUserName() + "' Created Successfully"));
        } else {
            response.setCode(CommonConstance.INTERNAL_SERVER_ERROR);
            response.setMessage("Failed to insert user");
        }
        return response;
    }

    @Transactional
    public BZoneResponse updateUser(Users user, Long id) {
        Optional<Users> savedUser = userRepo.findById(id);
        if (savedUser.isPresent()) {
            user.setId(savedUser.get().getId());
            Users updatedUser = userRepo.save(user);
            response.setCode(CommonConstance.SUCCESS_CODE);
            response.setData(updatedUser);
            response.setMessage(String.format("User '" + updatedUser.getUserName() + "' Updated Successfully"));
        } else {
            response.setMessage("User Not Found");
            response.setCode(CommonConstance.NO_CONTENT);
        }
        return response;
    }

    public BZoneResponse getUserById(Long userId) {
        Optional<Users> user = userRepo.findById(userId);
        if (user.isPresent()) {
            response.setCode(CommonConstance.SUCCESS_CODE);
            response.setData(user);
            response.setMessage("Success");
        } else {
//            response.setMessage("User Not Found");
//            response.setCode(CommonConstance.NO_CONTENT);
            throw new RecordNotFoundException("User Not Found!");
        }

        return response;
    }

    public BZoneResponse gelAllUser() {
//        List<Users> users = userRepo.findAll();
        List<Users> users = userRepo.findAllByStatus(Boolean.TRUE);
        if (!users.isEmpty()) {
            response.setCode(CommonConstance.SUCCESS_CODE);
            response.setData(users);
            response.setMessage("Success");
        } else {
            response.setMessage("No User(s)");
            response.setCode(CommonConstance.NO_CONTENT);
        }
        return response;
    }

    @Transactional
    public BZoneResponse deleteUser(Long userId) {
        Optional<Users> user = userRepo.findById(userId);
        if (user.isPresent()) {
            Users savedUser = user.get();
            savedUser.setStatus(Boolean.FALSE);
            Users updatedUser = userRepo.save(savedUser);
            response.setCode(CommonConstance.SUCCESS_CODE);
            response.setData(updatedUser);
            response.setMessage(String.format("User '" + updatedUser.getUserName() + "' Deleted Successfully"));
        } else {
            response.setMessage("User Not Found");
            response.setCode(CommonConstance.NO_CONTENT);
        }
        return response;
    }

    public BZoneResponse getUserByEmail(String email) {
        Users user = userRepo.findByEmail(email);
        if (Objects.nonNull(user)) {
            response.setCode(CommonConstance.SUCCESS_CODE);
            response.setMessage(String.format("Username sent to '" + email));
        } else {
            response.setMessage("Email '" + email + "' not found!");
            response.setCode(CommonConstance.NO_CONTENT);
        }
        return response;
    }

    public BZoneResponse getPasswordByEmail(String email) {
        Users user = userRepo.findByEmail(email);
        if (Objects.nonNull(user)) {
            response.setCode(CommonConstance.SUCCESS_CODE);
            response.setMessage(String.format("Password sent to '" + email));
        } else {
            response.setMessage("Email '" + email + "' not found!");
            response.setCode(CommonConstance.NO_CONTENT);
        }
        return response;
    }

}
