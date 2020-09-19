package com.kuang.controller;

import com.kuang.dao.DepartmentDao;
import com.kuang.dao.EmployeeDao;
import com.kuang.pojo.Department;
import com.kuang.pojo.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Collection;

@Controller
public class EmployeeController {

    @Autowired
    EmployeeDao employeeDao;
    @Autowired
    DepartmentDao departmentDao;

    @RequestMapping("/emps")
    public String list(Model model){
        Collection<Employee> employees = employeeDao.getAll();
        model.addAttribute("emps",employees);
        return "emp/list";
    }

    // 跳转到添加员工页面，方式是get
    @GetMapping("/emp")
    public String toAddpage(Model model){
        // 添加按钮页面，有一个员工部门的下拉框，我们在这里就需要先查询有哪些部门
        Collection<Department> departments = departmentDao.getDepartment();
        model.addAttribute("departments",departments);
        return "emp/add";
    }

    // 这个是add中的跳转，方式是post
    @PostMapping("/emp")
    public String addEmp(Employee employee){
        // 添加的操作
        System.out.println("save====>"+employee);
        employeeDao.save(employee);     //调用底层业务方法，保存员工信息
        return "redirect:/emps";
    }
    
}
