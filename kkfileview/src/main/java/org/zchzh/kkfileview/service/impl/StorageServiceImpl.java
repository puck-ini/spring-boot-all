package org.zchzh.kkfileview.service.impl;

import org.springframework.stereotype.Service;
import org.zchzh.kkfileview.service.StorageService;

import java.io.*;
import java.nio.channels.FileChannel;
import java.util.Objects;

/**
 * @author zengchzh
 * @date 2021/8/20
 */
@Service
public class StorageServiceImpl implements StorageService {

    private String path = "D:\\testdata\\";

    @Override
    public void upload(String fileName, InputStream is) {
        File file = new File(path + fileName);
        try (FileChannel source = ((FileInputStream) is).getChannel();
             FileChannel target = new RandomAccessFile(file, "rw").getChannel()) {
            source.transferTo(0, source.size(), target);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public InputStream getInputStream(String fileName) {
        File file = new File(path + fileName);
        if (!file.exists()) {
            return null;
        }
        try {
            return new FileInputStream(file);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
