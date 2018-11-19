package br.com.omaisfood.repository;

import br.com.omaisfood.model.User;
import br.com.omaisfood.model.enumerators.Permission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User findByEmail(String email);

    Boolean existsByEmail(String email);

    List<User> findAllByPermissionsIn(List<Permission> permissions);
}
