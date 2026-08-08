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

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ResourceLoaderAware;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ResourceLoader;

/**
 * Spring Boot auto-configuration for Berkeley DB / Derby embedded database integration.
 * <p>
 * Activates the embedded database environment only when the {@code berkeley.db.enabled}
 * property is explicitly set to {@code true}, allowing applications to opt in to the
 * Berkeley DB environment without forcing it on every Spring Boot application that
 * happens to scan this package.
 * </p>
 * <p>
 * The configuration binds the {@link DerbyProperties} POJO to the {@code berkeley.db.*}
 * configuration namespace and implements {@link ResourceLoaderAware} so the Spring
 * infrastructure can inject the application's resource loader for resolving file-based
 * database configuration.
 * </p>
 *
 * <h3>Configuration keys</h3>
 * <ul>
 *   <li>{@code berkeley.db.enabled} — opt-in switch (default {@code false})</li>
 *   <li>{@code berkeley.db.home-dir} — root directory of the database environment</li>
 *   <li>{@code berkeley.db.env-home} — environment home path</li>
 *   <li>{@code berkeley.db.env-dir} — environment directory name (default {@code dbEnv})</li>
 *   <li>{@code berkeley.db.database-name} — database name (default {@code tt})</li>
 *   <li>{@code berkeley.db.catalog-database-name} — catalog database name (default {@code tt})</li>
 * </ul>
 *
 * @author [@Loong Wan](https://github.com/loong10k)
 * @since 1.0.0
 */
@Configuration
@ConditionalOnProperty(prefix = DerbyProperties.PREFIX, value = "enabled", havingValue = "true")
@EnableConfigurationProperties({ DerbyProperties.class })
public class DerbyAutoConfiguration implements InitializingBean, ResourceLoaderAware {

    /**
     * Bound configuration properties; injected by Spring Boot's
     * {@code @EnableConfigurationProperties} mechanism.
     */
    @Autowired
    private DerbyProperties properties;

    /**
     * Spring {@link ResourceLoader} provided by the application context.
     * <p>Set by the framework during context refresh.</p>
     */
    private ResourceLoader resourceLoader;

    /**
     * Lifecycle hook invoked by Spring after all properties have been set.
     * <p>The current implementation is a no-op placeholder; future versions may
     * register the {@link hooks.DerbyShutdownHook} here to ensure clean shutdown
     * of the Berkeley DB environment when the JVM terminates.</p>
     *
     * @throws Exception if any startup task fails (currently never thrown)
     */
    @Override
    public void afterPropertiesSet() throws Exception {
        // Placeholder for future startup wiring (e.g. shutdown hook registration).
    }

    /**
     * Stores the {@link ResourceLoader} provided by the Spring application context.
     *
     * @param resourceLoader the resource loader to use for resolving classpath and
     *                       filesystem resources (database configuration files, etc.)
     */
    @Override
    public void setResourceLoader(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
    }
}