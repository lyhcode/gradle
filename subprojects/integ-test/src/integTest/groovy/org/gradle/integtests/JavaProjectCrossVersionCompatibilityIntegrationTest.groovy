/*
 * Copyright 2011 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.gradle.integtests

import org.gradle.integtests.fixtures.BasicGradleDistribution
import org.gradle.integtests.fixtures.CrossVersionCompatibilityTestRunner
import org.gradle.integtests.fixtures.GradleDistribution
import org.gradle.integtests.fixtures.GradleDistributionExecuter
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.gradle.integtests.fixtures.TestResources

@RunWith(CrossVersionCompatibilityTestRunner)
class JavaProjectCrossVersionCompatibilityIntegrationTest {
    @Rule public final GradleDistribution dist = new GradleDistribution()
    @Rule public final TestResources resources = new TestResources("JavaProjectCrossVersionCompatibilityIntegrationTest/canBuildJavaProject")
    BasicGradleDistribution previousVersion

    @Test
    public void "can upgrade and downgrade Gradle version used to build Java project"() {
        dist.testFile('buildSrc/src/main/groovy').assertIsDir()

        executer(previousVersion).withTasks('build').run()

        executer(dist).withTasks('build').run()

        executer(previousVersion).withTasks('build').run()
    }

    def executer(BasicGradleDistribution dist) {
        def executer = dist.executer();
        if (executer instanceof GradleDistributionExecuter) {
            executer.withDeprecationChecksDisabled()
        }
        executer.inDirectory(this.dist.testDir)
        return executer;
    }
}

