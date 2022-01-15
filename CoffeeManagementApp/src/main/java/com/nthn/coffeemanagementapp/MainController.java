/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nthn.coffeemanagementapp;

import com.nthn.check.RegisterChecker;
import com.nthn.configs.Utils;
import com.nthn.pojo.*;
import com.nthn.services.*;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

public class MainController implements Initializable {
    @FXML
    private TextField tfProduct;
    @FXML
    private ComboBox cbProduct;
    @FXML
    private TableView<Product> tbvProduct;
    @FXML
    private TextField txtName;
    @FXML
    private TextField txtPrice;
    @FXML
    private ChoiceBox<Category> chbCategory;
    @FXML
    private Button btnAdd;
    @FXML
    private MenuButton btnEdit;
    @FXML
    private Button btnDelete;

    @FXML
    private TextField tfTable;
    @FXML
    private ComboBox<Integer> cbCapacity;
    @FXML
    private ComboBox cbStatus;
    @FXML
    private TableView<Table> tbvTable;
    @FXML
    private TextField txtTableName;
    @FXML
    private TextField txtCapacity;
    @FXML
    private Button btnAddTable;
    @FXML
    private Button btnEditTable;
    @FXML
    private Button btnDeleteTable;

    @FXML
    private TableView<Employee> tbvEmployee;
    @FXML
    private TextField txtUsername;
    @FXML
    private ComboBox<String> cbOptional;

    @FXML
    private TableView<Order> tbvOrder;
    @FXML
    private ComboBox<String> cbPayment;
    @FXML
    private DatePicker dpOrderDate;
    @FXML
    private Button btnPayment;
    @FXML
    private TextField txtTable;
    @FXML
    private Button btnFilterOrderDate;
    @FXML
    private Button btnOrder;

    private final ProductController pc = new ProductController();
    private final TableController tc = new TableController();
    private final EmployeeController ec = new EmployeeController();
    private final StatisticController sc = new StatisticController();

    private final ProductService ps = new ProductService();
    private final TableService ts = new TableService();
    private final EmployeeService es = new EmployeeService();
    private final AccountService as = new AccountService();
    private final OrderService os = new OrderService();
    private final OrderDetailService ods = new OrderDetailService();


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.dpOrderDate.setConverter(Utils.converter);
        this.dpOrderDate.setValue(LocalDate.now());

        this.pc.loadTableViewProduct(tbvProduct);
        this.tc.loadTableViewTable(tbvTable);
        this.ec.loadTableViewEmployee(tbvEmployee);
        this.sc.loadTableViewOrder(tbvOrder);

        try {
            this.pc.loadComboBoxDataProduct(cbProduct);
            this.pc.loadChoiceBoxCategory(chbCategory);
            this.tc.loadComboBoxDataCapacity(cbCapacity);
            this.tc.loadComboBoxDataStatus(cbStatus);
            this.pc.loadTableDataProduct(null, tbvProduct, cbProduct);
            this.tc.loadTableDataTable(null, tbvTable);

            this.ec.loadComboBoxOptional(cbOptional);
            this.ec.loadTableDataEmployee(tbvEmployee);

            this.sc.loadTableDataOrder(tbvOrder);
        } catch (SQLException ex) {
            Logger.getLogger(ProductController.class.getName()).log(Level.SEVERE, null, ex);
        }

        this.tfProduct.textProperty().addListener((evt) -> {
            try {
                this.pc.loadTableDataProduct(this.tfProduct.getText(), tbvProduct, cbProduct);
            } catch (SQLException ex) {
                Logger.getLogger(ProductController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });

        this.tfTable.textProperty().addListener((evt) -> {
            try {
                this.tc.loadTableDataTable(this.tfTable.getText(), tbvTable);
            } catch (SQLException ex) {
                Logger.getLogger(ProductController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });

        this.cbCapacity.getSelectionModel().selectedItemProperty().addListener((ov, t, t1) -> {
            this.cbCapacity.setValue(t1);
            try {
                this.tc.loadTableDateTable1(tbvTable, cbCapacity, cbStatus);
            } catch (SQLException ex) {
                Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });

        this.cbStatus.getSelectionModel().selectedItemProperty().addListener((evt) -> {
            try {
                this.tc.loadTableDateTable1(tbvTable, cbCapacity, cbStatus);
            } catch (SQLException ex) {
                Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });

        this.cbOptional.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            this.cbOptional.valueProperty().set(newValue);

        });
        this.txtUsername.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.isBlank())
                try {
                    this.ec.loadTableDataEmployee(tbvEmployee, cbOptional, newValue);
                } catch (SQLException ex) {
                    Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
                }
            else this.ec.loadTableDataEmployee(tbvEmployee);
        });

        this.txtTable.textProperty().addListener((observable, oldValue, newValue) -> {
            this.sc.loadTableDataOrder(tbvOrder, newValue);
        });
    }

