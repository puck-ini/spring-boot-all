package org.zchzh.mongo.config;//package com.zchzh.mongodbtest.config;
//
//import com.mongodb.MongoClient;
//import com.mongodb.MongoClientOptions;
//import com.mongodb.MongoCredential;
//import com.mongodb.ServerAddress;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.Primary;
//import org.springframework.data.mapping.context.MappingContext;
//import org.springframework.data.mongodb.MongoDbFactory;
//import org.springframework.data.mongodb.core.MongoTemplate;
//import org.springframework.data.mongodb.core.SimpleMongoDbFactory;
//import org.springframework.data.mongodb.core.convert.MappingMongoConverter;
//import org.springframework.data.mongodb.core.mapping.MongoPersistentEntity;
//import org.springframework.data.mongodb.core.mapping.MongoPersistentProperty;
//import org.springframework.data.mongodb.gridfs.GridFsTemplate;
//
//import javax.net.ssl.SSLSocketFactory;
//import java.util.ArrayList;
//import java.util.List;
//
///**
// * @author zengchzh
// * @date 2021/1/11
// */
//
//@Slf4j
//@Configuration
//public class MongoSSLConfig {
//
//    @Primary
//    @Bean(name = "mongoDbFactory")
//    public MongoDbFactory mongoDbFactory(){
//        ServerAddress serverAddress = new ServerAddress("localhost", 27017);
////        List<MongoCredential> mongoCredentialList = new ArrayList<>();
////        mongoCredentialList.add(MongoCredential.createScramSha1Credential(
////                "test", "admin","test".toCharArray()));
//        log.info("===========" + System.getProperty("javax.net.ssl.trustStore") + "=============");
//        System.setProperty("javax.net.ssl.trustStore","D:\\mongo3.2\\ca\\mongokeystore.jks");
//        System.setProperty("javax.net.ssl.trustStorePassword","mongodb");
////        System.setProperty ("javax.net.ssl.keyStore","D:\\mongo3.2\\ca\\mongodb-cert.key");
////        System.setProperty ("javax.net.ssl.keyStorePassword","mongodb");
//        MongoClientOptions options = MongoClientOptions.builder()
////                .socketFactory(SSLSocketFactory.getDefault())
//                .sslEnabled(true) //开启SSL连接
//                .sslInvalidHostNameAllowed(true) //不检查证书域名
//                .build();
//        return new SimpleMongoDbFactory(new MongoClient(serverAddress,options),
//                "testdb1");
//
//    }
//
//    @Primary
//    @Bean(name = "mongoDbFactoryTemp")
//    public MongoDbFactory mongoDbFactoryTemp(){
//        ServerAddress serverAddress = new ServerAddress("localhost", 27017);
////        List<MongoCredential> mongoCredentialList = new ArrayList<>();
////        mongoCredentialList.add(MongoCredential.createScramSha1Credential(
////                "test", "admin","test".toCharArray()));
//        log.info("===========" + System.getProperty("javax.net.ssl.trustStore") + "=============");
//        System.setProperty("javax.net.ssl.trustStore","D:\\mongo3.2\\ca\\mongokeystore.jks");
//        System.setProperty("javax.net.ssl.trustStorePassword","mongodb");
////        System.setProperty ("javax.net.ssl.keyStore","D:\\mongo3.2\\ca\\mongodb-cert.key");
////        System.setProperty ("javax.net.ssl.keyStorePassword","mongodb");
//        MongoClientOptions options = MongoClientOptions.builder()
////                .socketFactory(SSLSocketFactory.getDefault())
//                .sslEnabled(true) //开启SSL连接
//                .sslInvalidHostNameAllowed(true) //不检查证书域名
//                .build();
//        return new SimpleMongoDbFactory(new MongoClient(serverAddress,options),
//                "testdb");
//
//    }
//
//    @Primary
//    @Bean(name = "converter")
//    public MappingMongoConverter mappingMongoConverter(@Qualifier("mongoDbFactory") MongoDbFactory mongoDbFactory,
//                                                       MappingContext<? extends MongoPersistentEntity<?>, MongoPersistentProperty> mappingContext){
//        return new MappingMongoConverter(mongoDbFactory,mappingContext);
//    }
//
//    @Primary
//    @Bean(name = "converter")
//    public MappingMongoConverter mappingMongoConverterTemp(@Qualifier("mongoDbFactoryTemp") MongoDbFactory mongoDbFactory,
//                                                       MappingContext<? extends MongoPersistentEntity<?>, MongoPersistentProperty> mappingContext){
//        return new MappingMongoConverter(mongoDbFactory,mappingContext);
//    }
//
//    @Bean
//    public MongoTemplate mongoTemplate(@Qualifier(value = "mongoDbFactory") MongoDbFactory mongoDbFactory){
//        return new MongoTemplate(mongoDbFactory);
//    }
//
//    @Bean
//    public GridFsTemplate gridFsTemplate(@Qualifier(value = "mongoDbFactory") MongoDbFactory mongoDbFactory,
//                                         @Qualifier(value = "converter") MappingMongoConverter mappingMongoConverter) {
//        return new GridFsTemplate(mongoDbFactory,mappingMongoConverter);
//    }
//
//    @Bean
//    public MongoTemplate mongoTemplateTemp(@Qualifier(value = "mongoDbFactoryTemp") MongoDbFactory mongoDbFactory){
//        return new MongoTemplate(mongoDbFactory);
//    }
//
//    @Bean
//    public GridFsTemplate gridFsTemplateTemp(@Qualifier(value = "mongoDbFactoryTemp") MongoDbFactory mongoDbFactory,
//                                         @Qualifier(value = "converter") MappingMongoConverter mappingMongoConverter) {
//        return new GridFsTemplate(mongoDbFactory,mappingMongoConverter);
//    }
//
//
//}
