package gui;

import java.net.URL;
import java.util.ResourceBundle;

import db.DbException;
import gui.util.Alerts;
import gui.util.Constraints;
import gui.util.utils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import model.entities.Department;
import model.services.DepartmentService;

public class DepartmentFormController implements Initializable {

	private Department entity;
	
	private DepartmentService service;

	@FXML
	private TextField txtId;

	@FXML
	private TextField txtName;

	@FXML
	private Label labelErrorName;

	@FXML
	private Button btSave;

	@FXML
	private Button btCancel;

	@FXML
	public void onBtSaveAction(ActionEvent event) {
		if(entity == null) {
		
			throw new IllegalStateException("Entity was null!");
			
		} else if (service == null) {
			
			throw new IllegalStateException("Service was null!");
			
		}
		try {
		entity = getFormData();
		service.saverOrUpdate(entity);
		utils.currentState(event).close();
		
		}catch (DbException error) {
			
			Alerts.showAlert("Error saving object", null, error.getMessage(), AlertType.ERROR);
		}
	}

	private Department getFormData() {
		Department obj = new Department();
		obj.setId(utils.tryParseToTint(txtId.getText()));
		obj.setName(txtName.getText());
		
		
		return obj;
	}

	public void setDepartment(Department entity) {

		this.entity = entity;
	}
	
	public void setDepartmentService(DepartmentService service ) {
		this.service = service;
		
	}

	@FXML
	public void onBtCancelAction(ActionEvent event) {
		
		utils.currentState(event).close();

	}

	@Override
	public void initialize(URL url, ResourceBundle rb) {
		initializaNodes();
	}

	private void initializaNodes() {

		Constraints.setTextFieldInteger(txtId);
		Constraints.setTextFieldMaxLength(txtName, 30);
	}

	public void updateFormData() {
		if (entity == null) {
			throw new IllegalStateException("Entity was null!");
		}
		txtId.setText(String.valueOf(entity.getId()));
		txtName.setText(entity.getName());
	}
}
