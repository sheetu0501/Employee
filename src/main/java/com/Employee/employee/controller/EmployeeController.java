package com.Employee.employee.controller;

import java.util.List;

import java.util.Map;
import java.util.jar.Attributes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.Employee.employee.entity.Employee;
import com.Employee.employee.repository.EmployeeRepository;

@Controller
public class EmployeeController {
	@Autowired
	EmployeeRepository employeeRepository;
	
	@GetMapping("/")
	public String load() {
		return "main.html";
	}
	
	@GetMapping("/add")
	public String add() {
		return "add.html";
	}

	@PostMapping("/add")
	public String add(@ModelAttribute Employee employee, RedirectAttributes attributes) {
		employeeRepository.save(employee);
		attributes.addFlashAttribute("message", "Data Saved Success");
		return "redirect:/";
	}

	@GetMapping("/view")
	public String view(RedirectAttributes attributes, ModelMap map) {
		List<Employee> list =employeeRepository.findAll();
		if(list.isEmpty()) {
			attributes.addFlashAttribute("message","No record present");
			return "redirect:/";
				}
		else {
		 map.put("list",list);
		 return "view.html";
		}}
	
	@GetMapping("/delete")
	public String delete(@RequestParam int id,RedirectAttributes attributes) {
		employeeRepository.deleteById(id);
		attributes.addFlashAttribute("message", "Data Deleted Success");
		return "redirect:/view";
	}
	
	@GetMapping("/edit")
	public String delete(@RequestParam int id,ModelMap map) {
		Employee employee = employeeRepository.findById(id).orElseThrow();
		map.put("emp", employee);
		return "edit.html";
	}
	
	@PostMapping("/update")
	public String update(@ModelAttribute Employee employee, RedirectAttributes attributes) {
		employeeRepository.save(employee);
		attributes.addFlashAttribute("message", "Data Update Success");
		return "redirect:/view";
	}

}
