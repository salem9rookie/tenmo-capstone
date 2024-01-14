package com.techelevator.dao;


import com.techelevator.tenmo.dao.JdbcTransferDao;
import org.junit.Before;
import org.junit.Test;
import org.springframework.jdbc.core.JdbcTemplate;

public class JdbcTransferDaoTests extends BaseDaoTests{

    private JdbcTransferDao jdbcTransferDao;
    @Before
    public void setup() {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        jdbcTransferDao = new JdbcTransferDao(jdbcTemplate);
    }






}
