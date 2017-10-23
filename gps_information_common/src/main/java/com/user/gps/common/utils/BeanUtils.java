package com.user.gps.common.utils;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

/**
 * 工具类方法,提供反射方式操作JavaBean属性
 * 
 * @author minchun
 *
 */
public class BeanUtils {

    private static final Logger logger = LoggerFactory.getLogger(BeanUtils.class);

    public static Map<String, Object> populate(Object bean) {
        Map<String, Object> properties = new HashMap<>();
        try {

            BeanInfo beanInfo = Introspector.getBeanInfo(bean.getClass());

            PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
            if (propertyDescriptors != null && propertyDescriptors.length > 0) {
                String beanFieldName = null;
                Object beanFieldValue = null;
                for (PropertyDescriptor pd : propertyDescriptors) {
                    beanFieldName = pd.getName();
                    if (!beanFieldName.equals("class")) {
                        Method readMethod = pd.getReadMethod();
                        beanFieldValue = readMethod.invoke(bean, new Object[0]);
                        if (beanFieldValue != null && !StringUtils.isEmpty(beanFieldValue)) {
                            properties.put(beanFieldName, beanFieldValue);
                        }
                    }
                }
            }

        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }

        return properties;
    }
}
