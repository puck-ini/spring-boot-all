package org.zchzh.springdatajpa.convert;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.function.BiConsumer;
import java.util.function.Function;

/**
 * @author linhw
 * @date 2021/04/29 11:30
 */
public final class TreeUtil {

    /**
     * 将集合封装成树型结构
     */
    public static <T, ID extends Serializable> List<T> tree(List<T> dataList,
                                                            Function<T, ID> idFunction,
                                                            Function<T, ID> pidFunction,
                                                            BiConsumer<T, List<T>> childrenConsumer,
                                                            ID pidDefaultValue
    ) {
        // 根节点对象集合
        List<T> rootList = new ArrayList<>();
        // 非根节点对象集合
        List<T> otherList = new ArrayList<>();

        TreeUtil.dataClassification(dataList, rootList, otherList, pidFunction, pidDefaultValue);

        for (T t : rootList) {
            processData(t, otherList, idFunction, pidFunction, childrenConsumer);
        }

        return rootList;
    }

//
//    /**
//     * 将树型结构转换成集合
//     */
//    public static <T> List<T> tree2List(T tree,
//                                        Function<T, List<T>> childrenFunction) {
//
//        if (ObjectUtil.isAnyNull(tree)) {
//            throw new NullPointerException("树对象不能为空！");
//        }
//
//        List<T> ts = new ArrayList<>();
//        Queue<T> queue = new LinkedList<>();
//        queue.add(tree);
//
//        while (!queue.isEmpty()) {
//            T t = queue.poll();
//            ts.add(t);
//
//            List<T> tChildren = childrenFunction.apply(t);
//            if (ObjectUtil.isNonNull(tChildren)) {
//                queue.addAll(tChildren);
//            }
//        }
//
//        return ts;
//    }
//
//    /**
//     * 将树型结构转换成集合
//     */
//    public static <T> List<T> tree2List(List<T> treeList,
//                                        Function<T, List<T>> childrenFunction) {
//
//        if (ObjectUtil.isAnyNull(treeList)) {
//            throw new NullPointerException("树对象不能为空！");
//        }
//
//        List<T> ts = new ArrayList<>();
//        for (T t : treeList) {
//            ts.addAll(tree2List(t, childrenFunction));
//        }
//
//        return ts;
//    }

    private static <T, ID extends Serializable> void processData(T t,
                                                                 List<T> otherList,
                                                                 Function<T, ID> idFunction,
                                                                 Function<T, ID> pidFunction,
                                                                 BiConsumer<T, List<T>> childrenConsumer) {
        if (otherList.size() == 0) {
            return;
        }

        ID id = idFunction.apply(t);

        List<T> children = new ArrayList<>();
        Iterator<T> iterator = otherList.iterator();
        while (iterator.hasNext()) {
            T other = iterator.next();
            ID otherParentId = pidFunction.apply(other);
            if (Objects.equals(id, otherParentId)) {
                children.add(other);
                iterator.remove();
            }
        }

        for (T child : children) {
            TreeUtil.processData(child, otherList, idFunction, pidFunction, childrenConsumer);
            childrenConsumer.accept(t, children);
        }
    }

    private static <T, ID extends Serializable> void dataClassification(List<T> dataList,
                                                                        List<T> rootList,
                                                                        List<T> otherList,
                                                                        Function<T, ID> pidFunction,
                                                                        ID pidDefaultValue) {
        for (T t : dataList) {
            ID pid = pidFunction.apply(t);
            if (Objects.equals(pid, pidDefaultValue)) {
                rootList.add(t);
            } else {
                otherList.add(t);
            }
        }
    }

    private TreeUtil() {
    }

    public static void main(String[] args) {

    }
}
