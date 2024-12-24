package Authservices.project.repository;

import Authservices.project.entities.UserInfo;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<UserInfo, Integer> {

    public UserInfo findByUsername(String username);

}
