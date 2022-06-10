package org.zchzh.session;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

/**
 * @author zengchzh
 * @date 2022/6/9
 */

@RequestMapping("/session")
@RestController
public class SessionController {

    private final Map<String, HttpSession> sessionMap = new HashMap<>();

    @GetMapping("/get")
    public void get(HttpServletRequest request) {
        HttpSession session = request.getSession();
        session.setAttribute("LocalSessionListener", new LocalSessionListener());
        // 设置 session 无操作时的过期时间，单位为s
        session.setMaxInactiveInterval(1800);
        sessionMap.put(session.getId(), session);
    }


    @GetMapping("/destroy")
    public void destroy(HttpServletRequest request) {
        HttpSession session = request.getSession();
        session.invalidate();
    }


    @GetMapping("/test")
    public void test() {
        int size = sessionMap.size();
        System.out.println(size);
    }
}
