package uz.pdp.appwarehouseg8.service;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.UserRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
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
    @Autowired
    private JavaMailSender javaMailSender;
    @Autowired
    AuthenticationManager authenticationManager;

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
                Collections.singletonList(systemRoleRepository.findByRoleSystemEnum(SystemRoleEnum.ROLE_USER)));
        company.setUser(user);
        user.setCode(UUID.randomUUID().toString());
        user.setEmail(registerDto.getEmail());
        userRepository.save(user);
        if (user.getEmail() != null)
            new Thread(() -> sendToEmailAboutVerification(user)).start();
        return new ApiResponse(
                messageByLang.getMessageByKey("successfully.registered"),
                true);
    }

    public ApiResponse login(LoginDto loginDto) {
        if (getPhoneChecked(loginDto.getPhoneNumber())) {
            Optional<User> optionalUser = userRepository.findByPhoneNumber(loginDto.getPhoneNumber());
            if (!optionalUser.isPresent()) {
                return new ApiResponse(
                        messageByLang.getMessageByKey("unauthorized"),
                        false);
            } else {
                User user = optionalUser.get();
                if (passwordEncoder.matches(loginDto.getPassword(), user.getPassword())) {
                    if (!user.isAccountNonExpired()
                            || !user.isAccountNonLocked()
                            || !user.isCredentialsNonExpired()
                            || !user.isEnabled())
                        return new ApiResponse("Iltimos emailingizni tekshiring", false);
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
        } else {
            return new ApiResponse("Oka aldamang postmandan", false);
        }
    }

    public UserDetails loadUserByUserId(String userIdFromToken) {
        return userRepository.findById(UUID.fromString(userIdFromToken)).orElseThrow(() -> new UsernameNotFoundException(userIdFromToken));
    }

    @Override
    public UserDetails loadUserByUsername(String phoneNumber) throws UsernameNotFoundException {
        return userRepository.findByPhoneNumber(phoneNumber).orElseThrow(() -> new UsernameNotFoundException(phoneNumber));
    }


    public void sendToEmailAboutVerification(User user) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("noreply@baeldung.com");
        message.setTo(user.getEmail());
        message.setSubject("OKa tasdqilab yuboring");
        message.setText("http://localhost:3000/verification/" + user.getEmail() + "/" + user.getCode());
        javaMailSender.send(message);
    }

    public ApiResponse verificationEmail(String email, String code) {
        Optional<User> optionalUser = userRepository.findByEmailAndCodeAndEnabledFalse(email, code);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            user.setEnabled(true);
            user.setCode(null);
            userRepository.save(user);
            return new ApiResponse("tasdiqlandi", true, jwtTokenProvider.generateToken(user.getId()));
        }
        return new ApiResponse("Xatolik", false);
    }

    public ApiResponse checkPasswordAndLogin(LoginDto loginDto) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            loginDto.getPhoneNumber(),
                            loginDto.getPassword()
                    ));
            return new ApiResponse("Hammasi ok", true);
        } catch (Exception e) {
            return new ApiResponse("Xatolikk!!!", false);
        }
    }

    public Boolean getPhoneChecked(String phoneNumber) {
        try {
            UserRecord userRecord = FirebaseAuth.getInstance().getUserByPhoneNumber(phoneNumber);
            if (userRecord == null) {
                return false;
            } else {
                if (userRepository.existsByPhoneNumber(userRecord.getPhoneNumber())) {
                    FirebaseAuth.getInstance().deleteUser(userRecord.getUid());
                    return true;
                }
            }
        } catch (FirebaseAuthException e) {
            e.printStackTrace();
            return false;
        }
        return false;
    }
}
