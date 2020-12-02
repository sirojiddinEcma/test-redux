package uz.pdp.appwarehouseg8.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * BY SIROJIDDIN on 30.11.2020
 */


@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginDto {
    @NotBlank
    private String phoneNumber;

    @NotNull
    private String password;
}
