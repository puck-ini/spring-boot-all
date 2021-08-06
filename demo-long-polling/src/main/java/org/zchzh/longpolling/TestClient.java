package org.zchzh.longpolling;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.Logger;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

/**
 * @author zengchzh
 * @date 2021/8/6
 */
@Slf4j
public class TestClient {

    private CloseableHttpClient httpClient;
    private RequestConfig requestConfig;

    public TestClient() {
        httpClient = HttpClientBuilder.create().build();
        this.requestConfig = RequestConfig.custom().setSocketTimeout(30000).build();
    }

    public void longPolling(String url, String id) throws IOException {
        String endpoint = url + "?id=" + id;
        HttpGet request = new HttpGet(endpoint);
        CloseableHttpResponse response = httpClient.execute(request);
        switch (response.getStatusLine().getStatusCode()) {
            // 200 表示信息变更
            case 200: {
                BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
                StringBuilder result = new StringBuilder();
                String line;
                while ((line = rd.readLine()) != null) {
                    result.append(line);
                }
                response.close();
                log.info("update msg : " + result.toString());
                longPolling(url, id);
                break;
            }
            // 304 表示信息未变更
            case 304: {
                log.info("test msg");
                longPolling(url, id);
                break;
            }
            default: {
                log.error("error");
            }
        }
    }

    public static void main(String[] args) throws IOException {
        // httpClient 会打印很多 debug 日志，关闭掉
        Logger logger = (Logger) LoggerFactory.getLogger("org.apache.http");
        logger.setLevel(Level.INFO);
        logger.setAdditive(false);
        TestClient client = new TestClient();
        client.longPolling("http://127.0.0.1:8080/listener", "test");
    }
}
