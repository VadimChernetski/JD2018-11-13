package by.it.nickgrudnitsky.jd03_03.dao;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class MyUniversalDAO<Type> implements InterfaceDao<Type> {

    private Type bean; //принимаемый бин
    private String table; //имя таблицы
    private Field[] fields; //все поля бина

    public MyUniversalDAO(Type bean) { //конструктор, который принимает бин
        this.bean = bean; //записываем бин
        table = bean.getClass().getSimpleName().toLowerCase() + "s"; //записываем название таблицы
        fields = bean.getClass().getDeclaredFields(); //получаем все поля бина и записываем их
    }

    @Override
    public boolean create(Type bean) throws SQLException {
        StringBuilder dbsField = new StringBuilder(); //стрингбилдер для полей
        StringBuilder values = new StringBuilder(); //стрингбилдер для значений
        String delimeter = ""; //запятая
        try {
            for (int i = 1; i < fields.length; i++) {
                Field field = fields[i];
                field.setAccessible(true); //обходим приватность полей
                dbsField.append(delimeter).append(field.getName()); //собираем поля
                values.append(delimeter).append("'").append(field.get(bean)).append("'"); //собираем значения
                delimeter = ",";
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        String sql = "INSERT INTO " + table + " (" + dbsField + ") VALUES (" + values + ");"; //собираем sql запрос
        int id = -1;
        try (Connection connection = MyConnection.getConnection();
             Statement statement = connection.createStatement()) {
            statement.executeUpdate(sql, Statement.RETURN_GENERATED_KEYS);
            ResultSet generatedKeys = statement.getGeneratedKeys();
            if (generatedKeys.next()) {
                id = generatedKeys.getInt(1);
            }
        }
        if (id > 0) try {
            fields[0].setAccessible(true);
            fields[0].set(bean, id);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return (id > 0);
    }

    @Override
    public Type read(long id) throws SQLException {
        List<Type> list = getAll("WHERE id=" + id);
        if (list.size() > 0) {
            return list.get(0);
        } else {
            return null;
        }
    }

    @Override
    public boolean update(Type bean) throws SQLException {
        StringBuilder sql = new StringBuilder();
        sql.append("UPDATE ").append(table).append(" SET ");
        String delimeter = "";
        try {
            for (int i = 1; i < fields.length; i++) {
                Field field = fields[i];
                field.setAccessible(true);
                sql.append(delimeter).append(field.getName()).append("='").append(field.get(bean)).append("'");
                delimeter = ", ";
            }
            sql.append(" WHERE " + table + ".id=" + fields[0].get(bean) + ";");
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        try (Connection connection = MyConnection.getConnection();
             Statement statement = connection.createStatement()) {
            String s = sql.toString();
            int i = statement.executeUpdate(s);
            return (i > 0);
        }
    }

    @Override
    public boolean delete(Type bean) throws SQLException {
        String sql = null;
        try {
            sql = "DELETE FROM `" + table + "` WHERE `" + table + "`.`id` = '" + fields[0].get(bean) + "'";
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        try (Connection connection = MyConnection.getConnection();
             Statement statement = connection.createStatement()) {
            int i = statement.executeUpdate(sql);
            return (i > 0);
        }
    }

    @Override
    public List<Type> getAll(String where) throws SQLException {
        List<Type> beans = new ArrayList<>();
        String sql = String.format("SELECT * FROM %s %s ;", table, where);
        try (Connection connection = MyConnection.getConnection();
             Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                Type type = (Type) bean.getClass().newInstance(); // создаем новый бин, куда будем складывать поля
                for (int i = 0; i < fields.length; i++) {
                    Field field = fields[i];
                    field.setAccessible(true);
                    if (field.getType() == String.class) {
                        field.set(type, resultSet.getString(field.getName()));
                    }
                    if (field.getType() == int.class) {
                        field.set(type, resultSet.getInt(field.getName()));
                    }
                    if (field.getType() == long.class) {
                        field.set(type, resultSet.getLong(field.getName()));
                    }
                }
                beans.add(type);
            }
        } catch (IllegalAccessException | InstantiationException e) {
            e.printStackTrace();
        }
        return beans;
    }

}
