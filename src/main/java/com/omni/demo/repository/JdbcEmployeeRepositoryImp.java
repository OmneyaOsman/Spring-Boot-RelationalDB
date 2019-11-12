package com.omni.demo.repository;

import com.omni.demo.model.Employee;
import com.omni.demo.utils.SqlStatements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class JdbcEmployeeRepositoryImp implements EmployeeRepository {

    @Autowired
    JdbcTemplate jdbcTemplate;


    @Override
    public int insertEmployee(Employee employee) {
        return jdbcTemplate.update(SqlStatements.INSERT,
                employee.getFirstName(),
                employee.getLastName());
    }

    @Override
    public void insertEmployees(List<Object[]> employees) {
        // Uses JdbcTemplate's batchUpdate operation to bulk load data
        jdbcTemplate.batchUpdate(SqlStatements.INSERT_All, employees);
    }

    @Override
    public int updateEmployeeFirstName(Employee employee) {
        return jdbcTemplate.update(SqlStatements.UPDATE, employee.getFirstName(), employee.getId());

    }

    @Override
    public int deleteById(Long id) {
        return jdbcTemplate.update(SqlStatements.DELETE, id);
    }

    @Override
    public List<Employee> findAll() {
        return jdbcTemplate.query(
                SqlStatements.SELECT_ALL,
                (rs, rowNum) ->
                        new Employee(
                                rs.getLong("id"),
                                rs.getString("first_name"),
                                rs.getString("last_name")));
    }

    @Override
    public Optional<Employee> findById(Long id) {
        return jdbcTemplate.queryForObject(
                SqlStatements.SELECT_BY_ID,
                new Object[]{id},
                (rs, rowNum) ->
                        Optional.of(new Employee(
                                rs.getLong("id"),
                                rs.getString("first_name"),
                                rs.getString("last_name")))
        );
    }

    @Override
    public Optional<Employee> findByFirstName(String firstName) {
        return jdbcTemplate.queryForObject(SqlStatements.SELECT_BY_FIRST_NAME
                , new Object[]{firstName},
                (rs, rowNum) ->
                        Optional.of(new Employee(
                                rs.getLong("id"),
                                rs.getString("first_name"),
                                rs.getString("last_name"))));

    }

    @Override
    public int count() {
        if (jdbcTemplate.queryForObject(SqlStatements.ROWS_COUNT, Integer.class) == null)
            throw new NullPointerException();
        return jdbcTemplate.queryForObject(SqlStatements.ROWS_COUNT, Integer.class);
    }

    @Override
    public void createTable() {
        jdbcTemplate.execute(SqlStatements.CREATE_TABLE_EMPLOYEE);
    }

    @Override
    public void dropTableIfExists() {
        jdbcTemplate.execute(SqlStatements.DROP_TABLE_EMPLOYEE);
    }
}
