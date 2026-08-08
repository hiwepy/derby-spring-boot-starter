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

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Configuration properties for the Berkeley DB / Derby embedded database starter.
 *
 * <p>Bound to the {@code berkeley.db.*} configuration namespace by Spring Boot.
 * * All fields are optional; the database environment is only created when
 * {@link #isEnabled()} returns {@code true}.</p>
 *
 * <p>Typical configuration in {@code application.yml}:</p>
 * <pre>{@code
 * berkeley:
 *   db:
 *     enabled: true
 *     home-dir: /var/lib/berkeley
 *     env-home: /var/lib/berkeley/env
 *     env-dir: dbEnv
 *     database-name: primary
 *     catalog-database-name: catalog
 * }</pre>
 *
 * @author [@Loong Wan](https://github.com/loong10k)
 * @since 1.0.0
 */
@ConfigurationProperties(DerbyProperties.PREFIX)
public class DerbyProperties {

    /**
     * Configuration prefix used by Spring Boot to bind properties.
     */
    public static final String PREFIX = "berkeley.db";

    /**
     * Opt-in flag for the Berkeley DB environment.
     * <p>When {@code false} (the default) the auto-configuration is skipped
     * entirely so this starter can be safely added to the classpath of any
     * application without forcing a database environment.</p>
     */
    private boolean enabled = false;

    /**
     * Root directory of the Berkeley DB home.
     * <p>Resolved as a Spring {@code Resource} by the auto-configuration.</p>
     */
    private String homeDir;

    /**
     * Explicit environment home path. When provided, takes precedence over
     * {@link #envDir} during environment construction.
     */
    private String envHome;

    /**
     * Directory name (relative to {@link #homeDir}) where Berkeley DB writes
     * its data files and log files. Defaults to {@code dbEnv}.
     */
    private String envDir = "dbEnv";

    /**
     * Logical name of the primary database to open. Defaults to {@code tt}.
     */
    private String databaseName = "tt";

    /**
     * Logical name of the catalog database that stores the class catalog.
     * Defaults to {@code tt}.
     */
    private String catalogDatabaseName = "tt";

    /**
     * @return {@code true} if the Berkeley DB starter should activate.
     */
    public boolean isEnabled() {
        return enabled;
    }

    /**
     * Enables or disables the Berkeley DB environment.
     *
     * @param enabled {@code true} to activate, {@code false} (the default) to skip.
     */
    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    /**
     * @return the configured home directory, or {@code null} when not set.
     */
    public String getHomeDir() {
        return homeDir;
    }

    /**
     * @param homeDir the root directory for the Berkeley DB environment.
     */
    public void setHomeDir(String homeDir) {
        this.homeDir = homeDir;
    }

    /**
     * @return the configured environment home path, or {@code null} when not set.
     */
    public String getEnvHome() {
        return envHome;
    }

    /**
     * @param envHome the explicit environment home path.
     */
    public void setEnvHome(String envHome) {
        this.envHome = envHome;
    }

    /**
     * @return the directory name where data and log files are written.
     */
    public String getEnvDir() {
        return envDir;
    }

    /**
     * @param envDir the directory name (relative to {@link #homeDir}) used for
     *               Berkeley DB data and log files.
     */
    public void setEnvDir(String envDir) {
        this.envDir = envDir;
    }

    /**
     * @return the configured primary database name.
     */
    public String getDatabaseName() {
        return databaseName;
    }

    /**
     * @param databaseName the logical name of the primary database to open.
     */
    public void setDatabaseName(String databaseName) {
        this.databaseName = databaseName;
    }

    /**
     * @return the configured catalog database name.
     */
    public String getCatalogDatabaseName() {
        return catalogDatabaseName;
    }

    /**
     * @param catalogDatabaseName the logical name of the catalog database.
     */
    public void setCatalogDatabaseName(String catalogDatabaseName) {
        this.catalogDatabaseName = catalogDatabaseName;
    }
}