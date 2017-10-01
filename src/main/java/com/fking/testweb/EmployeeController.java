/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fking.testweb;

import com.fking.Employee;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

/**
 *
 * @author Dell
 */
@ViewScoped
@ManagedBean(name = "employeeController")
public class EmployeeController implements Serializable {

    List<Employee> employees, selectedEmployees;
    LazyDataModel lazyDataModel;
    boolean selectAll;

    @PostConstruct
    public void init() {
        System.out.println("PostConstruct");
        employees = new ArrayList<Employee>();
        for (int i = 0; i < 20; i++) {
            Employee employee = new Employee();
            employee.setId(i);
            employee.setName("Employee" + i);
            employees.add(employee);
        }
    }

    public void search() {
        lazyDataModel = new LazyDataModel<Employee>() {

            @Override
            public Employee getRowData(String rowKey) {
                for(Employee e: employees){
                    try {
                        if (e.getId() == Integer.parseInt(rowKey)) {
                            return e;
                        }
                    } catch (NumberFormatException numberFormatException) {
                    }
                }
                return null;
            }
            
            
            @Override
            public List<Employee> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, Object> filters) {
                setRowCount(20);
                if (20 > pageSize) {
                    try {
                        return employees.subList(first, first + pageSize);
                    } catch (IndexOutOfBoundsException e) {
                        return employees.subList(first, first + (20 % pageSize));
                    }
                } else {
                    return employees;
                }

            }

        };
    }

    public void selectAllAction(){
        System.out.println("selectAllAction called.");
    }
    public void save() {
        System.out.println("selected employees: "+selectedEmployees.size());
    }

    public LazyDataModel getLazyDataModel() {
        return lazyDataModel;
    }

    public void setLazyDataModel(LazyDataModel lazyDataModel) {
        this.lazyDataModel = lazyDataModel;
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }

    public List<Employee> getSelectedEmployees() {
        return selectedEmployees;
    }

    public void setSelectedEmployees(List<Employee> selectedEmployees) {
        this.selectedEmployees = selectedEmployees;
    }

    public boolean isSelectAll() {
        return selectAll;
    }

    public void setSelectAll(boolean selectAll) {
        this.selectAll = selectAll;
    }
    
}
