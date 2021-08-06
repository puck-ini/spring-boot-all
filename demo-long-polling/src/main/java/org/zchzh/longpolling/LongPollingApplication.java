package org.zchzh.longpolling;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import com.google.common.collect.Multimaps;
import com.google.common.util.concurrent.ThreadFactoryBuilder;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.AsyncContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collection;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;

/**
 * @author zengchzh
 * @date 2021/8/6
 */

@Slf4j
@RestController
@SpringBootApplication
public class LongPollingApplication {
    public static void main(String[] args) {
        SpringApplication.run(LongPollingApplication.class, args);
    }

    @Data
    private static class AsyncTask {
        private AsyncContext asyncContext;

        private boolean timeout;

        public AsyncTask(AsyncContext asyncContext, boolean timeout) {
            this.asyncContext = asyncContext;
            this.timeout = timeout;
        }
    }

    // guava 提供的多值 Map，一个 key 可以对应多个 value
    private volatile Multimap<String, AsyncTask> dataIdContext = Multimaps.synchronizedSetMultimap(HashMultimap
            .create());

    private ThreadFactory threadFactory = new ThreadFactoryBuilder().setNameFormat("longPolling-timeout-checker-%d")
            .build();
    private ScheduledExecutorService timeoutChecker = new ScheduledThreadPoolExecutor(1, threadFactory);


    @RequestMapping("/listener")
    public void addListener(HttpServletRequest request,
                            HttpServletResponse response) {

        String id = request.getParameter("id");
        AsyncContext asyncContext = request.startAsync(request, response);
        AsyncTask asyncTask = new AsyncTask(asyncContext, true);

        dataIdContext.put(id, asyncTask);
        timeoutChecker.schedule(() -> {
            if (asyncTask.isTimeout()) {
                dataIdContext.remove(id, asyncTask);
                response.setStatus(HttpServletResponse.SC_NOT_MODIFIED);
                asyncContext.complete();
            }
        }, 20000, TimeUnit.MILLISECONDS);
        log.info("listenr : {} + map size {}", id, dataIdContext.size());
    }

    @RequestMapping("/publish")
    public String publishMsg(String id, String msg) throws IOException {
        Collection<AsyncTask> asyncTasks = dataIdContext.removeAll(id);
        for (AsyncTask asyncTask : asyncTasks) {
            asyncTask.setTimeout(false);
            HttpServletResponse response = (HttpServletResponse) asyncTask.getAsyncContext().getResponse();
            response.setStatus(HttpServletResponse.SC_OK);
            response.getWriter().println(msg);
            asyncTask.getAsyncContext().complete();
        }
        log.info("publish id : {} msg : {}", id, msg);
        return "success";
    }
}
