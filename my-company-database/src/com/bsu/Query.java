package com.bsu;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

class Query {

    public enum RequestType {
        BY_SHORT_NAME(1),
        BY_BRANCH(2),
        BY_ACTIVITY_TYPE(3),
        BY_FOUNDATION_DATE(4),
        BY_EMPLOYEE_NUMBER(5),
        DEFAULT(0);

        private final int value;

        RequestType(int value) {
            this.value = value;
        }

        public static RequestType fromIntValue(int value) {
            for (RequestType type : values()) {
                if (type.value == value) {
                    return type;
                }
            }
            return RequestType.DEFAULT;
        }
    }


    static List<Company> findByFDate(Date startDate, Date endDate, List<Company> records) {
        List<Company> ans = new ArrayList<>();
        for (Company it : records) {
            if (startDate.before(it.getFoundationDate()) && endDate.after(it.getFoundationDate())) {
                ans.add(it);
            }
        }
        return ans;
    }

    static List<Company> findByEmplNumber(int lowerBound, int upperBound, List<Company> records) {
        List<Company> ans = new ArrayList<>();
        for (Company it : records) {
            if (lowerBound <= it.getEmployeeNumber() && it.getEmployeeNumber() <= upperBound) {
                ans.add(it);
            }
        }
        return ans;
    }

    static List<Company> findByBranch(String branch, List<Company> records) {
        List<Company> ans = new ArrayList<>();
        for (Company it : records) {
            if (it.getBranch().equalsIgnoreCase(branch)) {
                ans.add(it);
            }
        }
        return ans;
    }

    static List<Company> findByActType(String activityType, List<Company> records) {
        List<Company> ans = new ArrayList<>();
        for (Company it : records) {
            if (it.getActivityType().equalsIgnoreCase(activityType)) {
                ans.add(it);
            }
        }
        return ans;
    }

    static Company findByShortName(String shortName, List<Company> records) {
        for (Company it : records) {
            if (it.getShortName().equalsIgnoreCase(shortName)) {
                return it;
            }
        }
        return Company.VOID_COMPANY;
    }
}
