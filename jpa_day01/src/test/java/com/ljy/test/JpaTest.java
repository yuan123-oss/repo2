package com.ljy.test;

import com.ljy.domain.Customer;
import com.ljy.utils.JpaUtils;
import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class JpaTest {

    /**
     *
     * JPA操作步骤
     * 1.加载配置文件创建工厂(实体管理类工厂）对象
     * 2.通过实体管理类工厂获取实体管理器
     * 3.获取事务对象，开启事务
     * 4.完成增删查改操作
     * 5.提交事务(回滚事务）
     * 6.释放资源
     */
    @Test
    public void testSave(){

//        //1.加载配置文件创建工厂(实体管理类工厂）对象
//        EntityManagerFactory factory = Persistence.createEntityManagerFactory("myJpa");
//
//        //2.通过实体管理类工厂获取实体管理器
//        EntityManager em = factory.createEntityManager();

        EntityManager em = JpaUtils.getEntityManager();

        //3.获取事务对象，开启事务
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        //4.完成增删查改操作,保存一个客户到数据库中
        Customer customer = new Customer();
        customer.setCustName("李金源");
        customer.setCustIndustry("QST");

        em.persist(customer);//保存

        //5.提交事务(回滚事务）
        tx.commit();

        //6.释放资源
        em.close();
//        factory.close();


    }

    /**
     * 根据id客户
     */
    @Test
    public void testFind(){

        //1.通过工具类获取EntityManager
        EntityManager em = JpaUtils.getEntityManager();
        //2.开启事务
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        //3.增删查改
        Customer customer = em.find(Customer.class, 1l);
        System.out.print(customer);
        //4.提交事务
        tx.commit();
        //5.释放资源
        em.close();
    }


    /**
     * 根据id客户(懒加载)
     */
    @Test
    public void testReference(){
        //1.通过工具类获取EntityManager
        EntityManager em = JpaUtils.getEntityManager();
        //2.开启事务
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        //3.增删查改
        Customer customer = em.getReference(Customer.class, 1l);
        System.out.print(customer);
        //4.提交事务
        tx.commit();
        //5.释放资源
        em.close();
    }


    /**
     * 删除客户
     */
    @Test
    public void testRemove(){
        //1.通过工具类获取EntityManager
        EntityManager em = JpaUtils.getEntityManager();
        //2.开启事务
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        //3.增删查改---删除客户

        //3.1根据id查询用户
        Customer customer = em.getReference(Customer.class, 1l);
        //3.2调用remove方法完成删除操作
        em.remove(customer);
        //4.提交事务
        tx.commit();
        //5.释放资源
        em.close();
    }

    /**
     * 更新客户
     */
    @Test
    public void testUpdate(){
        //1.通过工具类获取EntityManager
        EntityManager em = JpaUtils.getEntityManager();
        //2.开启事务
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        //3.增删查改---更新客户
        //3.1根据id查询用户
        Customer customer = em.getReference(Customer.class, 1l);
        //3.2更新客户
        customer.setCustAddress("青岛城阳区");
        customer.setCustName("季秀秀");
        em.merge(customer);
        //4.提交事务
        tx.commit();
        //5.释放资源
        em.close();
    }


}
