/**************************************************************************
 * Copyright (c) 2013 2359 Media Pvt Ltd
 *
 * NOTICE:  All information contained herein is, and remains the 
 * property of 2359 Media Pvt Ltd and its suppliers, if any. 
 * Dissemination of this information or reproduction of this material
 * is strictly forbidden unless prior written permission is obtained
 * from 2359 Media Pvt Ltd
 ***************************************************************************/
package com.media2359.euphoria.dao.project;

import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Set;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.Test;
import org.junit.FixMethodOrder;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.media2359.euphoria.dao.project.ProjectDAO;
import com.media2359.euphoria.dao.employee.EmployeeDAO;
import com.media2359.euphoria.model.employee.Employee;
import com.media2359.euphoria.model.project.Project;
import com.media2359.euphoria.model.project.ProjectTeam;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"/applicationContext-model-test.xml"})
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ProjectTeamDaoTest {
	@Autowired
	private ProjectTeamDAO projectTeamDao;
	
	@Autowired
	private ProjectDAO projectDao;
	
	@Autowired
	private EmployeeDAO employeeDao;
	
	private Logger log = Logger.getLogger(ProjectTeamDaoTest.class);
	
	@Test
	public void test1GetAllProjectTeams() {
		
		log.info("####test1GetAllProjectTeams start...");
		
		List<ProjectTeam> projectTeams = projectTeamDao.getAllProjectTeams();
		Assert.assertNotNull(projectTeams);
		log.info("####Number of Project Teams returned:"+projectTeams.size());
		ListIterator iterator = projectTeams.listIterator();
		ProjectTeam projectTeam = iterator.hasNext()?(ProjectTeam) iterator.next():null;
		log.info("####Project returned in Project Team:"+ projectTeam.getProject().toString());

		log.info("####test1GetAllProjectTeams end...");
	}
	
	@Test
	public void test2GetProjectTeamMembers() {
		log.info("####test2GetProjectTeamMembers start...");
		Project project = new Project();
		project.setId(Integer.valueOf(2));
		
		ProjectTeam projectTeam = projectTeamDao.getProjectTeam(project);
		Assert.assertNotNull(projectTeam);
		//test employee
		Set<Employee> employees = projectTeam.getTeamMembers();
		Assert.assertNotNull(employees);
		
		log.info("####Number of Team Members:"+employees.size());
		
		Employee employee;
		
		for (Iterator<Employee> it = employees.iterator(); it.hasNext(); )
		{
			employee = it.next();
			log.info("####Team Member Details:"+ employee.toString());
		}
		
		//test manager
		Set<Employee> managers = projectTeam.getProjectManagers();
		Assert.assertNotNull(managers);
		log.info("####Number of Team managers:"+managers.size());
		
		Employee manager;
		for (Iterator<Employee> it = managers.iterator(); it.hasNext(); )
		{
			manager = it.next();
			log.info("####Team Manager Details:"+ manager.toString());
		}
		
		log.info("####test2GetProjectTeamMembers end...");
	}
	
	@Test
	public void test3GetProjectTeam(){
		
		log.info("####test3GetProjectTeam start...");
		Project project = new Project();
		project.setId(Integer.valueOf(2));
		ProjectTeam projectTeam = projectTeamDao.getProjectTeam(project);
		Assert.assertNotNull(projectTeam);
		log.info("####test3GetProjectTeam end...");
	}
	
	
	/*@Test
	public void testGetProject() {
		Project project = projectDao.getProject(Integer.valueOf(1));
		Assert.assertNotNull(project);
		log.info("Project Name:" + project.getName());
	}
	
	@Test
	public void testAddProject() {
		Project project1 = new Project();
		Project project2;
		project1.setId(Integer.valueOf(0));
		project1.setName("TEST123");
		project1.setDescription("Project Unit Test for save a project");
		project1.setManDaysLeft(100);
		project1.setProjectManager("Mgr123");
		
		projectDao.addProject(project1);
		
		Integer maxKey = projectDao.getMaxKey();
		log.info("MaxKey::" + maxKey.toString());
		project2 = projectDao.getProject(maxKey);
		
		Assert.assertNotNull(project2);
		log.info("Project Name:" + project2.getName());
	}
	
	@Test
	public void testDeleteProject() {
		Project project1;
		Project project2;
		
		Integer maxKey = projectDao.getMaxKey();
		log.info("TestDeleteProject->MaxKey::" + maxKey.toString());
		//project1 = projectDao.getProject(maxKey);
		
		projectDao.deleteProject(maxKey);
		project2 = projectDao.getProject(maxKey);
		Assert.assertNull(project2);
		
	}*/
	/*@Test
	public void test3AddProjectTeam() {
		ProjectTeam projectTeam1 = new ProjectTeam();
		ProjectTeam projectTeam2;
		Project project = new Project();
		Employee employee = new Employee();
		Employee manager = new Employee();
		Set<Employee> employees = new HashSet<Employee>();
		Set<Employee> managers = new HashSet<Employee>();
		
		log.info("####test3AddProjectTeam Starts...");
		
		project = projectDao.getProject(Integer.valueOf(2));
		employee = employeeDao.getEmployee(Integer.valueOf(1));
		manager = employeeDao.getEmployee(Integer.valueOf(2));
		
		employees.add(employee);
		managers.add(manager);
		
		projectTeam1.setProjectTeamName("Test Add Project Team");
		projectTeam1.setProjectManagers(managers);
		projectTeam1.setTeamMembers(employees);
		projectTeam1.setCreatedBy("TY");
		projectTeam1.setCreatedTstmp(new Date());
		projectTeam1.setProject(project);
		
		Integer maxKey1 = projectTeamDao.getMaxKey();
		log.info("####test3AddProjectTeam->MaxKey1::" + maxKey1.toString());
		
		projectTeamDao.addProjectTeam(projectTeam1);
		
		Integer maxKey2 = projectTeamDao.getMaxKey();
		log.info("####test3AddProjectTeam->MaxKey2::" + maxKey1.toString());
		
	
		Assert.assertTrue((maxKey1.intValue()+1)==(maxKey2.intValue()));
		
	}*/
	
	@Test
	public void test4UpdateProjectTeam() {
		log.info("####test4UpdateProjectTeam start...");
		Project project = new Project();
		project.setId(Integer.valueOf(2));
		ProjectTeam projectTeam1;
		ProjectTeam projectTeam2;
		
		projectTeam1 = projectTeamDao.getProjectTeam(project);
		Assert.assertNotNull(projectTeam1);
		
		log.info("####ProjectTeam->toString()::" + projectTeam1.toString());
		projectTeam1.setProjectTeamName("TEST_UPD123");
		
		projectTeamDao.updateProjectTeam(projectTeam1);
		projectTeam2 = projectTeamDao.getProjectTeam(project);
		
		Assert.assertTrue("TEST_UPD123".equalsIgnoreCase(projectTeam2.getProjectTeamName()));
		log.info("####test4UpdateProjectTeam end...");
	}
		
}

