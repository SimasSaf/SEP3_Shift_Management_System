package group3.repository;


import group3.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IUserRepository
        extends JpaRepository<User, Long> {

    @Query("select u from User u where u.id = :#{#userId}")
    User findUserById(@Param("userId") Long userId);
}

