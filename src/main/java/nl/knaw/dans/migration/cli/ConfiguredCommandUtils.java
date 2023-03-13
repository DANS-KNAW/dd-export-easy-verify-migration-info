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

import net.sourceforge.argparse4j.inf.Argument;
import net.sourceforge.argparse4j.inf.Subparser;

public class ConfiguredCommandUtils {
  private static final String DANS_DEFAULT_CONFIG_PROPERTY = "dans.default.config";

  public ConfiguredCommandUtils() {
  }

  static Argument addFileArgument(Subparser subparser, boolean configFileAsOption) {
    String defaultConfig = System.getProperty("dans.default.config");
    return configFileAsOption ? subparser.addArgument(new String[]{"-c", "--config"}).nargs("?").dest("file").setDefault(defaultConfig).help("application configuration file") : subparser.addArgument(new String[]{"file"}).nargs("?").setDefault(defaultConfig).help("application configuration file");
  }
}
