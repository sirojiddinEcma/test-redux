package uz.pdp.appwarehouseg8.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.security.access.prepost.PreAuthorize;
import uz.pdp.appwarehouseg8.entity.Category;

import java.util.UUID;

@RepositoryRestResource(path = "category")
public interface CategoryRepository extends JpaRepository<Category, UUID> {
//    @PreAuthorize(value = "hasAnyAuthority('ADD_CATEGORY','EDIT_CATEGORY')")
    @Override
    <S extends Category> S save(S s);

//    @PreAuthorize(value = "hasAnyAuthority('DELETE_CATEGORY')")
    @Override
    void deleteById(UUID uuid);
}
