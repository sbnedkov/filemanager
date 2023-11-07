package com.example.filemanager;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;

@DataJpaTest
@ComponentScan(basePackages = { "com.example.filemanager"})
abstract class BaseDatabaseTest {
    @Test
    public void init() {}

    @Autowired
    protected TestEntityManager m_pManager;

    @Test
    public void initDatabase() {}
}
