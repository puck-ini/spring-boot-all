package org.zchzh.querydsl.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QTestEntity is a Querydsl query type for TestEntity
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QTestEntity extends EntityPathBase<TestEntity> {

    private static final long serialVersionUID = 734103282L;

    public static final QTestEntity testEntity = new QTestEntity("testEntity");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath info = createString("info");

    public final StringPath msg = createString("msg");

    public final NumberPath<Long> otherId = createNumber("otherId", Long.class);

    public QTestEntity(String variable) {
        super(TestEntity.class, forVariable(variable));
    }

    public QTestEntity(Path<? extends TestEntity> path) {
        super(path.getType(), path.getMetadata());
    }

    public QTestEntity(PathMetadata metadata) {
        super(TestEntity.class, metadata);
    }

}

