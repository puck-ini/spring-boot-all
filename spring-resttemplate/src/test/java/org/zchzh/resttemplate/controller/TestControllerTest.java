package org.zchzh.resttemplate.controller;

import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.*;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;
import org.zchzh.resttemplate.model.Result;
import org.zchzh.resttemplate.model.TestReq;

import java.net.URI;
import java.util.Arrays;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;


@Slf4j
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class TestControllerTest {


    @Autowired
//    @Qualifier("simpleRestTemplate")
    private RestTemplate restTemplate;

    @LocalServerPort
    private int port;

    private static final String URL = "http://127.0.0.1:";


    @Test
    void getv1() {
        Result result = restTemplate.getForObject(URL + port + "/v1/get", Result.class, new TestReq());
        Assert.assertNotNull(result);
        log.info(result.toString());
    }

    @Test
    void getv2() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("test", "test");
        headers.put(HttpHeaders.COOKIE, Collections.singletonList("cookie"));
        HttpEntity<TestReq> httpEntity = new HttpEntity<>(new TestReq(), headers);
        ResponseEntity<Result> responseEntity = restTemplate.exchange(URL + "/v2/get", HttpMethod.GET, httpEntity, Result.class);
        Result result = responseEntity.getBody();
        Assert.assertNotNull(result);
        log.info(result.toString());
    }

    @Test
    void getv3() {

    }

    @Test
    void postv1() {
        Result result = restTemplate.postForObject(URL + "/v1/post", new TestReq(), Result.class);
        Assert.assertNotNull(result);
        log.info(result.toString());
    }

    @Test
    void postv2() {
        UriComponents uriComponents = UriComponentsBuilder.fromHttpUrl(URL).path("/v2/post").build();
        URI uri = uriComponents.toUri();
        RequestEntity<TestReq> requestEntity = RequestEntity.post(uri)
                .header(HttpHeaders.COOKIE, "cookie")
                .header("test", "test")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .body(new TestReq());
        ResponseEntity<Result> responseEntity = restTemplate.exchange(requestEntity, Result.class);
        Result result = responseEntity.getBody();
        Assert.assertNotNull(result);
        log.info(result.toString());
    }

    @Test
    void postv3() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("test", "test");
        headers.put(HttpHeaders.COOKIE, Collections.singletonList("cookie"));
        HttpEntity<TestReq> httpEntity = new HttpEntity<>(new TestReq(), headers);
        ResponseEntity<Result> responseEntity = restTemplate.exchange(URL + "/v3/post", HttpMethod.POST, httpEntity, Result.class);
        Result result = responseEntity.getBody();
        Assert.assertNotNull(result);
        log.info(result.toString());
    }

    @Test
    void put() {
        HttpHeaders headers = new HttpHeaders();
//        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("test", "test");
        headers.put(HttpHeaders.COOKIE, Collections.singletonList("cookie"));
        HttpEntity<TestReq> httpEntity = new HttpEntity<>(new TestReq(), headers);
        ResponseEntity<Result> responseEntity = restTemplate.exchange(URL + "/put", HttpMethod.PUT, httpEntity, Result.class);
        Result result = responseEntity.getBody();
        Assert.assertNotNull(result);
        log.info(result.toString());
    }


    @Test
    void delete() {
        HttpHeaders headers = new HttpHeaders();
//        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("test", "test");
        headers.put(HttpHeaders.COOKIE, Collections.singletonList("cookie"));
        HttpEntity<TestReq> httpEntity = new HttpEntity<>(new TestReq(), headers);
        ResponseEntity<Result> responseEntity = restTemplate.exchange(URL + "/delete", HttpMethod.DELETE, httpEntity, Result.class);
        Result result = responseEntity.getBody();
        Assert.assertNotNull(result);
        log.info(result.toString());
    }

    @Test
    void upload() {
        MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
        body.add("msg", "test");
        FileSystemResource file1 = new FileSystemResource("D:\\testdata\\test.txt");
        FileSystemResource file2 = new FileSystemResource("D:\\testdata\\test.txt");
        body.add("files", file1);
        body.add("files", file2);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);
//        MultiValueMap<String, String> headers = new LinkedMultiValueMap();
//        headers.put(HttpHeaders.CONTENT_TYPE, Arrays.asList(MediaType.MULTIPART_FORM_DATA_VALUE));
        HttpEntity<MultiValueMap<String, Object>> httpEntity = new HttpEntity<>(body, headers);
        ResponseEntity<Result> responseEntity = restTemplate.exchange(URL + "/upload", HttpMethod.POST, httpEntity, Result.class);
        Result result = responseEntity.getBody();
        Assert.assertNotNull(result);
        log.info(result.toString());
    }
}