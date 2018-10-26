/*
 *
 * Copyright 2018 AEN
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

package AEN.guides.examples.namespace;

import io.AEN.sdk.infrastructure.NamespaceHttp;
import io.AEN.sdk.model.namespace.NamespaceId;
import io.AEN.sdk.model.namespace.NamespaceInfo;
import org.junit.jupiter.api.Test;

import java.net.MalformedURLException;
import java.util.concurrent.ExecutionException;

class CheckingNamespaceExistence {

    @Test
    void checkingNamespaceExistence() throws ExecutionException, InterruptedException, MalformedURLException {

        final NamespaceId namespaceId = new NamespaceId("foo");

        final NamespaceHttp namespaceHttp = new NamespaceHttp("http://localhost:3000");

        final NamespaceInfo namespaceInfo = namespaceHttp.getNamespace(namespaceId).toFuture().get();

        System.out.println(namespaceInfo);
    }
}