    @FXML
    public void ResetComboBox(ActionEvent event) throws SQLException {
        this.cbCapacity.valueProperty().set(null);
        this.cbStatus.valueProperty().set(null);
        this.tc.loadComboBoxDataCapacity(cbCapacity);
        this.tc.loadComboBoxDataStatus(cbStatus);
        this.tc.loadTableDataTable(null, tbvTable);
    }

    //Tab product
    public void btnAddProductHandler(ActionEvent event) throws SQLException {
        if (txtName.getText().isBlank() && txtPrice.getText().isBlank()) {
            Utils.showAlert(Alert.AlertType.ERROR, "Add Product Error!",
                    "Vui lòng nhập tên sản phẩm và giá tiền!");
            return;
        }
        if (txtName.getText().isBlank()) {
            Utils.showAlert(Alert.AlertType.ERROR, "Add Product Error!",
                    "Vui lòng nhập tên sản phẩm!");
            return;
        }
        if (txtName.getText().matches(".*[!@#$%^&*()].*")) {
            Utils.showAlert(Alert.AlertType.ERROR, "Add Product Error!",
                    "Tên sản phẩm có chứa ký tự đặc biệt. Nhập lại!");
            return;
        }
        if (txtName.getText().matches(".*[0-9].*")) {
            Utils.showAlert(Alert.AlertType.ERROR, "Add Product Error!",
                    "Tên sản phẩm có chứa chữ số. Nhập lại!");
            return;
        }
        if (txtPrice.getText().isBlank()) {
            Utils.showAlert(Alert.AlertType.ERROR, "Add Product Error!",
                    "Vui lòng nhập giá tiền!");
        } else if (txtPrice.getText().matches(".*\\W.*")) {
            Utils.showAlert(Alert.AlertType.ERROR, "Add Product Error!",
                    "Giá tiền chứa ký tự đặc biệt!");
        } else if (txtPrice.getText().matches(".*[a-zA-Z].*")) {
            Utils.showAlert(Alert.AlertType.ERROR, "Add Product Error!",
                    "Giá tiền chứa ký tự chữ cái!");
        }

        String productName = txtName.getText();
        int unitPrice = Integer.parseInt(txtPrice.getText());
        Category category = chbCategory.getValue();
        Product product = new Product(Utils.randomID(), productName, unitPrice, category);

        ProductService ps = new ProductService();

        //Nếu tên sản phẩm chưa có thì thêm sản phẩm, tải lại danh sách sản phẩm, ngược lại báo lỗi
        if (ps.getProductsByName(product.getProductName()) == null) {
            ps.addProduct(product);
            Utils.showAlert(Alert.AlertType.INFORMATION, "Add Product Success", "Thêm sản phẩm thành công.");
            this.pc.loadTableDataProduct("", tbvProduct, cbProduct);

            this.txtName.clear();
            this.txtPrice.clear();
        } else {
            Utils.showAlert(Alert.AlertType.ERROR, "Add Product Failed", "Sản phẩm đã tồn tại. Nhập lại!");
            this.txtName.clear();
        }
    }

