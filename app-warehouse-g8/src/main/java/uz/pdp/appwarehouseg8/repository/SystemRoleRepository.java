package uz.pdp.appwarehouseg8.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.appwarehouseg8.entity.SystemRole;
import uz.pdp.appwarehouseg8.entity.enums.SystemRoleEnum;

public interface SystemRoleRepository extends JpaRepository<SystemRole, SystemRoleEnum> {
    SystemRole findByRoleSystemEnum(SystemRoleEnum roleSystemEnum);
}
