package com.atguigu.p3.service;

import com.atguigu.p3.domain.*;

public class NameListService {
	
	private Employee[] employees;//用来保存公司所有员工对象

	public NameListService() {
		
		employees = new Employee[Data.EMPLOYEES.length];
		
		for (int i = 0; i < employees.length; i++) {
			// 获取通用的属性
			int type = Integer.parseInt(Data.EMPLOYEES[i][0]);
			int id = Integer.parseInt(Data.EMPLOYEES[i][1]);
			String name = Data.EMPLOYEES[i][2];
			int age = Integer.parseInt(Data.EMPLOYEES[i][3]);
			double salary = Double.parseDouble(Data.EMPLOYEES[i][4]);
			
			Equipment eq;
			int bonus;
			int stock;
			
			switch (type) {
			case Data.EMPLOYEE:
				employees[i] = new Employee(id,name,age,salary);
				break;
			case Data.PROGRAMMER:
				eq = createEquipment(i);
				employees[i] = new Programmer(id,name,age,salary,eq);
				break;
			case Data.DESIGNER:
				eq = createEquipment(i);
				bonus = Integer.parseInt(Data.EMPLOYEES[i][5]);
				employees[i] = new Designer(id,name,age,salary, eq, bonus);
				break;
			case Data.ARCHITECT:
				eq = createEquipment(i);
				bonus = Integer.parseInt(Data.EMPLOYEES[i][5]);
				stock = Integer.parseInt(Data.EMPLOYEES[i][6]);
				employees[i] = new Architect(id,name,age,salary, eq, bonus, stock);
				break;
			}
		}
	}
	
	private Equipment createEquipment(int index) {
		int type = Integer.parseInt(Data.EQIPMENTS[index][0]);
		switch (type) {
		case Data.PC:
			return new PC(Data.EQIPMENTS[index][1], Data.EQIPMENTS[index][2]);
		case Data.NOTEBOOK:
			int price = Integer.parseInt(Data.EQIPMENTS[index][2]);
			return new NoteBook(Data.EQIPMENTS[index][1],price);
		case Data.PRINTER:
			return new Printer(Data.EQIPMENTS[index][1], Data.EQIPMENTS[index][2]);
		}
		return null;
	}

	//获取指定ID的员工对象
	public Employee getEmployee(int id) throws TeamException{
		for (Employee employee : employees) {
			if (employee.getId() == id) {
				return employee;
			}
		}
		throw new TeamException("该员工不存在");
	}
	//获取当前所有员工
	public Employee[] getAllEmployees() {
		return employees;
	}

}
