package ru.sbercraft.service.integration;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

import java.sql.SQLException;

public class IntegrationTestBase {
    private static final String CLEAN_SQL = "DELETE FROM users;";
    private static final String CREATE_SQL = """
            CREATE TABLE IF NOT EXISTS users
            (
                id INTEGER PRIMARY KEY,
                lastname VARCHAR(100) NOT NULL,
                firstname VARCHAR(100) NOT NULL,
                email VARCHAR(100) NOT NULL UNIQUE,
                password VARCHAR(30) NOT NULL,
                role VARCHAR(200) NOT NULL,
                active BOOLEAN,
                subdivision_id INTEGER,
                date_registration TIMESTAMP
            );
            """;

//    @BeforeAll
//    static void prepareDatabase() throws SQLException {
//        try (var connection = ConnectionManager.get();
//             var statement = connection.createStatement()) {
//            statement.execute(CREATE_SQL);
//        }
//    }
//
//    @BeforeEach
//    void cleanData() throws SQLException {
//        try (var connection = ConnectionManager.get();
//             var statement = connection.createStatement()) {
//            statement.execute(CLEAN_SQL);
//        }
//    }
}
