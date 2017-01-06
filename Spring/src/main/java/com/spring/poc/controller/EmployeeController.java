package com.spring.poc.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.spring.poc.model.Employee;
import com.spring.poc.model.EmployeeBean;
import com.spring.poc.service.EmployeeService;

@Controller
public class EmployeeController {

	@Autowired
	private EmployeeService employeeService;

	@RequestMapping(value = "/cache", method = RequestMethod.GET)
	public ModelAndView cache() {
		Employee employee = employeeService.getEmployee(1);
		ModelAndView model = new ModelAndView("sucess");
		model.addObject("msg", "Fetched employed from Cache..." + employee.getEmpName());
		return model;
	}

	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public ModelAndView welcome() {
		return new ModelAndView("index");
	}

	@RequestMapping(value = "/employees", method = RequestMethod.GET)
	public ModelAndView listEmployees() {
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("employees", prepareListofBean(employeeService.listEmployeess()));
		return new ModelAndView("employeesList", model);
	}

	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public ModelAndView addEmployee(@ModelAttribute("command") EmployeeBean employeeBean, BindingResult result) {
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("employees", prepareListofBean(employeeService.listEmployeess()));
		return new ModelAndView("addEmployee", model);
	}

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public ModelAndView saveEmployee(@ModelAttribute("command") EmployeeBean employeeBean, BindingResult result) {
		Employee employee = prepareModel(employeeBean);
		employeeService.addEmployee(employee);
		return new ModelAndView("redirect:/add");
	}

	@RequestMapping(value = "/delete/", method = RequestMethod.GET)
	public ModelAndView deleteEmployee(@ModelAttribute("command") EmployeeBean employeeBean,
			@RequestParam("id") String id, BindingResult result) {
		Employee employee = employeeService.getEmployee(Integer.parseInt(id));
		employeeService.deleteEmployee(employee);
		return new ModelAndView("addEmployee");
	}

	// ----------------------------------------------------------------------------------------------------------------------------
	// ----------------------------------------------------------------------------------------------------------------------------

	@RequestMapping(value = "/edit/", method = RequestMethod.GET)
	public ModelAndView editEmployee(@ModelAttribute("command") EmployeeBean employeeBean,
			@RequestParam("id") String id, BindingResult result) {

		Map<String, Object> model = new HashMap<String, Object>();
		Employee employee = employeeService.getEmployee(Integer.parseInt(id));
		model.put("employee", employee);
		model.put("employees", prepareListofBean(employeeService.listEmployeess()));
		return new ModelAndView("editEmployee", model);
		// return new ModelAndView("redirect:/edit");
	}

	private Employee prepareModel(EmployeeBean employeeBean) {
		Employee employee = new Employee();
		employee.setEmpAddress(employeeBean.getAddress());
		employee.setEmpAge(employeeBean.getAge());
		employee.setEmpName(employeeBean.getName());
		employee.setSalary(employeeBean.getSalary());
		employee.setEmpId(employeeBean.getId());
		employee.setId(String.valueOf(employeeBean.getId()));
		employeeBean.setId(null);
		return employee;
	}

	private List<EmployeeBean> prepareListofBean(List<Employee> employees) {
		List<EmployeeBean> beans = null;
		if (employees != null && !employees.isEmpty()) {
			beans = new ArrayList<EmployeeBean>();
			EmployeeBean bean = null;
			for (Employee employee : employees) {
				bean = new EmployeeBean();
				bean.setName(employee.getEmpName());
				bean.setId(employee.getEmpId());
				bean.setAddress(employee.getEmpAddress());
				bean.setSalary(employee.getSalary());
				bean.setAge(employee.getEmpAge());
				beans.add(bean);
			}
		}
		return beans;
	}

}
