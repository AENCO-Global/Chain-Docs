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

package AEN.guides.examples.account;

import io.AEN.sdk.infrastructure.TransactionHttp;
import io.AEN.sdk.model.account.Account;
import io.AEN.sdk.model.account.PublicAccount;
import io.AEN.sdk.model.blockchain.NetworkType;
import io.AEN.sdk.model.transaction.AggregateTransaction;
import io.AEN.sdk.model.transaction.Deadline;
import io.AEN.sdk.model.transaction.ModifyMultisigAccountTransaction;
import io.AEN.sdk.model.transaction.SignedTransaction;
import org.junit.jupiter.api.Test;

import java.net.MalformedURLException;
import java.util.Collections;
import java.util.concurrent.ExecutionException;

import static java.time.temporal.ChronoUnit.HOURS;

class ModifyingAMultisigAccountIncreaseMinApproval {

    @Test
    void modifyingAMultisigAccountIncreaseMinApproval() throws ExecutionException, InterruptedException, MalformedURLException {
        // Replace with the multisig public key
        final String cosignatoryPrivateKey = "";
        final String multisigAccountPublicKey = "";

        final Account cosignatoryAccount = Account.createFromPrivateKey(cosignatoryPrivateKey, NetworkType.MIJIN_TEST);
        final PublicAccount multisigAccount = PublicAccount.createFromPublicKey(multisigAccountPublicKey, NetworkType.MIJIN_TEST);

        final ModifyMultisigAccountTransaction modifyMultisigAccountTransaction = ModifyMultisigAccountTransaction.create(
                Deadline.create(2, HOURS),
                1,
                0,
                Collections.emptyList(),
                NetworkType.MIJIN_TEST
        );

        final AggregateTransaction aggregateTransaction = AggregateTransaction.createComplete(
                Deadline.create(2, HOURS),
                Collections.singletonList(modifyMultisigAccountTransaction.toAggregate(multisigAccount)),
                NetworkType.MIJIN_TEST
        );

        final SignedTransaction signedTransaction = cosignatoryAccount.sign(aggregateTransaction);

        final TransactionHttp transactionHttp = new TransactionHttp("http://localhost:3000");

        transactionHttp.announce(signedTransaction).toFuture().get();
    }
}