    public void btnEditNameHandler(ActionEvent event) throws SQLException {
        Product product = this.tbvProduct.getSelectionModel().getSelectedItem();
        Product product1 = ps.getProduct(product.getProductID());
        product.setCategory(product1.getCategory());

        TextInputDialog dialog = new TextInputDialog();
        dialog.setHeaderText("Nhập tên sản phẩm: ");
        Optional<String> optional = dialog.showAndWait();
        if (optional.isPresent()) {
            product.setProductName(optional.get());
            ps.updateProduct(product);
            Utils.showAlert(Alert.AlertType.INFORMATION, "Edit Product Success", "Chỉnh sửa thành công.");
            this.pc.loadTableDataProduct("", tbvProduct, cbProduct);
        } else {
            Utils.showAlert(Alert.AlertType.INFORMATION, "Edit Product Failed", "Chỉnh sửa thành công.");
        }
    }

    public void btnEditPriceHandler(ActionEvent event) throws SQLException {
        Product product = this.tbvProduct.getSelectionModel().getSelectedItem();
        Product product1 = ps.getProduct(product.getProductID());
        product.setCategory(product1.getCategory());


        TextInputDialog dialog = new TextInputDialog();
        dialog.setHeaderText("Nhập giá tiền (VNĐ): ");
        Optional<String> optional = dialog.showAndWait();
        if (optional.isPresent()) {
            if (optional.get().isBlank()) {
                Utils.showAlert(Alert.AlertType.ERROR, "Edit Product Error", "Chưa nhập giá. Thử lại!");
                return;
            }
            product.setUnitPrice(Integer.parseInt(optional.get()));
            ps.updateProduct(product);

            Utils.showAlert(Alert.AlertType.INFORMATION, "Edit Product Success", "Giá mới đã được cập nhật.");
            this.pc.loadTableDataProduct("", tbvProduct, cbProduct);
        }
    }

    public void btnDeleteProductHandler(ActionEvent event) throws SQLException {
        Product product = this.tbvProduct.getSelectionModel().getSelectedItem();
        if (product == null) {
            Utils.showAlert(Alert.AlertType.ERROR, "Delete Product Error!", "Vui lòng chọn sản phẩm cần xóa!");
            return;
        }

        ps.deleteProduct(product.getProductID());
        if (ps.getProduct(product.getProductID()) == null) {
            Utils.showAlert(Alert.AlertType.INFORMATION, "Delete Product Success", "Sản phẩm " + product.getProductName() + " đã xóa.");
            this.pc.loadTableDataProduct("", tbvProduct, cbProduct);
        } else {
            Utils.showAlert(Alert.AlertType.ERROR, "Delete Product Failed", "Sản phẩm " + product.getProductName() + " chưa xóa.");
        }
    }

    //Tab table
    public void btnAddTableHandler(ActionEvent event) throws SQLException {
        if (txtTableName.getText().isBlank() && txtCapacity.getText().isBlank()) {
            Utils.showAlert(Alert.AlertType.ERROR, "Add Table Error!",
                    "Vui lòng nhập tên bàn và sức chứa!");
            return;
        }
        if (txtTableName.getText().isBlank()) {
            Utils.showAlert(Alert.AlertType.ERROR, "Add Table Error!",
                    "Vui lòng nhập tên bàn!");
            return;
        }
        if (txtCapacity.getText().isBlank()) {
            Utils.showAlert(Alert.AlertType.ERROR, "Add Table Error!",
                    "Vui lòng nhập sức chứa!");
        } else if (txtCapacity.getText().matches(".*\\W.*")) {
            Utils.showAlert(Alert.AlertType.ERROR, "Add Table Error!!",
                    "Sức chứa chứa ký tự đặc biệt!");
        } else if (txtCapacity.getText().matches(".*[a-zA-Z].*")) {
            Utils.showAlert(Alert.AlertType.ERROR, "Add Table Error!",
                    "Sức chứa chứa ký tự chữ cái!");
        }

        String tableName = txtTableName.getText();
        int capacity = Integer.parseInt(txtCapacity.getText());
        Table table = new Table(Utils.randomID(), tableName, capacity, Status.EMPTY);

        ts.addTable(table);

        if (ts.getTable(table.getTableID()) != null) {
            Utils.showAlert(Alert.AlertType.INFORMATION, "Add Table Success", "Thêm bàn thành công.");

            try {
                this.tc.loadTableDataTable("", tbvTable);
            } catch (SQLException ex) {
                Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
            }

            this.txtTableName.clear();
            this.txtCapacity.clear();
        } else {
            Utils.showAlert(Alert.AlertType.ERROR, "Add Table Failed", "Thêm bàn thất bại.");
        }
    }

