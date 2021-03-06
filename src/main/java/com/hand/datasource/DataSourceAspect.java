package com.hand.datasource;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;

import java.lang.reflect.Method;


/**
 * 创建DataSource的切面方法  弃用  在junit中才用before设置数据源了
 */
//@Aspect
//@Component
public class DataSourceAspect {



      /**
       * 拦截目标方法，获取由@DataSource指定的数据源标识，设置到线程存储中以便切换数据源
       *    拦截不到。。。
       * @param point
       * @throws Exception
       */
      @Before("execution(* com.hand.service.*.*())")
      public void intercept(JoinPoint point) throws Exception {
          System.out.println(111);
         Class<?> target = point.getTarget().getClass();
         MethodSignature signature = (MethodSignature) point.getSignature();
         // 默认使用目标类型的注解，如果没有则使用其实现接口的注解
         for (Class<?> clazz : target.getInterfaces()) {
             resolveDataSource(clazz, signature.getMethod());
         }
         resolveDataSource(target, signature.getMethod());
     }
 
     /**
      * 提取目标对象方法注解和类型注解中的数据源标识
      *
      * @param clazz
      * @param method
      */
     private void resolveDataSource(Class<?> clazz, Method method) {
         try {
             Class<?>[] types = method.getParameterTypes();
             // 默认使用类型注解
             if (clazz.isAnnotationPresent(DataSource.class)) {
                 DataSource source = clazz.getAnnotation(DataSource.class);
                 System.out.println("选择数据源===="+source.value());
                 DynamicDataSourceHolder.setDataSource(source.value());
             }
             // 方法注解可以覆盖类型注解
             Method m = clazz.getMethod(method.getName(), types);
             if (m != null && m.isAnnotationPresent(DataSource.class)) {
                 DataSource source = m.getAnnotation(DataSource.class);
                 System.out.println("选择数据源===="+source.value());
                 DynamicDataSourceHolder.setDataSource(source.value());
             }
         } catch (Exception e) {
             System.out.println(clazz + ":" + e.getMessage());
         }
     }
 
 }