package uz.pdp.appwarehouseg8.component;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import uz.pdp.appwarehouseg8.entity.CompanyPermission;
import uz.pdp.appwarehouseg8.entity.enums.CompanyPermissionEnum;
import uz.pdp.appwarehouseg8.repository.CompanyPermissionRepository;
import uz.pdp.appwarehouseg8.repository.SystemRoleRepository;

import java.util.ArrayList;
import java.util.List;

/**
 * BY SIROJIDDIN on 28.11.2020
 */


@Component
public class DataLoader implements CommandLineRunner {
    @Value("${spring.datasource.initialization-mode}")
    private String modeType;

    @Autowired
    SystemRoleRepository systemRoleRepository;
    @Autowired
    CompanyPermissionRepository companyPermissionRepository;

    @Override
    public void run(String... args) throws Exception {
        if (modeType.equals("always")) {
            CompanyPermissionEnum[] values = CompanyPermissionEnum.values();
            List<CompanyPermission> companyPermissions = new ArrayList<>();
            for (CompanyPermissionEnum value : values) {
                companyPermissions.add(new CompanyPermission(
                        value
                ));
                companyPermissionRepository.saveAll(companyPermissions);
            }
        }
    }
}
