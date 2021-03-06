package by.it.lobkova.jd03_01;

import java.sql.*;

public class C_Init {
    public static void main(String[] args) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        try (Connection connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:2016/", "root", "")) {
            Statement statement = connection.createStatement();
            statement.executeUpdate("DROP SCHEMA IF EXISTS `lobkova`");
            statement.executeUpdate("CREATE SCHEMA IF NOT EXISTS `lobkova` DEFAULT CHARACTER SET utf8");
            statement.executeUpdate("DROP TABLE IF EXISTS `lobkova`.`roles`");
            statement.executeUpdate("CREATE TABLE IF NOT EXISTS `lobkova`.`roles` (\n" +
                    "  `id` INT NOT NULL AUTO_INCREMENT,\n" +
                    "  `role` VARCHAR(45) NULL,\n" +
                    "  PRIMARY KEY (`id`))\n" +
                    "ENGINE = InnoDB;");
            statement.executeUpdate("CREATE TABLE IF NOT EXISTS `lobkova`.`users` (\n" +
                    "  `id` INT NOT NULL AUTO_INCREMENT,\n" +
                    "  `email` VARCHAR(100) NULL,\n" +
                    "  `login` VARCHAR(100) NULL,\n" +
                    "  `password` VARCHAR(100) NULL,\n" +
                    "  `name` VARCHAR(100) NULL,\n" +
                    "  `roles_ID` INT NOT NULL,\n" +
                    "  PRIMARY KEY (`id`),\n" +
                    "  CONSTRAINT `fk_users_roles1`\n" +
                    "    FOREIGN KEY (`roles_ID`)\n" +
                    "    REFERENCES `lobkova`.`roles` (`id`)\n" +
                    "    ON DELETE RESTRICT\n" +
                    "    ON UPDATE RESTRICT)\n" +
                    "ENGINE = InnoDB;");
            statement.executeUpdate("CREATE TABLE IF NOT EXISTS `lobkova`.`goals` (\n" +
                    "  `ID` INT NOT NULL AUTO_INCREMENT,\n" +
                    "  `name_goal` VARCHAR(100) NULL,\n" +
                    "  `description_goal` VARCHAR(500) NULL,\n" +
                    "  `beginDate` DATE NULL,\n" +
                    "  `endDate` DATE NULL,\n" +
                    "  `users_ID` INT NOT NULL,\n" +
                    "  PRIMARY KEY (`ID`),\n" +
                    "  CONSTRAINT `fk_goals_users1`\n" +
                    "    FOREIGN KEY (`users_ID`)\n" +
                    "    REFERENCES `lobkova`.`users` (`ID`)\n" +
                    "    ON DELETE NO ACTION\n" +
                    "    ON UPDATE NO ACTION)\n" +
                    "ENGINE = InnoDB;\n");
            statement.executeUpdate("CREATE TABLE IF NOT EXISTS `lobkova`.`tasks` (\n" +
                    "  `id` INT NOT NULL AUTO_INCREMENT,\n" +
                    "  `name_task` VARCHAR(100) NULL,\n" +
                    "  `description_task` VARCHAR(500) NULL,\n" +
                    "  `date` INT NULL,\n" +
                    "  `goals_ID` INT NOT NULL,\n" +
                    "  PRIMARY KEY (`id`),\n" +
                    "  CONSTRAINT `fk_tasks_goals1`\n" +
                    "    FOREIGN KEY (`goals_ID`)\n" +
                    "    REFERENCES `lobkova`.`goals` (`id`)\n" +
                    "    ON DELETE NO ACTION\n" +
                    "    ON UPDATE NO ACTION)\n" +
                    "ENGINE = InnoDB;");
            statement.executeUpdate("INSERT INTO `lobkova`.`roles` (`id`, `role`) VALUES (DEFAULT, 'administrator');");
            statement.executeUpdate("INSERT INTO `lobkova`.`roles` (`id`, `role`) VALUES (DEFAULT, 'user');");
            statement.executeUpdate("INSERT INTO `lobkova`.`users` (`id`, `email`, `login`, `password`, `name`, `roles_ID`) VALUES (NULL, 'user2@user', 'user2', 'user2', 'user2', '2');");
            statement.executeUpdate("INSERT INTO `lobkova`.`users` (`id`, `email`, `login`, `password`, `name`, `roles_ID`) VALUES (NULL, 'user3@user', 'user3', 'user3', 'user3', '2');");
            statement.executeUpdate("INSERT INTO `lobkova`.`users` (`id`, `email`, `login`, `password`, `name`, `roles_ID`) VALUES (NULL, 'user4@user', 'user4', 'user4', 'user4', '2');");

        } catch (SQLException e) {
            e.printStackTrace();
        }

        try (Connection connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:2016/", "root", "")) {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM `lobkova`.`users`;");
            while (resultSet.next()) {
                String out = resultSet.getString("name") + ", " + resultSet.getString("email");
                System.out.println(out);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}