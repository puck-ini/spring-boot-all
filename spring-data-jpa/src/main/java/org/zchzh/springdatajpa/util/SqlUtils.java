package org.zchzh.springdatajpa.util;



import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author zengchzh
 * @date 2021/8/5
 */
public class SqlUtils {

    /**
     *  找到类所有字段包括父类的集合
     * @param clazz 类Class
     * @return 类所有字段的集合
     */
    public static List<Field> getAllField(Class<?> clazz) {
        List<Field> fieldList = new ArrayList<>();
        Field[] fields = clazz.getDeclaredFields();
        if (fields.length != 0) {
            fieldList.addAll(Arrays.asList(fields));
        }
        Class<?> superclass = clazz.getSuperclass();
        // 如果父类是Object, 直接返回
        if (superclass == Object.class) {
            return fieldList;
        }
        // 递归获取所有的父级的Field
        List<Field> superClassFieldList = getAllField(superclass);
        if (!superClassFieldList.isEmpty()) {
            superClassFieldList.stream()
                    // 去除重复字段
                    .filter(field -> !fieldList.contains(field))
                    .forEach(fieldList::add);
        }
        return fieldList;
    }

    /**
     * 过滤Field
     * @Param sqlFilters
     * @Param fields
     * return 处理结果
     */
    public static List<Field> getFilterField(List<Field> fields, List<SqlFilter> sqlFilters){
        // 获取SqlFilter所有属性字段
        List<String> fieldList = sqlFilters.stream().map(SqlFilter::getProperty).distinct().collect(Collectors.toList());
        Iterator<Field> iterator = fields.iterator();
        while (iterator.hasNext()){
            Field field = iterator.next();
            field.setAccessible(true);
            if (fieldList.contains(field.getName())){
                iterator.remove();
            }
        }
        return fields;
    }
}
