package com.techelevator.tenmo.services;

import com.techelevator.tenmo.exception.TransferFailedException;
import com.techelevator.tenmo.model.Transfer;
import com.techelevator.tenmo.model.User;
import com.techelevator.util.BasicLogger;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestClientResponseException;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.List;

public class TransferService {

    private  String urlBaseTransfer = "http://localhost:8080/transfer";
    private  RestTemplate restTemplate = new RestTemplate();

    public TransferService(String urlBaseTransfer, RestTemplate restTemplate) {
        this.urlBaseTransfer = urlBaseTransfer;
    }

    public List<Transfer> getAllTransfers(User user, String bearerToken) { //get list of all transfers
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setBearerAuth(bearerToken);
        HttpEntity<Void> entity = new HttpEntity<>(httpHeaders);
        BasicLogger.log("http header getAllTransfers");
        try {
            ResponseEntity<List<Transfer>> response = restTemplate.exchange(
                    urlBaseTransfer + "/list", HttpMethod.GET, entity,
                    new ParameterizedTypeReference<List<Transfer>>(){}
                    );
            return response.getBody();
        } catch(RestClientResponseException | ResourceAccessException e) {
            BasicLogger.log("Error retrieving transfers: " + e.getMessage());
        } return Collections.emptyList();
    }


    public Transfer getTransferByTransferId(int transferId, String bearerToken){

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setBearerAuth(bearerToken);
        HttpEntity<Void> entity = new HttpEntity<>(httpHeaders);
        BasicLogger.log("http set for getTransferByTransferId");
        try{
            ResponseEntity<Transfer>transferResponseEntity= restTemplate.exchange(urlBaseTransfer+"/"+ transferId,
                    HttpMethod.GET, entity, Transfer.class);
            BasicLogger.log("Exchange getTransferByTransferId");
            return transferResponseEntity.getBody();
        }catch(RestClientResponseException | ResourceAccessException e) {
            BasicLogger.log("Error retrieving list of transfers: " + e.getMessage());
        }return null;
    }

    public Transfer createTransfer(Transfer transferRequest, String bearerToken) { //create transfer
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        httpHeaders.setBearerAuth(bearerToken);
        HttpEntity<Transfer> entity = new HttpEntity<>(transferRequest, httpHeaders);
        BasicLogger.log("http headers set");
        try {
            String url = urlBaseTransfer + "/send";
            BasicLogger.log("tserv - accessing url base /send");
            BasicLogger.log("tserv - attempting post for object createTransfer");
            return restTemplate.postForObject(url, entity, Transfer.class);
        } catch (RestClientResponseException restClientResponseException) {
            BasicLogger.log("tserv - create - Error rest client: " + restClientResponseException.getMessage());
            BasicLogger.log("tserv - create - Response body: " + restClientResponseException.getResponseBodyAsString());
            throw new TransferFailedException("Error sending transfer", restClientResponseException);
        } catch (ResourceAccessException resourceAccessException) {
            BasicLogger.log("tserv - create - Error resourceAccess: " + resourceAccessException.getMessage());
            throw new TransferFailedException("Error sending transfer", resourceAccessException);
        }
    }
}