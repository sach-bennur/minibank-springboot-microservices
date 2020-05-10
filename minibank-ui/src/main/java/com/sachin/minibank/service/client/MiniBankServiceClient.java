package com.sachin.minibank.service.client;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
import com.netflix.discovery.shared.Application;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.sachin.minibank.dto.AccountDto;
import com.sachin.minibank.dto.BenificiaryDto;
import com.sachin.minibank.dto.TransferFormData;
import com.sachin.minibank.dto.UserDto;

@Service
public class MiniBankServiceClient {


	@Autowired
	private RestTemplate restTemplate;

	@Autowired
	private EurekaClient eurekaClient;

	@Value("MINIBANK-SERVICE")
	private String miniBankServiceId;

	public List<AccountDto> getAccountDetails(String email) {
		
		InstanceInfo instanceInfo = getServiceInstanceInfo();
        String url = "http://" + instanceInfo.getIPAddr() + ":" + instanceInfo.getPort() + "/" + "getAccountDetails/" + email;
        System.out.println("URL" + url);
        ResponseEntity<AccountDto[]> response  = (ResponseEntity<AccountDto[]>) restTemplate.getForEntity(url, AccountDto[].class);
        
        return Arrays.asList(response.getBody());	
	}
	
	public boolean addTransfer(TransferFormData transferFormData) {
		InstanceInfo instanceInfo = getServiceInstanceInfo();
        String url = "http://" + instanceInfo.getIPAddr() + ":" + instanceInfo.getPort() + "/" + "addTransfer";
        ResponseEntity<Boolean> response = restTemplate.postForEntity(url, transferFormData, Boolean.class);
		return response.getBody();
	}
	
	@HystrixCommand(fallbackMethod = "getDefaultUser")
	public UserDto findUserByEmail(String email) {
		InstanceInfo instanceInfo = getServiceInstanceInfo();
        String url = "http://" + instanceInfo.getIPAddr() + ":" + instanceInfo.getPort() + "/" + "findUser/"+email;
        ResponseEntity<UserDto> response = restTemplate.getForEntity(url, UserDto.class);
		return response.getBody();
	}
	
	public UserDto addBenificiary(BenificiaryDto benificiaryDto) {
		InstanceInfo instanceInfo = getServiceInstanceInfo();
        String url = "http://" + instanceInfo.getIPAddr() + ":" + instanceInfo.getPort() + "/" + "addBenificiary";
        ResponseEntity<UserDto> response = restTemplate.postForEntity(url, benificiaryDto, UserDto.class);
		return response.getBody();
	}
	
	@SuppressWarnings("unused")
	private UserDto getDefaultUser(String email) {
		
		UserDto defaultUser = new UserDto();
		defaultUser.setFirstName("Default User");
		
		return defaultUser;
	}
	
	private InstanceInfo getServiceInstanceInfo() {
		Application application = eurekaClient.getApplication(miniBankServiceId);
        InstanceInfo instanceInfo = application.getInstances().get(0);
        return instanceInfo;
	}
}
