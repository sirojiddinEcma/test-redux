package uz.pdp.appwarehouseg8.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.appwarehouseg8.entity.CompanyPermission;

import java.util.UUID;

public interface CompanyPermissionRepository extends JpaRepository<CompanyPermission, UUID> {
}
