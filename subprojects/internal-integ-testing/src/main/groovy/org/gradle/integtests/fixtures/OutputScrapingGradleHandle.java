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
package org.gradle.integtests.fixtures;

abstract public class OutputScrapingGradleHandle<T extends GradleExecuter> implements GradleHandle<T> {

    protected ExecutionResult toExecutionResult(String output, String error) {
        return new OutputScrapingExecutionResult(transformStandardOutput(output), transformErrorOutput(error));
    }

    protected ExecutionResult toExecutionFailure(String output, String error) {
        return new OutputScrapingExecutionFailure(transformStandardOutput(output), transformErrorOutput(error));
    }

    protected String transformStandardOutput(String output) {
        return output;
    }

    protected String transformErrorOutput(String error) {
        return error;
    }

}