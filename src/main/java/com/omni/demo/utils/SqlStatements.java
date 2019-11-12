package com.omni.demo.utils;

public final class SqlStatements {

    public static final String CREATE_TABLE_EMPLOYEE = "CREATE TABLE EMPLOYEE" +
            " (id SERIAL ,first_name VARCHAR(255)," +
            " last_name VARCHAR(255))";
    public static final String DROP_TABLE_EMPLOYEE = "DROP TABLE EMPLOYEE IF EXISTS";

    public static final String INSERT = "INSERT INTO EMPLOYEE" +
            "(first_name,last_name)" +
            "VALUES(? ,?)";

    public static final String INSERT_All ="INSERT INTO EMPLOYEE(first_name, last_name) VALUES (?,?)";

    public static final String SELECT_ALL="SELECT * FROM EMPLOYEE";
    public static final String SELECT_BY_ID="SELECT * FROM EMPLOYEE WHERE id = ?";
    public static final String SELECT_BY_FIRST_NAME="SELECT id, first_name, last_name FROM EMPLOYEE WHERE first_name = ?";
    public static final String UPDATE="UPDATE EMPLOYEE  SET first_name = ? WHERE id = ?";
    public static final String DELETE= "DELETE EMPLOYEE where id = ?";
    public static final String ROWS_COUNT="SELECT COUNT(*) FROM EMPLOYEE";

    private SqlStatements() {
    }
}
