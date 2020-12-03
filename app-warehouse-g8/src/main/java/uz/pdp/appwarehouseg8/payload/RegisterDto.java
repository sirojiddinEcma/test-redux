package uz.pdp.appwarehouseg8.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uz.pdp.appwarehouseg8.utils.ValidPassword;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

/**
 * BY SIROJIDDIN on 28.11.2020
 */


@Data
@AllArgsConstructor
@NoArgsConstructor
@ValidPassword(password = "password", prePassword = "prePassword")
public class RegisterDto {
    @NotBlank
    private String firstName;

    @NotBlank
    private String lastName;

    //    @Pattern(regexp = "^[+][9][9][8][0-9]{9}$")
    @Pattern(regexp = "\\(?\\+[0-9]{1,3}\\)? ?-?[0-9]{1,3} ?-?[0-9]{3,5} ?-?[0-9]{5}( ?-?[0-9]{3})? ?(\\w{1,10}\\s?\\d{1,6})?")
    private String phoneNumber;

    @NotBlank
    private String companyName;

    private String address;

    @Email
    private String email;

    @NotBlank
    @Size(min = 6, max = 12)
    private String password;

    @NotBlank
    @Size(min = 6, max = 12)
    private String prePassword;
}
