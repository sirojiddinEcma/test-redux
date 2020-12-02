package uz.pdp.appwarehouseg8.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import uz.pdp.appwarehouseg8.entity.Company;

import java.util.UUID;

@RepositoryRestResource(path = "company")
public interface CompanyRepository extends JpaRepository<Company, UUID> {
}
