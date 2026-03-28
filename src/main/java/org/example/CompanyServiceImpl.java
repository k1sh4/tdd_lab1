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
            return NumberOfEmployee(company.getEmployeesCount(), company, companies);
    }

    private long NumberOfEmployee(long count, Company company, List<Company> companies) {
        for (Company child : companies) {
            if (child.getParent() == company) {
                count += child.getEmployeesCount() + NumberOfEmployee(0, child, companies);
            }
        }
        return count;
    }


}
