package Authservices.project.service;

import Authservices.project.Utils.ValidationUtil;
import Authservices.project.entities.UserInfo;
import Authservices.project.model.UserInfoDto;
import Authservices.project.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Objects;
import java.util.UUID;

@Component
@AllArgsConstructor
@Data
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private final UserRepository userRepository;

    @Autowired
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserInfo user = userRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }
        return new CustomUserDetails(user);
    }

    public UserInfo checkIfUserAlreadyExist(UserInfoDto userInfoDto) {
        return userRepository.findByUsername(userInfoDto.getUsername());
    }

    public Boolean signupUser(UserInfoDto userInfoDto) {
        // Validate user email and password
//        if (!ValidationUtil.validateUserAttributes(userInfoDto)) {
//            throw new IllegalArgumentException("Invalid user attributes provided.");
//        }

        userInfoDto.setPassword(passwordEncoder.encode(userInfoDto.getPassword()));
       if(Objects.nonNull(checkIfUserAlreadyExist(userInfoDto))){
           return false;
       }
       String userId = UUID.randomUUID().toString();
       userRepository.save(new UserInfo(userId, userInfoDto.getUsername(), userInfoDto.getPassword(),
                      new HashSet<>()));
       return true;
    }
}