package com.wuhulala.base.support;


import org.apache.commons.collections4.MapUtils;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ApplicationObjectSupport;
import org.springframework.util.Assert;

import java.lang.annotation.Annotation;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author wuhulala<br>
 * @date 2018/7/16<br>
 * @link https://github.com/wuhulala/rpc3-spring-cloud<br>
 * @description Spring 上下文<br>
 * @since v1.0<br>
 */
public class SpringContext extends ApplicationObjectSupport implements
        InitializingBean {

    /**
     * spring 应用上下文
     */
    private static ApplicationContext applicationContext = null;

    @Override
    public void afterPropertiesSet() throws Exception {
        SpringContext.applicationContext = super.getApplicationContext();
        Assert.notNull(applicationContext, "Spring ApplicationContext 不能为空!");
    }

    /**
     * 根据名称获取Bean
     *
     */
    public static Object getBean(String beanName) {
        return applicationContext.getBean(beanName);
    }

    /**
     * 根据名称，类型获取Bean
     *
     */
    public static <T> T getBean(String beanName, Class<T> requiredType) {
        return applicationContext.getBean(beanName, requiredType);
    }

    /**
     * 根据类型获取Bean Map
     *
     */
    public static <T> Map<String, T> getBeanMapOfType(Class<T> type) {
        return applicationContext.getBeansOfType(type);
    }

    /**
     * 根据类型获取Bean List
     *
     */
    public static <T> List<T> getBeanListOfType(Class<T> type) {
        Map<String, T> beanMap = getBeanMapOfType(type);
        List<T> list = new ArrayList<>();
        if (MapUtils.isNotEmpty(beanMap)) {
            list.addAll(beanMap.values());
        }
        return list;
    }

    /**
     * 根据注解获取bean list
     *
     */
    public static List<Object> getBeanListOfAnnotation(Class<? extends Annotation> ann) {
        Map<String, Object> beanMap = applicationContext.getBeansWithAnnotation(ann);
        List<Object> list = new ArrayList<>();
        if (MapUtils.isNotEmpty(beanMap)) {
            list.addAll(beanMap.values());
        }
        return list;
    }

    /**
     * 根据类型获取Bean Array
     *
     */
    public static <T> T[] getBeanArrayOfType(Class<T> type) {
        List<T> list = getBeanListOfType(type);
        T[] array = (T[]) Array.newInstance(type, list.size());
        array = list.toArray(array);
        return array;
    }


}