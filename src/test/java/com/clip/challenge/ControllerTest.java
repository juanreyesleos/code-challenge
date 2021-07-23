package com.clip.challenge;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.net.URL;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;

import com.clip.challenge.config.SpringConfiguration;
import com.clip.challenge.entity.TransactionEntity;
import com.clip.challenge.repository.TransactionRepository;
import com.clip.challenge.service.impl.TransactionServiceImpl;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@ActiveProfiles({ "h2IntegrationTests" })
@ContextConfiguration(classes = SpringConfiguration.class)
class ControllerTest {
	@Autowired
	TransactionServiceImpl transactionService;

	@Autowired
	TransactionRepository transactionRepository;

	@LocalServerPort
	private int port;

	@Autowired
	private TestRestTemplate restTemplate;


	@Test
	public void addTransactioinTest() {
		TransactionEntity transactionDTO = new TransactionEntity();
		transactionDTO.setAmount(300d);
		transactionDTO.setClipUser("test");
		transactionDTO.setCarddata("3495834583438204");
		HttpEntity<TransactionEntity> requestEntity = new HttpEntity<>(transactionDTO);
		ResponseEntity<TransactionEntity> response = this.restTemplate.exchange(
				"http://localhost:" + port + "/transaction", HttpMethod.POST, requestEntity, TransactionEntity.class);
		assertEquals("test", response.getBody().getClipUser());
		assertEquals("3495834583438204", response.getBody().getCarddata());
		assertNotNull(response.getBody().getDate());
		assertNotNull(response.getBody().getId());
		assertEquals(false, response.getBody().isPaid());
		assertEquals(200, response.getStatusCodeValue());
	}

	@Test
	public void addTransactioinWithOutClipUserParameterTest() {
		TransactionEntity transactionDTO = new TransactionEntity();
		transactionDTO.setAmount(300d);
		transactionDTO.setCarddata("3495834583438204");
		HttpEntity<TransactionEntity> requestEntity = new HttpEntity<>(transactionDTO);
		ResponseEntity<String> response = this.restTemplate.exchange("http://localhost:" + port + "/transaction",
				HttpMethod.POST, requestEntity, String.class);
		assertEquals(true, response.getBody().contains("clipUser parameter is mandatory"));
		assertEquals(true, response.getBody().contains("400"));
		assertEquals(true, response.getBody().contains("BAD_REQUEST"));
		assertEquals(400, response.getStatusCodeValue());
	}

	@Test
	public void addTransactioinWithOutCardDataParameterTest() {
		TransactionEntity transactionDTO = new TransactionEntity();
		transactionDTO.setAmount(300d);
		transactionDTO.setClipUser("test");
		HttpEntity<TransactionEntity> requestEntity = new HttpEntity<>(transactionDTO);
		ResponseEntity<String> response = this.restTemplate.exchange("http://localhost:" + port + "/transaction",
				HttpMethod.POST, requestEntity, String.class);
		assertEquals(true, response.getBody().contains("carddata parameter is mandatory"));
		assertEquals(true, response.getBody().contains("400"));
		assertEquals(true, response.getBody().contains("BAD_REQUEST"));
	}

	@Test
	public void addTransactioinWithOutAmountParameterTest() {
		TransactionEntity transactionDTO = new TransactionEntity();
		transactionDTO.setClipUser("test");
		transactionDTO.setCarddata("3495834583438204");
		HttpEntity<TransactionEntity> requestEntity = new HttpEntity<>(transactionDTO);
		ResponseEntity<String> response = this.restTemplate.exchange("http://localhost:" + port + "/transaction",
				HttpMethod.POST, requestEntity, String.class);
		assertEquals(true, response.getBody().contains("amount parameter is mandatory"));
		assertEquals(true, response.getBody().contains("400"));
		assertEquals(true, response.getBody().contains("BAD_REQUEST"));
	}

	@Test
	public void addTransactioinWithOutBodyTest() {
		HttpEntity<String> requestEntity = new HttpEntity<>("data");
		ResponseEntity<String> response = this.restTemplate.exchange("http://localhost:" + port + "/transaction",
				HttpMethod.POST, requestEntity, String.class);
		assertEquals(true, response.getBody().contains("415"));
		assertEquals(true, response.getBody().contains("Unsupported Media Type"));
	}

