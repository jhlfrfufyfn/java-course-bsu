package com.bsu;

import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

class Company {
    static final String[] FIELD_LIST = {"name", "shortName", "actualizationDate", "address", "foundationDate", "employeeNumber",
            "auditor", "phoneNumber", "eMail", "branch", "activityType", "webPage"};

    static Company VOID_COMPANY;

    String name;
    String shortName;
    Date actualizationDate;
    String address;
    Date foundationDate;
    int employeeNumber;
    String auditor;
    String phoneNumber;
    String eMail;
    String branch;
    String activityType;
    String webPage;

    String getNameKey() {
        return FIELD_LIST[0];
    }

    String getShortNameKey() {
        return FIELD_LIST[1];
    }

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    String getActualizationDateKey() throws ParseException {
        return FIELD_LIST[2];
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getActualizationDate() {
        return actualizationDate;
    }

    public void setActualizationDate(Date actualizationDate) {
        this.actualizationDate = actualizationDate;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Date getFoundationDate() {
        return foundationDate;
    }

    public void setFoundationDate(Date foundationDate) {
        this.foundationDate = foundationDate;
    }

    public int getEmployeeNumber() {
        return employeeNumber;
    }

    public void setEmployeeNumber(int employeeNumber) {
        this.employeeNumber = employeeNumber;
    }

    public String getAuditor() {
        return auditor;
    }

    public void setAuditor(String auditor) {
        this.auditor = auditor;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String geteMail() {
        return eMail;
    }

    public void seteMail(String eMail) {
        this.eMail = eMail;
    }

    public String getBranch() {
        return branch;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }

    public String getActivityType() {
        return activityType;
    }

    public void setActivityType(String activityType) {
        this.activityType = activityType;
    }

    public String getWebPage() {
        return webPage;
    }

    public void setWebPage(String webPage) {
        this.webPage = webPage;
    }

    String getAddressKey() {
        return FIELD_LIST[3];
    }

    String getFoundationDateKey() throws ParseException {
        return FIELD_LIST[4];
    }

    String getEmployeeNumberKey() {
        return FIELD_LIST[5];
    }

    String getAuditorKey() {
        return FIELD_LIST[6];
    }

    String getPhoneNumberKey() {
        return FIELD_LIST[7];
    }

    String getEMailKey() {
        return FIELD_LIST[8];
    }

    String getBranchKey() {
        return FIELD_LIST[9];
    }

    String getActivityTypeKey() {
        return FIELD_LIST[10];
    }

    String getWebPageKey() {
        return FIELD_LIST[11];
    }

    @Override
    public String toString() {
        return name + ";" + shortName + ";" + actualizationDate.toString() + ";"
                + address + ";" + foundationDate.toString() + ";" + employeeNumber + ";"
                + auditor + ";" + phoneNumber + ";" + eMail + ";" + branch + ";" + activityType + ";"
                + webPage + ";";
    }

    Company(String[] aData) throws ParseException {
        name = aData[0];
        shortName = aData[1];
        actualizationDate = Main.dateFormat.parse(aData[2]);
        address = aData[3];
        foundationDate = Main.dateFormat.parse(aData[4]);
        employeeNumber = Integer.parseInt(aData[5]);
        auditor = aData[6];
        phoneNumber = aData[7];
        eMail = aData[8];
        branch = aData[9];
        activityType = aData[10];
        webPage = aData[11];
    }

    void print(FileWriter fw) throws IOException {
        fw.write(name + ";" + shortName + ";" + actualizationDate.toString() + ";"
                + address + ";" + foundationDate.toString() + ";" + employeeNumber + ";"
                + auditor + ";" + phoneNumber + ";" + eMail + ";" + branch + ";" + activityType + ";"
                + webPage + ";");
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Company company = (Company) o;
        return employeeNumber == company.employeeNumber &&
                Objects.equals(name, company.name) &&
                Objects.equals(shortName, company.shortName) &&
                Objects.equals(actualizationDate, company.actualizationDate) &&
                Objects.equals(address, company.address) &&
                Objects.equals(foundationDate, company.foundationDate) &&
                Objects.equals(auditor, company.auditor) &&
                Objects.equals(phoneNumber, company.phoneNumber) &&
                Objects.equals(eMail, company.eMail) &&
                Objects.equals(branch, company.branch) &&
                Objects.equals(activityType, company.activityType) &&
                Objects.equals(webPage, company.webPage);
    }

    public String[] getFields(List<String> list, Company c) {
        List<String> ans = new ArrayList<>();
        for (String str : list) {
            if (str.equalsIgnoreCase(FIELD_LIST[0])) {
                ans.add(c.name);
            }
            if (str.equalsIgnoreCase(FIELD_LIST[1])) {
                ans.add(c.shortName);
            }
            if (str.equalsIgnoreCase(FIELD_LIST[2])) {
                ans.add(c.actualizationDate.toString());
            }
            if (str.equalsIgnoreCase(FIELD_LIST[3])) {
                ans.add(c.address);
            }
            if (str.equalsIgnoreCase(FIELD_LIST[4])) {
                ans.add(c.foundationDate.toString());
            }
            if (str.equalsIgnoreCase(FIELD_LIST[5])) {
                ans.add(Integer.toString(c.employeeNumber));
            }
            if (str.equalsIgnoreCase(FIELD_LIST[6])) {
                ans.add(c.auditor);
            }
            if (str.equalsIgnoreCase(FIELD_LIST[7])) {
                ans.add(c.phoneNumber);
            }
            if (str.equalsIgnoreCase(FIELD_LIST[8])) {
                ans.add(c.eMail);
            }
            if (str.equalsIgnoreCase(FIELD_LIST[9])) {
                ans.add(c.branch);
            }
            if (str.equalsIgnoreCase(FIELD_LIST[10])) {
                ans.add(c.activityType);
            }
            if (str.equalsIgnoreCase(FIELD_LIST[11])) {
                ans.add(c.webPage);
            }
        }
        return (String[]) ans.toArray();
    }

    boolean checkShortName(String s) {
        return s.equalsIgnoreCase(shortName);
    }

    enum CompanyFields {
        NAME(0), SHORT_NAME(1), ACTUALIZATION_DATE(2), ADDRESS(3),
        FOUNDATION_DATE(4), EMPLOYEE_NUMBER(5), AUDITOR(6), PHONE_NUMBER(7),
        E_MAIL(8), BRANCH(9), ACTIVITY_TYPE(10), WEB_PAGE(11);

        CompanyFields(int i) {
        }
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, shortName, actualizationDate, address, foundationDate, employeeNumber,
                auditor, phoneNumber, eMail, branch, activityType, webPage);
    }

}
