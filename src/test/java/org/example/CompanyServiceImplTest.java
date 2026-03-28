package org.example;
import org.junit.jupiter.api.*;

import java.util.List;

class CompanyServiceImplTest {

    private final Company main = new Company(null,2);
    private final Company book = new Company(main,3);
    private final Company manager = new Company(main,4);
    private final Company developer = new Company(manager,8);
    private final Company design = new Company(manager,6);
    private final Company lawer = new Company(null,1);
    private final Company cleaner = new Company(null,0);
    private final Company dev_assistants = new Company(developer,5);
    private final Company book_assistants = new Company(book,2);


    private final List<Company> companyList = List.of(main, book, manager, developer, design, lawer, cleaner, dev_assistants, book_assistants);
    private final ICompanyService companyService = new CompanyServiceImpl();

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void whenCompanyHasNoParentItIsOnTop() {
        Company result = companyService.getTopLevelParent(main);
        Assertions.assertEquals(main, result);
    }

    @Test
    void whenCompanyIsSingleItIsOnTop() {
        Company result = companyService.getTopLevelParent(lawer);
        Assertions.assertEquals(lawer, result);
    }

    @Test
    void whenCompanyHasOneStepToTheTopThenFindTop() {
        Company result = companyService.getTopLevelParent(book);
        Assertions.assertEquals(main, result);
    }

    @Test
    void whenCompanyHasTwoStepsToTheTopThenFindTop() {
        Company result = companyService.getTopLevelParent(developer);
        Assertions.assertEquals(main, result);
    }

    @Test
    void whenCompanyIsNullThenNull() {
        Company result = companyService.getTopLevelParent(null);
        Assertions.assertNull(result);
    }

     //-=============================================================================-

    @Test
    void whenEmployeeIsNullThenNull() {
        long count = companyService.getEmployeeCountForCompanyAndChildren(cleaner, companyList);
        Assertions.assertEquals(0, count);
    }

    @Test
    void whenCompanyIsMain() {
        long count = companyService.getEmployeeCountForCompanyAndChildren(main, companyList);
        Assertions.assertEquals(30, count);
    }

    @Test
    void whenCompanyIsMainAndDontHasChildren() {
        long count = companyService.getEmployeeCountForCompanyAndChildren(lawer, companyList);
        Assertions.assertEquals(1, count);
    }

    @Test
    void whenCompanyHasChildrenAndGrandchildren() {
        long count = companyService.getEmployeeCountForCompanyAndChildren(manager, companyList);
        Assertions.assertEquals(23, count);
    }

    @Test
    void whenCompanyDevHasOnlyChild() {
        long count = companyService.getEmployeeCountForCompanyAndChildren(developer, companyList);
        Assertions.assertEquals(13, count);
    }
    @Test
    void whenCompanyBookHasOnlyChild() {
        long count = companyService.getEmployeeCountForCompanyAndChildren(book, companyList);
        Assertions.assertEquals(5, count);
    }

    @Test
    void whenCompanyDontHasChildren() {
        long count = companyService.getEmployeeCountForCompanyAndChildren(design, companyList);
        Assertions.assertEquals(6, count);
    }

    @Test
    void whenCompanyIsNullThenZero() {
        long count = companyService.getEmployeeCountForCompanyAndChildren(null, companyList);
        Assertions.assertEquals(0, count);
    }

    @Test
    void whenListIsEmptyThenZero() {
        long count = companyService.getEmployeeCountForCompanyAndChildren(book, List.of());
        Assertions.assertEquals(0, count);
    }

    @Test
    void whenAllNullThenZero() {
        long count = companyService.getEmployeeCountForCompanyAndChildren(null, null);
        Assertions.assertEquals(0, count);
    }
}