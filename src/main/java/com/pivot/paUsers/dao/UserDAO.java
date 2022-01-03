package com.pivot.paUsers.dao;

import com.pivot.paUsers.dto.CountByGender;
import com.pivot.paUsers.models.UserAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


import java.util.List;
import java.util.UUID;

@Repository
public interface UserDAO extends JpaRepository<UserAccount, UUID> {
    UserAccount findByEmail(String email);
    UserAccount findByUserId(UUID userId);

    @Query("select new com.pivot.paUsers.dto.CountByGender(u.gender AS title, count(u.gender) AS value) from UserAccount AS u group by u.gender")
    List<CountByGender> countTotalByGender();
}
