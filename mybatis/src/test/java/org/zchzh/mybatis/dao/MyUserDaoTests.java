package org.zchzh.mybatis.dao;

import lombok.extern.slf4j.Slf4j;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StopWatch;
import org.zchzh.mybatis.entity.MyUser;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;
import java.util.stream.IntStream;

/**
 * @author zengchzh
 * @date 2023/8/9
 */

@Slf4j
@SpringBootTest
public class MyUserDaoTests {

    @Autowired
    private MyUserDao myUserDao;

    private final StopWatch sw = new StopWatch();

    private List<MyUser> getTestData() {
        List<MyUser> myUserList = new ArrayList<>();
        IntStream.range(0, 300000).forEach(i -> {
            MyUser myUser = new MyUser();
            myUser.setName("name" + i);
            myUser.setAddr("addr" + i);
            myUser.setDes("desc" + i);
            myUser.setMsg("msg" + i);
            myUserList.add(myUser);
        });
        return myUserList;
    }

    // 30w 29.8s
    @Transactional
    @Test
    void testInsertV1() {
        sw.start("testInsertV1");
        myUserDao.insertBatchV1(getTestData());
        sw.stop();
        log.info(sw.prettyPrint());
    }

    // 出现异常Thread stack overrun:  266728 bytes used of a 286720 byte stack, and 20000 bytes needed.  Use 'mysqld --thread_stack=#' to specify a bigger stack.
    // 需要在my.ini中增大thread_stack，thread_stack表示mysql给每个连接分配的内存堆栈大小
    // 30w 7.9s

    ExecutorService executor = new ThreadPoolExecutor(
            5,
            5,
            30,
            TimeUnit.SECONDS,
            new LinkedBlockingQueue<>(),
            r -> {
                Thread thread = new Thread(r);
                thread.setName("test-insert-t-" + r.hashCode());
                return thread;
            });

    @Transactional
    @Test
    void testInsertV2() {
        sw.start("testInsertV2");
        List<MyUser> myUserList = getTestData();
//        int size = 0;
//        List<MyUser> partList = new ArrayList<>();
//        for (MyUser myUser : myUserList) {
//            partList.add(myUser);
//            size++;
//            if (size % 10000 == 0) {
//                myUserDao.insertBatchV2(partList);
//                partList = new ArrayList<>();
//            }
//        }
        int partI = 0;
        for (int i = 0; i <= myUserList.size(); i = i + 10000) {
            if (i != 0 && i % 10000 == 0) {
                log.info(partI + ":" + i);
                int finalPartI = partI;
                int finalI = i;
                executor.execute(new Runnable() {
                    @Override
                    public void run() {
                        myUserDao.insertBatchV2(myUserList.subList(finalPartI, finalI));
                    }
                });
                partI = i;
            }
        }
        sw.stop();
        log.info(sw.prettyPrint());
    }

    // 30w 30s
    @Transactional
    @Test
    void testInsertV3() {
        sw.start("testInsertV3");
        myUserDao.insertBatchV3(getTestData());
        sw.stop();
        log.info(sw.prettyPrint());
    }

    // 30w 7s
    @Transactional
    @Test
    void testInsertV4() {
        sw.start("testInsertV4");
        List<MyUser> myUserList = getTestData();
        int partI = 0;
        for (int i = 0; i <= myUserList.size(); i = i + 10000) {
            if (i != 0 && i % 10000 == 0) {
                log.info(partI + ":" + i);
                myUserDao.insertBatchV4(myUserList.subList(partI, i));
                partI = i;
            }
        }
        sw.stop();
        log.info(sw.prettyPrint());
    }
}
