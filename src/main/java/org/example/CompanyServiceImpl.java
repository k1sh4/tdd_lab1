package org.example;

import java.util.List;

public class CompanyServiceImpl implements ICompanyService {
    @Override
    public Company getTopLevelParent(Company child) {
        if (child == null) {
            return null;
        }
        if (child.getParent() == null) {
            return child;
        }
        while (child.getParent() != null) {
            child = child.getParent();
        }
        return child;
    }

    @Override
    public long getEmployeeCountForCompanyAndChildren(Company company, List<Company> companies) {
        if (company == null || companies.isEmpty()) {
            return 0;
        }
            long count = NumberOfEmployee(company.getEmployeesCount(), company, companies);
            return count;
    }

    private long NumberOfEmployee(long number, Company company, List<Company> companies) {
        for (Company child : companies) {
            if (child.getParent() == company) {
                number += child.getEmployeesCount() + NumberOfEmployee(0, child, companies);
            }
        }
        return number;
    }


}
