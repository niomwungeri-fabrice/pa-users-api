package com.pivot.paUsers.services;

import com.pivot.paUsers.dao.UserDAO;
import com.pivot.paUsers.dto.CompleteForm;
import com.pivot.paUsers.dto.CountByGender;
import com.pivot.paUsers.models.UserAccount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserService {

    @Autowired
    private UserDAO userDAO;

    public UserAccount signUp(UserAccount user) {
        return userDAO.save(user);
    }

    public UserAccount findByEmail (String email){
        return userDAO.findByEmail(email);
    }

    public Optional<UserAccount> findByUserId (String userId){

        return userDAO.findById(UUID.fromString(userId));
    }


    public UserAccount completeForm(CompleteForm userAccount, String userId){
        UserAccount accountDb = userDAO.findByUserId(UUID.fromString(userId));
        accountDb.setAge(userAccount.getAge());
        accountDb.setGender(userAccount.getGender());
        accountDb.setPhoneNumber(userAccount.getPhoneNumber());
        return userDAO.save(accountDb);
    }


    public List<CountByGender> countByGender() {
        return userDAO.countTotalByGender();
    }

    public List<UserAccount> getAllUsers(){
        return userDAO.findAll();
    }

}
