package org.zchzh.mongo.config;//package com.zchzh.mongodbtest;
//
//import com.mongodb.MongoClient;
//import com.mongodb.MongoClientOptions;
//import com.mongodb.MongoCredential;
//import com.mongodb.ServerAddress;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.data.mongodb.MongoDbFactory;
//import org.springframework.data.mongodb.core.SimpleMongoDbFactory;
//
//import java.util.ArrayList;
//import java.util.Collections;
//import java.util.List;
//
///**
// * @author zengchzh
// * @date 2021/1/11
// */
//
//@Configuration
//public class MongoConfig {
//
////    @Bean
////    public MongoClientOptions mongoClientOptions(){
////        System.setProperty ("javax.net.ssl.keyStore","D:\\mongo3.2\\ca\\mongodb.pem");
//////        System.setProperty ("javax.net.ssl.keyStorePassword","PASSWORD");
////        MongoClientOptions.Builder builder = MongoClientOptions.builder();
////        MongoClientOptions options=builder.sslEnabled(true).build();
////        return options;
////    }
//
//    // 覆盖容器中默认的MongoDbFacotry Bean
//    @Bean
//    @Autowired
//    public MongoDbFactory mongoDbFactory(MongoProperties properties) {
//
//        // 客户端配置（连接数，副本集群验证）
//        MongoClientOptions.Builder builder = new MongoClientOptions.Builder();
//        builder.connectionsPerHost(properties.getMaxConnectionsPerHost());
//        builder.minConnectionsPerHost(properties.getMinConnectionsPerHost());
//        if (properties.getReplicaSet() != null) {
//            builder.requiredReplicaSetName(properties.getReplicaSet());
//        }
//        builder.threadsAllowedToBlockForConnectionMultiplier(
//                properties.getThreadsAllowedToBlockForConnectionMultiplier());
//        builder.serverSelectionTimeout(properties.getServerSelectionTimeout());
//        builder.maxWaitTime(properties.getMaxWaitTime());
//        builder.maxConnectionIdleTime(properties.getMaxConnectionIdleTime());
//        builder.maxConnectionLifeTime(properties.getMaxConnectionLifeTime());
//        builder.connectTimeout(properties.getConnectTimeout());
//        builder.socketTimeout(properties.getSocketTimeout());
//        // builder.socketKeepAlive(properties.getSocketKeepAlive());
//        System.setProperty("javax.net.ssl.trustStore", "D:\\mongo3.2\\ca\\mongodb.pem");
//        builder.sslEnabled(properties.getSslEnabled());
//        builder.sslInvalidHostNameAllowed(properties.getSslInvalidHostNameAllowed());
//        builder.alwaysUseMBeans(properties.getAlwaysUseMBeans());
//        builder.heartbeatFrequency(properties.getHeartbeatFrequency());
//        builder.minHeartbeatFrequency(properties.getMinHeartbeatFrequency());
//        builder.heartbeatConnectTimeout(properties.getHeartbeatConnectTimeout());
//        builder.heartbeatSocketTimeout(properties.getHeartbeatSocketTimeout());
//        builder.localThreshold(properties.getLocalThreshold());
//        MongoClientOptions mongoClientOptions = builder.build();
//
//        // MongoDB地址列表
//        List<ServerAddress> serverAddresses = new ArrayList<ServerAddress>();
//        for (String address : properties.getAddress()) {
//            String[] hostAndPort = address.split(":");
//            String host = hostAndPort[0];
//            Integer port = Integer.parseInt(hostAndPort[1]);
//            ServerAddress serverAddress = new ServerAddress(host, port);
//            serverAddresses.add(serverAddress);
//        }
//
//
//         //连接认证
//         MongoCredential mongoCredential = null;
//         if (properties.getUsername() != null) {
//          mongoCredential = MongoCredential.createScramSha1Credential(
//                  properties.getUsername(), properties.getAuthenticationDatabase() != null
//                          ? properties.getAuthenticationDatabase() : properties.getDatabase(),
//                  properties.getPassword().toCharArray());
//         }
//
//         //创建认证客户端
//         MongoClient mongoClient = new MongoClient(serverAddresses, Collections.singletonList(mongoCredential), mongoClientOptions);
//
////        // 创建非认证客户端
////        MongoClient mongoClient = new MongoClient(serverAddresses, mongoClientOptions);
//
//        // 创建MongoDbFactory
//        MongoDbFactory mongoDbFactory = new SimpleMongoDbFactory(mongoClient, properties.getDatabase());
//        return mongoDbFactory;
//    }
//}
