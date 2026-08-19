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
package org.apache.derby.spring.boot.hooks;

import org.apache.derby.database.Database;
import org.springframework.core.env.Environment;

/**
 * JVM shutdown hook for gracefully closing the Berkeley DB environment and any
 * open {@link Database} handles.
 *
 * <p>Registered with the JVM via {@link Runtime#addShutdownHook(Thread)} so that
 * Berkeley DB resources are released when the application process exits, even if
 * the shutdown sequence is initiated by a signal or an unexpected exception.</p>
 *
 * <p>The current implementation is intentionally a no-op stub; concrete
 * shutdown logic will be added when the full Berkeley DB integration is wired up
 * in {@link org.apache.derby.spring.boot.DerbyAutoConfiguration}.</p>
 *
 * @author <a href="https://github.com/loong10k">Loong Wan</a>
 * @since 1.0.0
 */
public class DerbyShutdownHook extends Thread {

    /**
     * The primary database handle that should be closed during JVM shutdown.
     */
    private final Database database;

    /**
     * The Berkeley DB environment associated with {@link #database}.
     */
    private final Environment environment;

    /**
     * Creates a new shutdown hook bound to the supplied database and environment.
     *
     * @param database    the primary database handle to close (may be {@code null}
     *                    when the environment was never opened)
     * @param environment the Berkeley DB environment to clean up
     */
    public DerbyShutdownHook(Database database, Environment environment) {
        this.database = database;
        this.environment = environment;
    }

    /**
     * Invoked by the JVM during shutdown.
     *
     * <p>The current implementation is intentionally empty; future versions
     * should close {@link #database} and {@link #environment} here in the
     * correct order to avoid {@code DatabaseException}s.</p>
     */
    @Override
    /**
     * <p>Run.</p>
     */
    public void run() {
        // No-op placeholder for future shutdown logic.
    }
}