package com.jason.userContactInfo.services;

import com.jason.userContactInfo.models.UserContact;
import org.springframework.context.annotation.Primary;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
@Primary
public class UserContactsServiceUsingFullJsonImpl implements UserContactsService {

    private static final String SERVER_URL = "https://jsonplaceholder.typicode.com/users";

    public UserContact findById(String id) {
        UserContact result = null;  // result should be object
        try {
            RestTemplate restTemplate = new RestTemplate();
            ResponseEntity<UserContact[]> response = restTemplate.getForEntity(SERVER_URL, UserContact[].class);
            for (UserContact userContact: response.getBody()) {
                if (userContact.getId().equals(id)) {
                    result = userContact;
                }
            }

            if (result == null) {
                UserContact errorResult =  new UserContact();
                errorResult.setId("-1 (Not Found)");
                return errorResult;
            }
        } catch(Exception e) {
            UserContact errorResult =  new UserContact();
            if (e instanceof ResourceAccessException) {
                errorResult.setId("-1 (Network-Error)");
            } else {
                errorResult.setId("-1 (Error-by-some-reason)");
            }
            return errorResult;
        }
        return result;
    }


    public UserContact[] findByName(String name) {
        String url = SERVER_URL + "/users?username=" + name;
        UserContact[] results;    // result should be array
        try {
            RestTemplate restTemplate = new RestTemplate();
            ResponseEntity<UserContact[]> response = restTemplate.getForEntity(SERVER_URL, UserContact[].class);
            List<UserContact> resultContactsList = new ArrayList<>();
            for (UserContact userContact: response.getBody()) {
                if (userContact.getUsername().equals(name)) {
                    resultContactsList.add(userContact);
                }
            }
            results = new UserContact[resultContactsList.size()];
            resultContactsList.toArray(results);

            if (results.length == 0) {
                UserContact[] errorResultArray = new UserContact[1];
                UserContact error404 = new UserContact();
                error404.setId("-1 (Not Found)");
                errorResultArray[0] = error404;
                return errorResultArray;
            }
        } catch(Exception e) {
            UserContact errorResult = new UserContact();
            if (e instanceof ResourceAccessException) {
                errorResult.setId("-1 (Network-Error)");
            } else {
                errorResult.setId("-1 (Error-by-some-reason)");
            }
            UserContact[] errorResultArray = new UserContact[1];
            errorResultArray[0] = errorResult;
            return errorResultArray;
        }
        return results;
    }
}
