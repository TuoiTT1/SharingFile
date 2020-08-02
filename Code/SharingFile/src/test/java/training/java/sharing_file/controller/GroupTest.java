package training.java.sharing_file.controller;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;

import training.java.sharing_file.dto.GroupDTO;

class GroupTest {
	@Autowired
	private TestRestTemplate restTemplate;

	private String getRootUrl() {
		return "http://localhost:8080";
	}

//	@Test
//	void testCreateGroup() {
//		GroupDTO groupDTO = new GroupDTO();
//		groupDTO.setCode("GRP1");
//		groupDTO.setName("Group 1");
//		groupDTO.setDescription("");
//
//		ResponseEntity<GroupDTO> postResponse = restTemplate.postForEntity(getRootUrl() + "/group/new", groupDTO,
//				GroupDTO.class);
//		assertNotNull(postResponse);
//		assertNotNull(postResponse.getBody());
//		
//	}

}
