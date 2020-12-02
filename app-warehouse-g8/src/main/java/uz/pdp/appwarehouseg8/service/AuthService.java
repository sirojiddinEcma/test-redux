package uz.pdp.appwarehouseg8.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import uz.pdp.appwarehouseg8.component.MessageByLang;
import uz.pdp.appwarehouseg8.entity.Company;
import uz.pdp.appwarehouseg8.entity.CompanyRole;
import uz.pdp.appwarehouseg8.entity.User;
import uz.pdp.appwarehouseg8.entity.Warehouse;
import uz.pdp.appwarehouseg8.entity.enums.SystemRoleEnum;
import uz.pdp.appwarehouseg8.payload.ApiResponse;
import uz.pdp.appwarehouseg8.payload.LoginDto;
import uz.pdp.appwarehouseg8.payload.RegisterDto;
import uz.pdp.appwarehouseg8.repository.CompanyPermissionRepository;
import uz.pdp.appwarehouseg8.repository.SystemRoleRepository;
import uz.pdp.appwarehouseg8.repository.UserRepository;
import uz.pdp.appwarehouseg8.security.JwtTokenProvider;

import java.util.Collections;
import java.util.Optional;
import java.util.UUID;

/**
 * BY SIROJIDDIN on 28.11.2020
 */


@Service
public class AuthService implements UserDetailsService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    MessageByLang messageByLang;
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    SystemRoleRepository systemRoleRepository;
    @Autowired
    CompanyPermissionRepository companyPermissionRepository;
    @Autowired
    JwtTokenProvider jwtTokenProvider;

    public ApiResponse register(RegisterDto registerDto) {
        if (userRepository.existsByPhoneNumber(registerDto.getPhoneNumber())) {
            return new ApiResponse(messageByLang.getMessageByKey("already.exist"), false);
        }
        Company company = new Company(
                registerDto.getCompanyName(),
                registerDto.getAddress(),
                registerDto.getPhoneNumber());
        Warehouse warehouse = new Warehouse(
                "Main warehouse",
                registerDto.getAddress(),
                company);
        company.setWarehouses(Collections.singletonList(warehouse));

        CompanyRole companyRole = new CompanyRole(
                "Director",
                "Kompaniya rahbari",
                company,
                companyPermissionRepository.findAll());

        User user = new User(
                registerDto.getFirstName(),
                registerDto.getLastName(),
                registerDto.getPhoneNumber(),
                passwordEncoder.encode(registerDto.getPassword()),
                Collections.singletonList(company),
                companyRole,
                Collections.singletonList(systemRoleRepository.findByRoleSystemEnum(SystemRoleEnum.ROLE_USER))
        );
        company.setUser(user);
        userRepository.save(user);
        return new ApiResponse(
                messageByLang.getMessageByKey("successfully.registered"),
                true
        );
    }

    public ApiResponse login(LoginDto loginDto) {
        Optional<User> optionalUser = userRepository.findByPhoneNumber(loginDto.getPhoneNumber());
        if (!optionalUser.isPresent()) {
            return new ApiResponse(
                    messageByLang.getMessageByKey("unauthorized"),
                    false);
        } else {
            User user = optionalUser.get();
            if (passwordEncoder.matches(loginDto.getPassword(), user.getPassword())) {
                String token = jwtTokenProvider.generateToken(user.getId());
                return new ApiResponse(
                        messageByLang.getMessageByKey("success.login"),
                        true,
                        token
                );
            } else {
                return new ApiResponse(
                        messageByLang.getMessageByKey("unauthorized"),
                        false);
            }
        }
    }

    public UserDetails loadUserByUserId(String userIdFromToken) {
        return userRepository.findById(UUID.fromString(userIdFromToken)).orElseThrow(() -> new UsernameNotFoundException(userIdFromToken));
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        return null;
    }
}
