package Authservices.project.Utils;

import Authservices.project.model.UserInfoDto;

public class ValidationUtil {
    public static boolean validateUserAttributes(UserInfoDto userInfoDto) {
        // Validate email format
        if (userInfoDto.getEmail() == null || !userInfoDto.getEmail().matches("^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Z]{2,6}$")) {
            return false;
        }

        // Validate password strength
        if (userInfoDto.getPassword() == null || userInfoDto.getPassword().length() < 6) {
            return false;
        }

        return true;
    }
}
