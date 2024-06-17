
package controllers;

import model.Employeemodel;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;



@ManagedBean(name="employeeController" )
@SessionScoped
public class EmployeeController implements Serializable{

 private static final long serialVersionUID = 1L;

    private List<Employeemodel> employees;
    private Employeemodel currentEmployee;
    private boolean editMode;


    public EmployeeController() {

        employees = new ArrayList<>();
        // Add some initial data
        employees.add(new Employeemodel("Thomas Hardy", "thomashardy@mail.com", "89 Chiaroscuro Rd, Portland, USA", "(171) 555-2222"));
        employees.add(new Employeemodel("Dominique Perrier", "dominiqueperrier@mail.com", "Obere Str. 57, Berlin, Germany", "(313) 555-5735"));
        employees.add(new Employeemodel("Maria Anders", "mariaanders@mail.com", "25, rue Lauriston, Paris, France", "(503) 555-9931"));
 currentEmployee = new Employeemodel(); 
    }

     public List<Employeemodel> getEmployees() {
        return employees;
    }

    public Employeemodel getCurrentEmployee() {
        return currentEmployee;
    }

    public void setCurrentEmployee(Employeemodel currentEmployee) {
        this.currentEmployee = currentEmployee;
    }

     public boolean isEditMode() {
        return editMode;
    }

    public void setEditMode(boolean editMode) {
        this.editMode = editMode;
    }


    public void saveEmployee() {
        if (editMode) {
           
            int index = employees.indexOf(currentEmployee);
            employees.set(index, currentEmployee);
            editMode = false;
        } else {
           
            employees.add(new Employeemodel(currentEmployee.getName(), currentEmployee.getEmail(), currentEmployee.getAddress(), currentEmployee.getPhone()));
        }
        currentEmployee = new Employeemodel(); 
    }

    public void addEmployee() {
        employees.add(new Employeemodel(currentEmployee.getName(), currentEmployee.getEmail(), currentEmployee.getAddress(), currentEmployee.getPhone()));
        currentEmployee = new Employeemodel(); 
    }

    public void editEmployee(Employeemodel employee) {
        currentEmployee = employee;
        editMode = true;
    }

    public void updateEmployee() {
        
        currentEmployee = new Employeemodel(); 
        editMode = false;
    }

    public void deleteEmployee(Employeemodel employee) {
        employees.remove(employee);
    }

    public void resetForm() {
        currentEmployee = new Employeemodel();
        editMode = false;
    }

 

}
