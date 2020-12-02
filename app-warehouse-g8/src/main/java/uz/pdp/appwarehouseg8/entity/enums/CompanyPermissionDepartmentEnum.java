package uz.pdp.appwarehouseg8.entity.enums;

public enum CompanyPermissionDepartmentEnum {
    PRODUCT ("Products"),
    INPUT("Input Product"),
    CATEGORY("Input Category"),
    OUTPUT("Output Product"),
    PAYMENT("Payments");

    public String name;

    CompanyPermissionDepartmentEnum(String name) {
        this.name = name;
    }
}
