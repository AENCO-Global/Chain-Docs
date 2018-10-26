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

import io.AEN.sdk.infrastructure.TransactionHttp;
import io.AEN.sdk.model.account.Account;
import io.AEN.sdk.model.blockchain.NetworkType;
import io.AEN.sdk.model.transaction.Deadline;
import io.AEN.sdk.model.transaction.RegisterNamespaceTransaction;
import io.AEN.sdk.model.transaction.SignedTransaction;
import io.AEN.sdk.model.transaction.TransactionAnnounceResponse;
import org.junit.jupiter.api.Test;

import java.math.BigInteger;
import java.net.MalformedURLException;
import java.time.temporal.ChronoUnit;
import java.util.concurrent.ExecutionException;

class RegisteringANamespace {

    @Test
    void registeringANamespace() throws ExecutionException, InterruptedException, MalformedURLException {

        // Replace with private key
        final String privateKey = "";

        final Account account = Account.createFromPrivateKey(privateKey, NetworkType.MIJIN_TEST);

        // Replace with namespace name
        final String namespaceName = "foo";

        final RegisterNamespaceTransaction registerNamespaceTransaction = RegisterNamespaceTransaction.createRootNamespace(
                Deadline.create(2, ChronoUnit.HOURS),
                namespaceName,
                BigInteger.valueOf(1000),
                NetworkType.MIJIN_TEST
        );

        final SignedTransaction signedTransaction = account.sign(registerNamespaceTransaction);

        final TransactionHttp transactionHttp = new TransactionHttp("http://localhost:3000");

        transactionHttp.announce(signedTransaction).toFuture().get();
    }
}
