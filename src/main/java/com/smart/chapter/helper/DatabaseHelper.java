package com.smart.chapter.helper;

import com.smart.chapter.util.CollectionUtil;
import com.smart.chapter.util.PropsUtil;
import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.MapListHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.Properties;

/**
 * @description: 数据库工具类
 * @author: daihanguang
 * @create: 2019-05-06 11:48
 */
public final class DatabaseHelper {

    private static final Logger LOGGER = LoggerFactory.getLogger(DatabaseHelper.class);

    private static final QueryRunner QUERY_RUNNER;

    private static final ThreadLocal<Connection> CONNECTION_HOLDER;

    private static final BasicDataSource DATA_SOURCE;


    static {
        QUERY_RUNNER = new QueryRunner();
        CONNECTION_HOLDER = new ThreadLocal<Connection>();
        DATA_SOURCE = new BasicDataSource();
        Properties properties = PropsUtil.loadProps("config.properties");
        String driver = PropsUtil.getString(properties, "jdbc.driver");
        String url = PropsUtil.getString(properties, "jdbc.url");
        String username = PropsUtil.getString(properties, "jdbc.username");
        String password = PropsUtil.getString(properties, "jdbc.password");
        DATA_SOURCE.setDriverClassName(driver);
        DATA_SOURCE.setUrl(url);
        DATA_SOURCE.setUsername(username);
        DATA_SOURCE.setPassword(password);
    }

    /**
     * 获取数据库连接
     * @return
     */
    public static Connection getConnection(){
        Connection connection = CONNECTION_HOLDER.get();
        if(connection == null){
            try {
                connection = DATA_SOURCE.getConnection();
            } catch (SQLException e) {
                LOGGER.info("get connection failure", e);
            } finally {
                CONNECTION_HOLDER.set(connection);
            }
        }
        return  connection;
    }

    /**
     * 关闭数据库连接
     */
    public static void closeCollection(){
        Connection connection = CONNECTION_HOLDER.get();
        if(connection != null){
            try {
                connection.close();
            } catch (SQLException e) {
                LOGGER.info("close collection failure", e);
            } finally {
                CONNECTION_HOLDER.remove();
            }
        }
    }

    /**
     * 获取实体对象集合
     * @param conn
     * @param clazz
     * @param sql
     * @param objs
     * @param <T>
     * @return
     */
    public static <T> List<T> queryEntityList(Connection conn, Class<T> clazz, String sql, Object... objs){
        List<T> entityList = null;
        try {
            entityList = QUERY_RUNNER.execute(conn, sql, new BeanListHandler(clazz), objs);
        } catch (SQLException e) {
            LOGGER.info("query entity list failure", e);
        } finally {
            closeCollection();
        }
        return entityList;
    }

    /**
     * 执行查询语句
     * @param sql
     * @param objs
     * @return
     */
    public static List<Map<String, Object>> excuteQuery(String sql, Object... objs){
        List<Map<String, Object>> result = null;
        try {
            Connection connection = getConnection();
            result = QUERY_RUNNER.query(connection, sql, new MapListHandler(), objs);
        } catch (SQLException e) {
            LOGGER.info("query entity list failure", e);
            throw new RuntimeException(e);
        }
        return  result;
    }

    /**
     * 执行更新语句（包括insert,update和delete)
     * @param sql
     * @param objs
     * @return
     */
    public static int excuteUpdate(String sql, Object... objs){
        int rows = 0;
        try {
            Connection connection = getConnection();
            rows = QUERY_RUNNER.update(connection, sql, objs);
        } catch (SQLException e) {
            LOGGER.info("excute udpate failure", e);
            throw new RuntimeException(e);
        }
        return rows;
    }

    /**
     * 插入实体
     * @param clazz
     * @param fieldMap
     * @param <T>
     * @return
     */
    public static <T> Boolean insertEnity(Class<T> clazz, Map<String, Object> fieldMap){
        if(CollectionUtil.isEmpty(fieldMap)){
            LOGGER.info("can not insert : fieldMap is Empty ");
            return false;
        }
        String sql = "INSERT INTO " + getTableName(clazz);
        StringBuilder columnNames = new StringBuilder("(");
        StringBuilder colunnValues = new StringBuilder("(");
        for (Map.Entry<String, Object> entry : fieldMap.entrySet()){
            String columnName = entry.getKey();
            columnNames.append(columnName).append(", ");
            colunnValues.append("?, ");
        }
        columnNames.replace(columnNames.lastIndexOf(", "), columnNames.length(), ")");
        colunnValues.replace(colunnValues.lastIndexOf(", "), colunnValues.length(), ")");
        sql = sql + columnNames.toString() + " VALUES " + colunnValues.toString();
        return excuteUpdate(sql, fieldMap.values()) == 1;
    }

    /**
     * 获取表名
     * @param clazz
     * @return
     */
    public static String getTableName(Class<?> clazz){
        return clazz.getSimpleName();
    }

}
