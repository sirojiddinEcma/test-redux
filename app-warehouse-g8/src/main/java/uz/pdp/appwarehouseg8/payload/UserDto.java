package uz.pdp.appwarehouseg8.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

/**
 * BY SIROJIDDIN on 03.12.2020
 */


@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private UUID photo;
    private String password;

}
