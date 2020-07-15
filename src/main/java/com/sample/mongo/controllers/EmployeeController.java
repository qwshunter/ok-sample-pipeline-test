package com.sample.mongo.controllers;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.sns.AmazonSNSClient;
import com.amazonaws.services.sns.model.CreateTopicRequest;
import com.amazonaws.services.sns.model.CreateTopicResult;
import com.amazonaws.services.sns.model.MessageAttributeValue;
import com.amazonaws.services.sns.model.PublishRequest;
import com.amazonaws.services.sns.model.PublishResult;
import com.sample.mongo.commands.EmployeeForm;
import com.sample.mongo.converters.EmployeeToEmployeeForm;
import com.sample.mongo.domain.Employee;
import com.sample.mongo.services.EmployeeService;

@Controller
public class EmployeeController {
	private EmployeeService employeeService;    

	@Autowired
	public void setEmployeeToemployeeform(EmployeeToEmployeeForm employeeToemployeeform) {
	}

	@Autowired
	public void setEmployeeService(EmployeeService employeeService) {
		this.employeeService = employeeService;
	}

	@RequestMapping("/")
	public String redirToList() {
		return "redirect:/employee/list";
	}

	@RequestMapping({ "/employee/list", "/employee" })
	public String listEmployees(Model model) {
		model.addAttribute("employees", employeeService.listAll());
		return "employee/list";
	}

	@RequestMapping("/employee/show/{id}")
	public String getemployee(@PathVariable String id, Model model) {
		model.addAttribute("employee", employeeService.getById(id));
		return "employee/show";
	}

	@RequestMapping("/employee/new")
	public String newEmployee(Model model) {
		model.addAttribute("employeeform", new EmployeeForm());
		return "employee/employeeform";
	}

	@RequestMapping(value = "/employee", method = RequestMethod.POST)
	public String saveOrUpdateEmployee(@Valid EmployeeForm employeeform, BindingResult bindingResult) {

		if (bindingResult.hasErrors()) {
			return "employee/employeeform";
		}

		Employee savedemployee = employeeService.saveOrUpdateEmployeeform(employeeform);
		System.out.println("saved employee:: " + savedemployee);
		// return "redirect:/employee/show/" + savedemployee.getId();
		return "redirect:/employee/list";
	}

	@RequestMapping("/test/sns")
	public String testSns() {
		System.out.println("inside  testSns:: ");
		AWSCredentials awsCredentials = new BasicAWSCredentials("XXXX", "XXXX");
	    
	      
	    AmazonSNSClient snsClient = new AmazonSNSClient(awsCredentials);
	    snsClient.withRegion(Regions.AP_SOUTH_1);
	    CreateTopicRequest createReq = new CreateTopicRequest()
	            .withName("ok-sample-topic");
	    CreateTopicResult createRes = snsClient.createTopic(createReq);
	  //  createRes.setTopicArn("arn:aws:sns:ap-south-1:911551673775:ok-sample-topic");
	    
	    System.out.println("inside  getTopicArn:: "+createRes.getTopicArn());
	    
	    // Publish to a topic
        PublishRequest publishReq = new PublishRequest()
            .withTopicArn(createRes.getTopicArn())
            .withMessage("Example notification sent at " + new Date());
        PublishResult result = snsClient.publish(publishReq);
        
	    
	    System.out.println("result::"+result); // Prints the message ID.
		return "Success";
	}
}
