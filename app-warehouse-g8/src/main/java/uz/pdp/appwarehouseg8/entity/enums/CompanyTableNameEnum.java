package uz.pdp.appwarehouseg8.entity.enums;

/**
 * BY SIROJIDDIN on 18.11.2020
 */


public enum CompanyTableNameEnum {
    PRODUCT("", "", "Products", CompanyPermissionDepartmentEnum.PRODUCT),
    INPUT("", "", "Input Product", CompanyPermissionDepartmentEnum.INPUT),
    CATEGORY("", "", "Input Product", CompanyPermissionDepartmentEnum.CATEGORY),
    OUTPUT("", "", "Output Product", CompanyPermissionDepartmentEnum.OUTPUT),
    PAYMENT("", "", "Payments", CompanyPermissionDepartmentEnum.PAYMENT);

    public String nameUz;
    public String nameRu;
    public String nameEn;
    public CompanyPermissionDepartmentEnum companyPermissionDepartmentEnum;

    CompanyTableNameEnum(String nameUz, String nameRu, String nameEn, CompanyPermissionDepartmentEnum companyPermissionDepartmentEnum) {
        this.nameUz = nameUz;
        this.companyPermissionDepartmentEnum = companyPermissionDepartmentEnum;
    }
}
