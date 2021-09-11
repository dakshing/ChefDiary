package com.mydiaries.chefdiary.user.repositories;

import com.mydiaries.chefdiary.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByMailIdEquals(String mailId);
    User findByUserNameEquals(String userName);
}
