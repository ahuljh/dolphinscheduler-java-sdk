package com.github.ahuljh.common;

import org.apache.commons.beanutils.BeanUtils;

import java.util.Map;

public class MapToBeanConverter {
    public static <T> T convertMapToBean(Map<String, Object> map, Class<T> beanClass) {
        try {
            T bean = beanClass.getDeclaredConstructor().newInstance(); // 创建 Bean 实例
            BeanUtils.populate(bean, map); // 将 Map 的值填充到 Bean 中
            return bean;
        } catch (Exception e) {
            throw new RuntimeException("Failed to convert map to bean", e);
        }
    }
}
