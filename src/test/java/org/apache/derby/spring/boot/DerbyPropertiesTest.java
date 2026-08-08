/*
 * Copyright (c) 2018, hiwepy (https://github.com/hiwepy).
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package org.apache.derby.spring.boot;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Unit tests for {@link DerbyProperties}.
 *
 * <p>Verifies default values, getters/setters and POJO contract.</p>
 *
 * @author wandl
 * @since 1.0.0
 */
@DisplayName("DerbyProperties Tests")
class DerbyPropertiesTest {

    private DerbyProperties properties;

    @BeforeEach
    void setUp() {
        properties = new DerbyProperties();
    }

    @Test
    @DisplayName("Configuration prefix is 'berkeley.db'")
    void testPrefix() {
        assertThat(DerbyProperties.PREFIX).isEqualTo("berkeley.db");
    }

    @Test
    @DisplayName("Default value of enabled is false")
    void testDefaultEnabled() {
        assertThat(properties.isEnabled()).isFalse();
    }

    @Test
    @DisplayName("Setter for enabled updates the value")
    void testSetEnabled() {
        properties.setEnabled(true);
        assertThat(properties.isEnabled()).isTrue();
    }

    @Test
    @DisplayName("Default value of envDir is 'dbEnv'")
    void testDefaultEnvDir() {
        assertThat(properties.getEnvDir()).isEqualTo("dbEnv");
    }

    @Test
    @DisplayName("Setter for envDir updates the value")
    void testSetEnvDir() {
        properties.setEnvDir("customEnv");
        assertThat(properties.getEnvDir()).isEqualTo("customEnv");
    }

    @Test
    @DisplayName("Default value of databaseName is 'tt'")
    void testDefaultDatabaseName() {
        assertThat(properties.getDatabaseName()).isEqualTo("tt");
    }

    @Test
    @DisplayName("Setter for databaseName updates the value")
    void testSetDatabaseName() {
        properties.setDatabaseName("customDb");
        assertThat(properties.getDatabaseName()).isEqualTo("customDb");
    }

    @Test
    @DisplayName("Default value of catalogDatabaseName is 'tt'")
    void testDefaultCatalogDatabaseName() {
        assertThat(properties.getCatalogDatabaseName()).isEqualTo("tt");
    }

    @Test
    @DisplayName("Setter for catalogDatabaseName updates the value")
    void testSetCatalogDatabaseName() {
        properties.setCatalogDatabaseName("customCatalog");
        assertThat(properties.getCatalogDatabaseName()).isEqualTo("customCatalog");
    }

    @Test
    @DisplayName("Getter for homeDir returns the configured value")
    void testHomeDir() {
        properties.setHomeDir("/var/db");
        assertThat(properties.getHomeDir()).isEqualTo("/var/db");
    }

    @Test
    @DisplayName("Getter for envHome returns the configured value")
    void testEnvHome() {
        properties.setEnvHome("/var/env");
        assertThat(properties.getEnvHome()).isEqualTo("/var/env");
    }
}