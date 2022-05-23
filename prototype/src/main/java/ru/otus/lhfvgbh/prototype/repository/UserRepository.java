package ru.otus.lhfvgbh.prototype.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.otus.lhfvgbh.prototype.domain.User;

public interface UserRepository extends JpaRepository<User, Long> {

    User findByName(String name);

    @Modifying(clearAutomatically = true)
    @Query("UPDATE User u SET u.isActive=false WHERE u.id = :id")
    void deactivate(@Param("id") Long id);

    @Modifying(clearAutomatically = true)
    @Query("UPDATE User u SET u.isActive=true WHERE u.id = :id")
    void activate(@Param("id") Long id);

}
