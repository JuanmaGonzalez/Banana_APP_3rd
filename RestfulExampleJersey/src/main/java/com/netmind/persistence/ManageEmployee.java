package com.netmind.persistence;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import com.netmind.models.Employee;

public class ManageEmployee {
	
	public SessionFactory factory = null;
	
	public static ManageEmployee instance = null;
	
	public ManageEmployee() {
		factory = new Configuration().configure().buildSessionFactory();			
	}
	
	public static ManageEmployee getInstance() {
		if ( instance == null ) {
			instance = new ManageEmployee(); }
		
		return instance;		
	}
	
	
	public void insertEntities () {
		Session session = factory.openSession();
		
		Transaction t = session.beginTransaction();
		
		Employee e1 = new Employee("Ricardo", "Juan");
		session.persist(e1);		
		t.commit();
		session.close();
		
		Employee e2 = new Employee("Juan","Pedro");		
		insertEmployee(e2);
		
		Employee e3 = new Employee("Juan","Andres");		
		insertEmployee(e3);
		
		Employee e4 = new Employee("Juan","Rodrigues");		
		insertEmployee(e4);	
		
	}
	
	public int insertEmployee(Employee EmpleadoNuevo) {
		Session session = factory.openSession();
		
		Transaction t = session.beginTransaction();
		int eid = ((Integer) session.save((EmpleadoNuevo))).intValue();
		
		t.commit();
		session.close();
		
		return eid;
		
	}
	
	public Employee getEmpleado(int eid)  {
		Session session = factory.openSession();
		
		Transaction t = session.beginTransaction();
		Employee elEmpleado = session.get(Employee.class,eid);
		
		t.commit();
		session.close();
		
		return elEmpleado;
	}
	
	

}
