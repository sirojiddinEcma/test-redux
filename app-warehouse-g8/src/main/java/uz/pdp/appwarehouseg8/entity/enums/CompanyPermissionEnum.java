package uz.pdp.appwarehouseg8.entity.enums;

/**
 * BY SIROJIDDIN on 18.11.2020
 */


public enum CompanyPermissionEnum {
    GET_PRODUCT("Ko'rish", "Smotret", "View", CompanyTableNameEnum.PRODUCT),
    ADD_PRODUCT("Qo'shish", "Dobavit", "Create", CompanyTableNameEnum.PRODUCT),
    EDIT_PRODUCT("Tahrirlash", "Izmenit", "Edit", CompanyTableNameEnum.PRODUCT),
    DELETE_PRODUCT("O'chirish", "Udalit", "Delete", CompanyTableNameEnum.PRODUCT),
    GET_INPUT("Ko'rish", "Smotret", "View", CompanyTableNameEnum.INPUT),
    ADD_INPUT("Qo'shish", "Dobavit", "Create", CompanyTableNameEnum.INPUT),
    EDIT_INPUT("Tahrirlash", "Izmenit", "Edit", CompanyTableNameEnum.INPUT),
    DELETE_INPUT("O'chirish", "Udalit", "Delete", CompanyTableNameEnum.INPUT),
    GET_CATEGORY("Ko'rish", "Smotret", "View", CompanyTableNameEnum.CATEGORY),
    ADD_CATEGORY("Qo'shish", "Dobavit", "Create", CompanyTableNameEnum.CATEGORY),
    EDIT_CATEGORY("Tahrirlash", "Izmenit", "Edit", CompanyTableNameEnum.CATEGORY),
    DELETE_CATEGORY("O'chirish", "Udalit", "Delete", CompanyTableNameEnum.CATEGORY);

    public String nameUz;
    public String nameRu;
    public String nameEn;
    public CompanyTableNameEnum tableNameEnum;

    CompanyPermissionEnum(String nameUz, String nameRu, String nameEn, CompanyTableNameEnum tableNameEnum) {
        this.nameUz = nameUz;
        this.nameRu = nameRu;
        this.nameEn = nameEn;
    }
}
