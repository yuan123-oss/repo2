package com.ljy.test;


import com.ljy.utils.JpaUtils;
import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import java.util.List;

/**
 * 测试jpql语句
 */
public class JpqlTest {


    /**
     * Jpql查询全部
     */
    @Test
    public void testFindAll(){
        //1.获取EntityManager对象
        EntityManager em = JpaUtils.getEntityManager();

        //2.开启事务
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        //3.查询全部
        //创建query查询对象，query对象才是执行jpql的对象
        String jpql = "from Customer";
        Query query = em.createQuery(jpql);

        //发送查询，并封装结果集
        List list = query.getResultList();
        for (Object lists : list) {
            System.out.println(lists);
        }

        //4.提交事务
        tx.commit();

        //5.释放资源
        em.close();

    }


    /**
     * Jpql倒序查询全部
     */
    @Test
    public void testOrders(){
        //1.获取EntityManager对象
        EntityManager em = JpaUtils.getEntityManager();

        //2.开启事务
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        //3.查询全部
        String jpql = "from Customer order by custId desc";
        Query query = em.createQuery(jpql);//创建query查询对象，query对象才是执行jpql的对象

        //发送查询，并封装结果集
        List list = query.getResultList();
        for (Object lists : list) {
            System.out.println(lists);
        }

        //4.提交事务
        tx.commit();

        //5.释放资源
        em.close();

    }

    /**
     * Jpql统计查询客户总数
     */
    @Test
    public void testCount(){
        //1.获取EntityManager对象
        EntityManager em = JpaUtils.getEntityManager();

        //2.开启事务
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        //3.查询
        //创建query查询对象，query对象才是执行jpql的对象
        String jpql = "select count(custId) from Customer";
        Query query = em.createQuery(jpql);

        Object result = query.getSingleResult();
        System.out.println(result);
        //4.提交事务
        tx.commit();

        //5.释放资源
        em.close();

    }


    /**
     * Jpql分页查询
     */
    @Test
    public void testPage(){
        //1.获取EntityManager对象
        EntityManager em = JpaUtils.getEntityManager();

        //2.开启事务
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        //3.分页查询全部

        //创建query查询对象，query对象才是执行jpql的对象
        String jpql = "from Customer";
        Query query = em.createQuery(jpql);

        //对参数进行赋值---分页参数
        //起始索引
        query.setFirstResult(0);
        //每页查询条数
        query.setMaxResults(2);

        //发送查询，并封装结果集
        List list = query.getResultList();
        for (Object lists : list) {
            System.out.println(lists);
        }
        //4.提交事务
        tx.commit();

        //5.释放资源
        em.close();

    }


    /**
     * Jpql条件查询
     */
    @Test
    public void testCondition(){
        //1.获取EntityManager对象
        EntityManager em = JpaUtils.getEntityManager();

        //2.开启事务
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        //3.分页查询全部

        //3.1创建query查询对象，query对象才是执行jpql的对象
        String jpql = "from Customer where custAddress like ?";
        Query query = em.createQuery(jpql);

        //3.2对参数进行赋值---占位符参数
        query.setParameter(1,"青岛%");
        //3.3发送查询，并封装结果集
        List list = query.getResultList();
        for (Object lists : list) {
            System.out.println(lists);
        }
        //4.提交事务
        tx.commit();

        //5.释放资源
        em.close();

    }

}
