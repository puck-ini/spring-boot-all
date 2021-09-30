package org.zchzh.querydsl.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QOtherEntity is a Querydsl query type for OtherEntity
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QOtherEntity extends EntityPathBase<OtherEntity> {

    private static final long serialVersionUID = 1163204310L;

    public static final QOtherEntity otherEntity = new QOtherEntity("otherEntity");

    public final DateTimePath<java.time.LocalDateTime> createTime = createDateTime("createTime", java.time.LocalDateTime.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final DateTimePath<java.time.LocalDateTime> updateTime = createDateTime("updateTime", java.time.LocalDateTime.class);

    public QOtherEntity(String variable) {
        super(OtherEntity.class, forVariable(variable));
    }

    public QOtherEntity(Path<? extends OtherEntity> path) {
        super(path.getType(), path.getMetadata());
    }

    public QOtherEntity(PathMetadata metadata) {
        super(OtherEntity.class, metadata);
    }

}

