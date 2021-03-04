package unitTests;

import com.computerAccessoriesStore.models.Act;
import com.computerAccessoriesStore.models.Product;
import com.computerAccessoriesStore.models.User;
import com.computerAccessoriesStore.models.enums.Role;
import com.computerAccessoriesStore.service.impl.ActServiceImpl;
import com.computerAccessoriesStore.service.impl.ProductServiceImpl;
import com.computerAccessoriesStore.service.impl.UserServiceImpl;
import com.computerAccessoriesStore.transfer.ActDTO;
import com.computerAccessoriesStore.transfer.ProductDTO;
import com.computerAccessoriesStore.transfer.UserDTO;
import org.testng.Assert;
import org.testng.annotations.DataProvider;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

public class Test {

    @DataProvider(name = "user-data-provider")
    public Object[][] userDataProviderMethod() {
        long testId = 112232;
        String testName = "Elizaveta";
        String testSurname = "Alexandrova";
        String testMail = "l3eaver@gmail.com";
        String testUserName = "iElizabeerta";
        String testPassword = "11231243";
        Date testDate = new Date(Calendar.getInstance().getTime().getTime());
        return new Object[][]{
                {
                        testId,
                        testName,
                        testSurname,
                        testMail,
                        testUserName,
                        testPassword,
                        testDate,
                        Role.ROLE_ADMIN
                },
                {
                        testId / 1000,
                        testName.toUpperCase(Locale.ROOT),
                        testSurname.toUpperCase(Locale.ROOT),
                        testMail.toUpperCase(Locale.ROOT),
                        testUserName.toUpperCase(Locale.ROOT),
                        testPassword.toUpperCase(Locale.ROOT),
                        testDate,
                        Role.ROLE_BLOCKED
                }
        };
    }

    @org.testng.annotations.Test(dataProvider = "user-data-provider")
    public void testUserDTOTransformation(long testId, String testName, String testSurname, String testMail, String testUserName, String testPassword, Date testDate, Role role) {
        UserDTO dto = new UserDTO(
                testId,
                testName,
                testSurname,
                testMail,
                testUserName,
                testPassword,
                testDate,
                role
        );
        User testUser = new User(
                testId,
                testName,
                testSurname,
                testMail,
                testUserName,
                testPassword,
                testDate,
                role,
                new ArrayList<>(),
                new ArrayList<>(),
                new ArrayList<>(),
                new ArrayList<>()
        );
        User realUser = new UserServiceImpl()
                .getTransformedObject(dto);
        Assert.assertEquals(realUser, testUser, "Something wrong with users transformation");
    }

    @DataProvider(name = "act-data-provider")
    public Object[][] actDataProviderMethod() {
        long testId = 228133712;
        long testCount = 2;
        Date testDate = new Date(Calendar.getInstance().getTime().getTime());
        return new Object[][]{
                {
                        testId,
                        testCount,
                        testDate,
                },
                {
                        testId / 1000,
                        testCount + 1000000,
                        testDate,
                }
        };
    }

    @org.testng.annotations.Test(dataProvider = "act-data-provider")
    public void testActDTOTransformation(long testId, long testCount, Date testDate) {
        ActDTO dto = new ActDTO(
                testId,
                testCount,
                testDate,
                Long.parseLong("0"),
                Long.parseLong("0"),
                Long.parseLong("0")
        );
        Act testAct = new Act(
                testId,
                testCount,
                testDate,
                new User(
                        Long.parseLong("0"),
                        null,
                        null,
                        null,
                        null,
                        null,
                        null,
                        null,
                        null,
                        null,
                        null,
                        null
                ),
                new User(
                        Long.parseLong("0"),
                        null,
                        null,
                        null,
                        null,
                        null,
                        null,
                        null,
                        null,
                        null,
                        null,
                        null
                ),
                new Product(
                        Long.parseLong("0"),
                        null,
                        null,
                        null,
                        null
                )
        );
        Act realAct = new ActServiceImpl()
                .getTransformedObject(dto);
        Assert.assertEquals(realAct, testAct, "Something wrong with act transfornation");
    }

    @DataProvider(name = "product-data-provider")
    public Object[][] productDataProviderMethod() {
        String testName = "IPhone";
        float testCoast = 1243124;
        return new Object[][]{
                {
                        testName.toUpperCase(Locale.ROOT),
                        testCoast,
                },
                {
                        testName,
                        testCoast * 123,
                }
        };
    }

    @org.testng.annotations.Test(dataProvider = "product-data-provider")
    public void testProductDTOTransformation(String testName, float testCoast) {
        ProductDTO dto = new ProductDTO(
                null,
                testName,
                testCoast,
                Long.parseLong("0")
        );
        Product testProduct = new Product(
                null,
                testCoast,
                testName,
                new User(Long.parseLong("0"),
                        null,
                        null,
                        null,
                        null,
                        null,
                        null,
                        null,
                        null,
                        null,
                        null,
                        null
                ),
                null);
        Product realProduct = new ProductServiceImpl()
                .getTransformedObject(dto);
        Assert.assertEquals(realProduct, testProduct, "Something wrong with product transfornation");
    }
}
