/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nthn.coffeemanagementapp;

import com.nthn.configs.Utils;
import com.nthn.pojo.*;
import com.nthn.services.EmployeeService;
import com.nthn.services.ProductService;
import com.nthn.services.TableService;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.MenuButton;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;

/**
 * FXML Controller class
 *
 * @author PC
 */
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
    private  TextField txtUsername;

    @FXML
    private DatePicker datePicker;

    private final ProductController pc = new ProductController();

    private final TableController tc = new TableController();

    private final TableService ts = new TableService();

    private final ProductService ps = new ProductService();


    private final EmployeeController ec = new EmployeeController();
    private final EmployeeService es = new EmployeeService();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO

        this.pc.loadTableViewProduct(tbvProduct);
        this.tc.loadTableViewTable(tbvTable);
        this.ec.loadTableViewEmployee(tbvEmployee);

        try {
            this.pc.loadComboBoxDataProduct(cbProduct);
            this.pc.loadChoiceBoxCategory(chbCategory);
            this.tc.loadComboBoxDataCapacity(cbCapacity);
            this.tc.loadComboBoxDataStatus(cbStatus);
            this.pc.loadTableDataProduct(null, tbvProduct, cbProduct);
            this.tc.loadTableDataTable(null, tbvTable);

            this.ec.loadTableDataEmployee(tbvEmployee);
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

        this.txtUsername.textProperty().addListener((observable, oldValue, newValue) -> {
            this.ec.loadTableDataEmployee(tbvEmployee,newValue);
        });
    }

    @FXML
    private void ResetComboBox(ActionEvent event) throws SQLException {
        this.cbCapacity.valueProperty().set(null);
        this.cbStatus.valueProperty().set(null);
        this.tc.loadComboBoxDataCapacity(cbCapacity);
        this.tc.loadComboBoxDataStatus(cbStatus);
        this.tc.loadTableDataTable(null, tbvTable);
    }

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
        try {
            ps.addProduct(product);
        } catch (SQLException ex) {
            Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
        }

        if (ps.getProduct(product.getProductID()) != null) {
            Utils.showAlert(Alert.AlertType.INFORMATION, "Add Product Success", "Thêm sản phẩm thành công.");

            try {
                this.pc.loadTableDataProduct("", tbvProduct, cbProduct);
            } catch (SQLException ex) {
                Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
            }

            this.txtName.clear();
            this.txtPrice.clear();
        } else {
            Utils.showAlert(Alert.AlertType.ERROR, "Add Product Failed", "Thêm sản phẩm thất bại.");
        }
    }

    //Chưa hoàn thành
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

    //Chưa hoàn thành
    public void btnEditPriceHandler(ActionEvent event) throws SQLException {
        Product product = this.tbvProduct.getSelectionModel().getSelectedItem();
        Product product1 = ps.getProduct(product.getProductID());
        product.setCategory(product1.getCategory());

        product.viewDetail();
        TextInputDialog dialog = new TextInputDialog();
        dialog.setHeaderText("Nhập giá tiền (VNĐ): ");
        Optional<String> optional = dialog.showAndWait();
        if (optional.isPresent()) {
            product.setUnitPrice(Integer.parseInt(optional.get()));
            ps.updateProduct(product);

            Utils.showAlert(Alert.AlertType.INFORMATION, "Edit Product Success", "Giá mới đã được cập nhật.");
            this.pc.loadTableDataProduct("", tbvProduct, cbProduct);
        } else {
            Utils.showAlert(Alert.AlertType.INFORMATION, "Edit Product Failed", "Giá chưa được cập nhật.");
        }
    }

    public void btnDeleteProductHandler(ActionEvent event) throws SQLException {
        Product product = this.tbvProduct.getSelectionModel().getSelectedItem();
        ps.deleteProduct(product.getProductID());

        if (ps.getProduct(product.getProductID()) == null) {
            Utils.showAlert(Alert.AlertType.INFORMATION, "Delete Product Success", "Sản phẩm " + product.getProductName() + " đã xóa.");

            try {
                this.pc.loadTableDataProduct("", tbvProduct, cbProduct);
            } catch (SQLException ex) {
                Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            Utils.showAlert(Alert.AlertType.ERROR, "Delete Product Failed", "Sản phẩm " + product.getProductName() + " chưa xóa.");
        }
    }

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

    //Chưa hoàn thành
    public void btnEditNameTable(ActionEvent event) {
        Table table = this.tbvTable.getSelectionModel().getSelectedItem();
//        List<Table> listTable = this.tc.tables;
//
//        listTable.forEach((t) -> {
//            if (t.getTableName().equals(table.getTableName()) && t.getCapacity() == table.getCapacity()) {
//
//                table.setStatus(t.getStatus());
//                table.setTableID(t.getTableID());
//            }
//
//        });

        TextInputDialog dialog = new TextInputDialog();
        dialog.setHeaderText("Nhập tên bàn: ");
        Optional<String> optional = dialog.showAndWait();
        if (optional.isPresent()) {
            table.setTableName(optional.get());
            try {
                ts.updateTable(table);
            } catch (SQLException ex) {
                Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
            }
            Utils.showAlert(Alert.AlertType.INFORMATION, "Edit Table Success", "Chỉnh sửa thành công.");
            try {
                this.tc.loadTableDataTable("", tbvTable);
            } catch (SQLException ex) {
                Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            Utils.showAlert(Alert.AlertType.INFORMATION, "Edit Table Failed", "Chỉnh sửa thất bại.");
        }
    }

    public void btnDeleteTableHandler(ActionEvent event) throws SQLException {
        Table table = this.tbvTable.getSelectionModel().getSelectedItem();
        ts.deleteTable(table.getTableID());

        if (ts.getTable(table.getTableID()) == null) {
            Utils.showAlert(Alert.AlertType.INFORMATION, "Delete Table Success", "Bàn " + table.getTableName() + " đã xóa.");
            this.tc.loadTableDataTable("", tbvTable);
        } else {
            Utils.showAlert(Alert.AlertType.INFORMATION, "Delete Table Failed", "Bàn " + table.getTableName() + " chưa xóa.");
        }
    }

    public void btnAddEmployeeHandler(ActionEvent event) throws IOException {
        App app = new App();
        app.loaderController("Register.fxml", "Coffee Management App - Register");
        this.ec.loadTableDataEmployee(tbvEmployee);
    }


    public void btnDeleteEmployeeHandler(ActionEvent event) {
        Employee employee = this.tbvEmployee.getSelectionModel().getSelectedItem();
        es.deleteEmployee(employee.getEmployeeID(), employee.getAccount().getAccountID());

        if (es.getEmployeeByID(employee.getEmployeeID())== null) {
            Utils.showAlert(Alert.AlertType.INFORMATION, "Delete Employee Success", "Nhân viên " + employee.getFullName()
                    + " đã xóa.");
            this.ec.loadTableDataEmployee(tbvEmployee);
        } else {
            Utils.showAlert(Alert.AlertType.INFORMATION, "Delete Employee Failed", "Nhân viên " + employee.getFullName()
                    + " chưa xóa.");
        }
    }
}
