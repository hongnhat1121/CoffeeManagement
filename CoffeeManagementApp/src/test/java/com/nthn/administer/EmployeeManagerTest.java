package com.nthn.administer;

import com.nthn.configs.Utils;
import com.nthn.pojo.Account;
import com.nthn.pojo.Employee;
import com.nthn.pojo.Gender;
import com.nthn.services.AccountService;
import com.nthn.services.EmployeeService;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

/**
 *
 * @author HONGNHAT
 */
public class EmployeeManagerTest {

    private EmployeeService service = new EmployeeService();

    @ParameterizedTest(name = "{index} => employee={0}, account={1}")
    @CsvSource({"cc77cadd-96ce-4a61-beee-831474b4cd84, 2c577442-b501-406d-a48b-6fac14941b4c"})
    public void testDeleteEmployeeSuccess(String employee, String account) {
        try {
            service.deleteEmployee(employee, account);
            Employee employee1 = service.getEmployeeByID(employee);

            AccountService as = new AccountService();
            Account a = as.getAccountByID(account);

            Assertions.assertNull(employee1);
            Assertions.assertNull(a);
        } catch (SQLException ex) {
            Logger.getLogger(TableManagerTester.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @ParameterizedTest(name = "{index} => id={0}, name={1}, birthDate={2}, "
            + "gender={3}, identityCard={4}, address={5}, phone={6}, hireDate={7}, accountID={8}")
    @CsvSource({"c9491382-ree5-4920-ab42-c04fed63bc45, Nguyễn Văn A, 10/02/2003, Nam, 496848458, null, 0875934953, 11/01/2022, 8a110593-f475-49e9-ba06-379f0ed97ea9"})
    public void testAddEmployeeSuccess(String id, String name, String birthDate,
            String gender, String identityCard, String address, String phone,
            String hireDate, String accountID) {
        try {
            Employee employee = new Employee(id, Utils.converter.fromString(hireDate),
                    name, Utils.converter.fromString(birthDate),
                    Gender.getByContent(gender), identityCard, address, phone, accountID);
            service.addEmployee(employee);

            employee = service.getEmployeeByID(id);

            Assertions.assertNotNull(employee);
        } catch (SQLException ex) {
            Logger.getLogger(TableManagerTester.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @ParameterizedTest(name = "{index} => id={0}, name={1}, birthDate={2}, "
            + "gender={3}, identityCard={4}, address={5}, phone={6}, hireDate={7}, accountID={8}")
    @CsvSource({"c9491382-ree5-4920-ab42-c04fed63bc45, Nguyễn Văn A, 10/02/2003, Nam, 496848458, null, 0875934953, 11/01/2022, 8a110593-f475-49e9-ba06-379f0ed97ea9"})
    public void testAddEmployeeFailed(String id, String name, String birthDate,
            String gender, String identityCard, String address, String phone,
            String hireDate, String accountID) {
        try {
            Employee employee = new Employee(id, Utils.converter.fromString(hireDate),
                    name, Utils.converter.fromString(birthDate),
                    Gender.getByContent(gender), identityCard, address, phone, accountID);
            service.addEmployee(employee);

            employee = service.getEmployeeByID(id);

            Assertions.assertNull(employee);
        } catch (SQLException ex) {
            Logger.getLogger(TableManagerTester.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @ParameterizedTest(name = "{index} => id={0}, name={1}, birthDate={2}, "
            + "gender={3}, identityCard={4}, address={5}, phone={6}, hireDate={7}, accountID={8}")
    @CsvSource({"c9491382-ree5-4920-ab42-c04fed63bc45, Nguyễn Văn A, 10/02/2003, Nam, 496848458, null, 0875934953, 11/01/2022, 8a110593-f475-49e9-ba06-379f0ed97ea9"})
    public void testUpdateEmployeeSuccess(String id, String name, String birthDate,
            String gender, String identityCard, String address, String phone,
            String hireDate, String accountID) {
        try {
            Employee employee = service.getEmployeeByID(id);

            Employee employee1 = new Employee(id, Utils.converter.fromString(hireDate),
                    name, Utils.converter.fromString(birthDate),
                    Gender.getByContent(gender), identityCard, address, phone, accountID);

            AccountService as = new AccountService();
            Account a = as.getAccountByID(accountID);

            service.updateEmployee(employee, a);

            employee1 = service.getEmployeeByID(id);

            Assertions.assertNotEquals(employee, employee1);
        } catch (SQLException ex) {
            Logger.getLogger(TableManagerTester.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
