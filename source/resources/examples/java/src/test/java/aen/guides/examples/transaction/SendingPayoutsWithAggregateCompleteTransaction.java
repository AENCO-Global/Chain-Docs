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

package AEN.guides.examples.transaction;

import io.AEN.sdk.infrastructure.TransactionHttp;
import io.AEN.sdk.model.account.Account;
import io.AEN.sdk.model.account.Address;
import io.AEN.sdk.model.blockchain.NetworkType;
import io.AEN.sdk.model.mosaic.AENC;
import io.AEN.sdk.model.transaction.*;
import org.junit.jupiter.api.Test;
import java.math.BigInteger;
import java.net.MalformedURLException;
import java.util.Arrays;
import java.util.Collections;
import java.util.concurrent.ExecutionException;

import static java.time.temporal.ChronoUnit.HOURS;

class SendingPayoutsWithAggregateCompleteTransaction {

    @Test
    void sendingPayoutsWithAggregateCompleteTransaction() throws ExecutionException, InterruptedException, MalformedURLException {

        // Replace with private key
        final String privateKey = "";

        final Address brotherAddress = Address.createFromRawAddress("SDG4WG-FS7EQJ-KFQKXM-4IUCQG-PXUW5H-DJVIJB-OXJG");
        final Address sisterAddress = Address.createFromRawAddress("SCGPXB-2A7T4I-W5MQCX-FQY4UQ-W5JNU5-F55HGK-HBUN");

        final Account account = Account.createFromPrivateKey(privateKey, NetworkType.MIJIN_TEST);

        final AENC AENC = AENC.createRelative(BigInteger.valueOf(10)); // 10 AENC represent 10 000 000 micro AENC

        final TransferTransaction brotherTransferTransaction = TransferTransaction.create(
                Deadline.create(2, HOURS),
                brotherAddress,
                Collections.singletonList(AENC),
                PlainMessage.create("payout"),
                NetworkType.MIJIN_TEST
        );

        final TransferTransaction sisterTransferTransaction = TransferTransaction.create(
                Deadline.create(2, HOURS),
                sisterAddress,
                Collections.singletonList(AENC),
                PlainMessage.create("payout"),
                NetworkType.MIJIN_TEST
        );

        final AggregateTransaction aggregateTransaction = AggregateTransaction.createComplete(
                Deadline.create(2, HOURS),
                Arrays.asList(
                        brotherTransferTransaction.toAggregate(account.getPublicAccount()),
                        sisterTransferTransaction.toAggregate(account.getPublicAccount())
                ),
                NetworkType.MIJIN_TEST
        );

        final TransactionHttp transactionHttp = new TransactionHttp("http://localhost:3000");

        final SignedTransaction signedTransaction = account.sign(aggregateTransaction);

        transactionHttp.announce(signedTransaction).toFuture().get();
    }
}
