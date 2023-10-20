/*
 * Copyright (C) 2021 DANS - Data Archiving and Networked Services (info@dans.knaw.nl)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package nl.knaw.dans.migration.cli;

import io.dropwizard.core.Application;
import io.dropwizard.core.Configuration;
import io.dropwizard.core.cli.EnvironmentCommand;
import net.sourceforge.argparse4j.inf.Argument;
import net.sourceforge.argparse4j.inf.Subparser;

public abstract class DefaultConfigEnvironmentCommand<T extends Configuration> extends EnvironmentCommand<T> {
  private final boolean configFileAsOption;

  protected DefaultConfigEnvironmentCommand(Application<T> application, String name, String description, boolean configFileAsOption) {
    super(application, name, description);
    this.configFileAsOption = configFileAsOption;
  }

  protected DefaultConfigEnvironmentCommand(Application<T> application, String name, String description) {
    this(application, name, description, false);
  }

  protected Argument addFileArgument(Subparser subparser) {
    return ConfiguredCommandUtils.addFileArgument(subparser, this.configFileAsOption);
  }
}