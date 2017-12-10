package controllers;


import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import tools.Utils;

import java.util.Arrays;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;
import static tools.Constants.remote_db_base_url;
import static tools.Constants.remote_search_base_url;

@RestController
public class HttpController {

    private static final String base_url = "/api/v1/";

    @RequestMapping(value = base_url + "searchitems", method = POST)
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public String postDataToElasticSearch(@RequestBody String resource) {
        final String url = remote_search_base_url +"/items/searchitems";
        return "ok" ; //postUrl(url,resource);
    }


    @RequestMapping(value = base_url + "items", method = POST)
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public String postDataToCouchDBSearch(@RequestBody String resource) {
        final String url = remote_db_base_url +"/product";
        String response = postUrl(url, resource);
        String searchDbResponse = postToSearchDB(response);
        return searchDbResponse;
    }

    private String postUrl(String url, String requestJson){
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> entity = new HttpEntity<String>(requestJson,headers);
        return restTemplate.postForObject(url, entity, String.class);
    }

    private String postToSearchDB(String resource){
        String result = Utils.convertToSearchObject(resource);
        final String url = remote_search_base_url +"/items/searchitems";
        return postUrl(url,result);
    }

}