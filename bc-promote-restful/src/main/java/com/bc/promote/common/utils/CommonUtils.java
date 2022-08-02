package com.bc.promote.common.utils;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import org.apache.commons.collections4.CollectionUtils;

import java.lang.reflect.Field;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 通用工具类
 *
 * @author 万爷
 * @date 2022/07/26
 */
public class CommonUtils {

    /**
     * Stream分割list集合
     * @param list 集合数据
     * @param splitSize 几个分割一组
     * @return 集合分割后的集合
     */
    public static <T> List<List<T>> splitList(List<T> list, int splitSize) {
        //判断集合是否为空
        if (CollectionUtils.isEmpty(list))
            return Collections.emptyList();
        //计算分割后的大小
        int maxSize = (list.size() + splitSize - 1) / splitSize;
        //开始分割
        return Stream.iterate(0, n -> n + 1)
                .limit(maxSize)
                .parallel()
                .map(a -> list.parallelStream().skip(a * splitSize).limit(splitSize).collect(Collectors.toList()))
                .filter(b -> !b.isEmpty())
                .collect(Collectors.toList());
    }

    /**
     * 将对象转成 Map
     * @param obj
     * @return
     * @throws Exception
     */
    public static HashMap<String, Object> convertToMap(Object obj){
        HashMap<String, Object> map = new HashMap<String, Object>();
        try{
            Field[] fields = obj.getClass().getDeclaredFields();
            for (int i = 0, len = fields.length; i < len; i++) {
                String varName = fields[i].getName();
                boolean accessFlag = fields[i].isAccessible();
                fields[i].setAccessible(true);

                Object o = fields[i].get(obj);
                if (o != null)
                    map.put(varName, o.toString());

                fields[i].setAccessible(accessFlag);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return map;
    }

    /**
     * 将对象转成表字段 Map
     * @param obj
     * @return
     * @throws Exception
     */
    public static HashMap<String, Object> convertToColumnMap(Object obj){
        HashMap<String, Object> map = new HashMap<String, Object>();
        try{
            Field[] fields = obj.getClass().getDeclaredFields();
            for (int i = 0, len = fields.length; i < len; i++) {
                String varName = fields[i].getName();
                boolean accessFlag = fields[i].isAccessible();
                fields[i].setAccessible(true);

                Object o = fields[i].get(obj);
                if (o != null){
                    if(fields[i].isAnnotationPresent(TableField.class)){
                        String tableFieldName = fields[i].getAnnotation(TableField.class).value();
                        map.put(tableFieldName, o.toString());
                    }else if(fields[i].isAnnotationPresent(TableId.class)){
                        String tableFieldName = fields[i].getAnnotation(TableId.class).value();
                        map.put(tableFieldName, o.toString());
                    }
                }
                fields[i].setAccessible(accessFlag);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return map;
    }

}