    public void btnEditNameTable(ActionEvent event) throws SQLException {
        Table table = this.tbvTable.getSelectionModel().getSelectedItem();
        if (table == null) {
            Utils.showAlert(Alert.AlertType.ERROR, "Edit Table Error!", "Vui lòng chọn bàn cần chỉnh sửa!");
            return;
        }

        Table table1 = ts.getTable(table.getTableID());

        TextInputDialog dialog = new TextInputDialog();
        dialog.setHeaderText("Nhập tên bàn: ");
        Optional<String> optional = dialog.showAndWait();
        String newName;
        if (optional.isPresent()) {
            newName = optional.get();

            if (newName.isBlank()) {
                Utils.showAlert(Alert.AlertType.ERROR, "Edit Table Error", "Chưa nhập tên bàn. Thử lại!");
                return;
            }
            if (ts.getTablesByName(newName).isEmpty()) {
                table.setTableName(newName);
                ts.updateTable(table);
                Utils.showAlert(Alert.AlertType.INFORMATION, "Edit Table Success", "Chỉnh sửa thành công.");
                this.tc.loadTableDataTable("", tbvTable);
            } else {
                Utils.showAlert(Alert.AlertType.ERROR, "Edit Table Failed", "Tên bàn bị trùng. Thử lại!");
            }
        }
    }

    public void btnEditCapacityTable(ActionEvent event) throws SQLException {
        Table table = this.tbvTable.getSelectionModel().getSelectedItem();
        if (table == null) {
            Utils.showAlert(Alert.AlertType.ERROR, "Edit Table Error!", "Vui lòng chọn bàn cần chỉnh sửa!");
            return;
        }

        Table table1 = ts.getTable(table.getTableID());

        TextInputDialog dialog = new TextInputDialog();
        dialog.setHeaderText("Nhập sức chứa: ");
        Optional<String> optional = dialog.showAndWait();

        if (optional.isPresent()) {
            if (optional.get().isBlank()) {
                Utils.showAlert(Alert.AlertType.ERROR, "Edit Table Error", "Chưa nhập sứa chứa. Thử lại!");
                return;
            }

            int newCapacity = Integer.parseInt(optional.get());
            if (newCapacity == 0) {
                Utils.showAlert(Alert.AlertType.ERROR, "Edit Table Error", "Sức chứa phải lớn hơn 0. Thử lại!");
                return;
            }

            table.setCapacity(newCapacity);
            ts.updateTable(table);
            Utils.showAlert(Alert.AlertType.INFORMATION, "Edit Table Success", "Chỉnh sửa thành công.");
            this.tc.loadTableDataTable("", tbvTable);
        }
    }

    public void btnDeleteTableHandler(ActionEvent event) throws SQLException {
        Table table = this.tbvTable.getSelectionModel().getSelectedItem();
        if (table == null || table.getStatus() == Status.FULL) {
            Utils.showAlert(Alert.AlertType.ERROR, "Delete Table Error!", "Vui lòng chọn bàn cần xóa!");
            return;
        }
        ts.deleteTable(table.getTableID());

        if (ts.getTable(table.getTableID()) == null) {
            Utils.showAlert(Alert.AlertType.INFORMATION, "Delete Table Success", "Bàn " + table.getTableName() + " đã xóa.");
            this.tc.loadTableDataTable("", tbvTable);
        } else {
            Utils.showAlert(Alert.AlertType.INFORMATION, "Delete Table Failed", "Bàn " + table.getTableName() + " chưa xóa.");
        }
    }

    //Tab employee
    public void btnAddEmployeeHandler(ActionEvent event) {
        App app = new App();
        app.loaderController("Register.fxml", "Coffee Management App - Register");
        this.ec.loadTableDataEmployee(tbvEmployee);
    }