	@Test
	public void addTransactioWithHttpDiferentTest() {
		TransactionEntity transactionDTO = new TransactionEntity();
		transactionDTO.setAmount(300d);
		transactionDTO.setClipUser("test");
		transactionDTO.setCarddata("3495834583438204");
		HttpEntity<TransactionEntity> requestEntity = new HttpEntity<>(transactionDTO);
		ResponseEntity<String> response = this.restTemplate.exchange("http://localhost:" + port + "/transaction",
				HttpMethod.DELETE, requestEntity, String.class);
		assertEquals(true, response.getBody().contains("400"));
		assertEquals(true, response.getBody().contains("BAD_REQUEST"));
		assertEquals(true, response.getBody().contains("not supporte"));
		assertEquals(400, response.getStatusCodeValue());
	}

	@Test
	public void getTransactionByClipUserTest() throws Exception {
		ResponseEntity<String> response = restTemplate
				.getForEntity(new URL("http://localhost:" + port + "/transaction/test").toString(), String.class);
		assertEquals(200, response.getStatusCodeValue());
	}

	@Test
	public void getTransactionByClipUserTestWithData() throws Exception {
		ResponseEntity<Object[]> response = restTemplate
				.getForEntity(new URL("http://localhost:" + port + "/transaction/juan").toString(), Object[].class);
		assertEquals(true, response.getBody().length >= 0);
		assertEquals(200, response.getStatusCodeValue());
	}

	@Test
	public void getTransactionByClipUserFailPathTest() throws Exception {
		ResponseEntity<String> response = restTemplate
				.getForEntity(new URL("http://localhost:" + port + "/transactionn/juan").toString(), String.class);
		assertEquals(404, response.getStatusCodeValue());
		assertEquals(true, response.getBody().contains("Not Found Exception"));
		assertEquals(true, response.getBody().contains("404"));
		assertEquals(true, response.getBody().contains("NOT_FOUND"));
	}

	@Test
	public void findByClipUserOrderByClipUserTest() throws Exception {
		ResponseEntity<String> response = restTemplate
				.getForEntity(new URL("http://localhost:" + port + "/disbursement").toString(), String.class);
		assertEquals(200, response.getStatusCodeValue());
	}

	@Test
	public void findByClipUserOrderByClipUserTestWithData() throws Exception {
		ResponseEntity<Object[]> response = restTemplate
				.getForEntity(new URL("http://localhost:" + port + "/disbursement").toString(), Object[].class);
		assertEquals(true, response.getBody().length >= 0);
		assertEquals(200, response.getStatusCodeValue());
	}

	@Test
	public void findByClipUserOrderByClipUserFailPathTest() throws Exception {
		ResponseEntity<String> response = restTemplate
				.getForEntity(new URL("http://localhost:" + port + "/disbursement2").toString(), String.class);
		assertEquals(404, response.getStatusCodeValue());
		assertEquals(true, response.getBody().contains("Not Found Exception"));
		assertEquals(true, response.getBody().contains("404"));
		assertEquals(true, response.getBody().contains("NOT_FOUND"));
	}

	
	@Test
	public void makeDisbursementTest() throws Exception {
		ResponseEntity<Object[]> response = restTemplate
				.postForEntity(new URL("http://localhost:" + port + "/disbursement").toString(), null, Object[].class);
		assertEquals(true, response.getBody().length >= 0);
		assertEquals(200, response.getStatusCodeValue());
	}

	@Test
	public void makeDisbursementFailPathTest() throws Exception {
		ResponseEntity<String> response = restTemplate
				.postForEntity(new URL("http://localhost:" + port + "/disbursementt").toString(), null, String.class);
		assertEquals(404, response.getStatusCodeValue());
		assertEquals(true, response.getBody().contains("Not Found Exception"));
		assertEquals(true, response.getBody().contains("404"));
		assertEquals(true, response.getBody().contains("NOT_FOUND"));
	}

	@Test
	public void makeDisbursementNotSupportHttpTest() throws Exception {
		ResponseEntity<String> response = this.restTemplate.exchange("http://localhost:" + port + "/transaction",
				HttpMethod.DELETE, null, String.class);
		assertEquals(true, response.getBody().contains("400"));
		assertEquals(true, response.getBody().contains("BAD_REQUEST"));
		assertEquals(true, response.getBody().contains("not supporte"));
		assertEquals(400, response.getStatusCodeValue());
	}

}
