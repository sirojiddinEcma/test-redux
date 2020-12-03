package uz.pdp.appwarehouseg8.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import uz.pdp.appwarehouseg8.entity.User;
import uz.pdp.appwarehouseg8.payload.ApiResponse;
import uz.pdp.appwarehouseg8.payload.UserDto;
import uz.pdp.appwarehouseg8.repository.AttachmentRepository;
import uz.pdp.appwarehouseg8.repository.UserRepository;

/**
 * BY SIROJIDDIN on 03.12.2020
 */


@Service
public class UserService {
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    AttachmentRepository attachmentRepository;
    @Autowired
    UserRepository userRepository;

    public ApiResponse editProfile(UserDto userDto, User user) {
        if (passwordEncoder.matches(userDto.getPassword(), user.getPassword())) {
            user.setFirstName(userDto.getFirstName());
            user.setLastName(userDto.getLastName());
            if (!user.getPhoneNumber().equals(userDto.getPhoneNumber())) {
                if (userRepository.existsByPhoneNumber(userDto.getPhoneNumber())) {
                    return new ApiResponse("Already phone", false);
                }
            }
            if (userDto.getPhoto() != null)
                user.setPhoto(attachmentRepository.findById(userDto.getPhoto()).orElseThrow(() -> new ResourceNotFoundException("Rasm topilmadi" + userDto.getPhoto())));
            user.setPhoneNumber(userDto.getPhoneNumber());
            userRepository.save(user);
            return new ApiResponse("Tahrirlandi", true);
        } else {
            return new ApiResponse("Parol xato", false);
        }
    }
}