    public void btnEditAddressHandler(ActionEvent event) throws SQLException {
        Employee employee = this.tbvEmployee.getSelectionModel().getSelectedItem();
        if (employee == null) {
            Utils.showAlert(Alert.AlertType.ERROR, "Edit Address Error!", "Vui lòng chọn nhân viên cần chỉnh sửa!");
            return;
        }

        TextInputDialog dialog = new TextInputDialog();
        dialog.setHeaderText("Nhập địa chỉ: ");
        Optional<String> optional = dialog.showAndWait();
        if (optional.isPresent()) {
            if (optional.get().isBlank()) {
                Utils.showAlert(Alert.AlertType.ERROR, "Edit Address Failed!", "Chưa nhập địa chỉ. Thử lại!");
                return;
            }

            employee.setAddress(optional.get());
            es.updateEmployee(employee, employee.getAccount());
            this.ec.loadTableDataEmployee(tbvEmployee);
            Utils.showAlert(Alert.AlertType.INFORMATION, "Edit Address Success!", "Chỉnh sửa thành công.");
        }
    }

    public void btnEditFullNameHandler(ActionEvent event) throws SQLException {
        Employee employee = this.tbvEmployee.getSelectionModel().getSelectedItem();
        if (employee == null) {
            Utils.showAlert(Alert.AlertType.ERROR, "Edit Full Name Error!", "Vui lòng chọn nhân viên cần chỉnh sửa!");
            return;
        }

        TextInputDialog dialog = new TextInputDialog();
        dialog.setHeaderText("Nhập họ tên: ");
        Optional<String> optional = dialog.showAndWait();
        if (optional.isPresent()) {
            if (optional.get().isBlank()) {
                Utils.showAlert(Alert.AlertType.ERROR, "Edit Full Name Error!", "Chưa nhập họ tên. Thử lại!");
                return;
            }

            String fullname = optional.get();
            if (fullname.matches(".*[0-9].*")) {
                Utils.showAlert(Alert.AlertType.ERROR, "Edit Full Name Error!", "Họ tên có chứa chữ số. Thử lại!");
                return;
            } else {
                employee.setFullName(fullname);
                es.updateEmployee(employee, employee.getAccount());
                this.ec.loadTableDataEmployee(tbvEmployee);
                Utils.showAlert(Alert.AlertType.INFORMATION, "Edit Full Name Success!", "Chỉnh sửa thành công.");
            }
        }
    }

    public void btnDeleteEmployeeHandler(ActionEvent event) {
        Employee employee = this.tbvEmployee.getSelectionModel().getSelectedItem();
        if (employee == null) {
            Utils.showAlert(Alert.AlertType.ERROR, "Delete Employee Error!", "Vui lòng chọn nhân viên cần xóa!");
            return;
        }
        es.deleteEmployee(employee.getEmployeeID(), employee.getAccount().getAccountID());
        if (es.getEmployeeByID(employee.getEmployeeID()) == null) {
            Utils.showAlert(Alert.AlertType.INFORMATION, "Delete Employee Success", "Nhân viên " + employee.getFullName()
                    + " đã xóa.");
            this.ec.loadTableDataEmployee(tbvEmployee);
        } else {
            Utils.showAlert(Alert.AlertType.INFORMATION, "Delete Employee Failed", "Nhân viên " + employee.getFullName()
                    + " chưa xóa.");
        }
    }


    //Tab order
    public void btnPaymentHandler(ActionEvent event) {
        Order order = tbvOrder.getSelectionModel().getSelectedItem();
        this.os.updateOrder(order);
        Table table = ts.getTable(order.getTableID());
        table.setStatus(Status.EMPTY);

        ts.updateTable(table);

        Utils.showAlert(Alert.AlertType.INFORMATION, "Payment Success", "Hóa đơn của bàn " + table.getTableName() + " đã được thanh toán.");
        this.sc.loadTableDataOrder(tbvOrder);
    }

    private LocalDate getLocalDate(DatePicker datePicker) {
        TextField textField = datePicker.getEditor();
        String date = textField.getText();
        LocalDate result = Utils.converter.fromString(date);
        return result;
    }

    public void btnFilterOrderDateHandler(ActionEvent event) {
        LocalDate localDate = this.getLocalDate(this.dpOrderDate);
        this.sc.loadTableDataOrder(tbvOrder, localDate);
    }

    public void btnOrderHandler(ActionEvent event) {
        App app = new App();
        app.loaderController("Register.fxml", "Create an account");
    }
}
