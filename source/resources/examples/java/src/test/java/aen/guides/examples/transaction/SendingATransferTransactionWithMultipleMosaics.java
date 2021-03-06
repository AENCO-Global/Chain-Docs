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

import io.AEN.sdk.infrastructure.Listener;
import io.AEN.sdk.infrastructure.TransactionHttp;
import io.AEN.sdk.model.account.Account;
import io.AEN.sdk.model.account.Address;
import io.AEN.sdk.model.blockchain.NetworkType;
import io.AEN.sdk.model.mosaic.Mosaic;
import io.AEN.sdk.model.mosaic.MosaicId;
import io.AEN.sdk.model.mosaic.XEM;
import io.AEN.sdk.model.transaction.*;
import org.junit.jupiter.api.Test;

import java.math.BigInteger;
import java.net.MalformedURLException;
import java.util.Arrays;
import java.util.Collections;
import java.util.concurrent.ExecutionException;

import static java.time.temporal.ChronoUnit.HOURS;

class SendingATransferTransactionWithMultipleMosaics {

    @Test
    void sendingATransferTransactionWithMultipleMosaics() throws ExecutionException, InterruptedException, MalformedURLException {
        // Replace with a Cosignatory's private key
        final String privateKey = "";

        final String recipientAddress = "SD5DT3-CH4BLA-BL5HIM-EKP2TA-PUKF4N-Y3L5HR-IR54";

        final Account account = Account.createFromPrivateKey(privateKey, NetworkType.MIJIN_TEST);

        final TransferTransaction transferTransaction = TransferTransaction.create(
                Deadline.create(2, HOURS),
                Address.createFromRawAddress(recipientAddress),
                Arrays.asList(
                        new Mosaic(new MosaicId("alice:token"), BigInteger.valueOf(10)),
                        XEM.createRelative(BigInteger.valueOf(10))
                ),
                PlainMessage.create("sending multiple mosaics"),
                NetworkType.MIJIN_TEST
        );


        final SignedTransaction signedTransaction = account.sign(transferTransaction);
        final TransactionHttp transactionHttp = new TransactionHttp("http://localhost:3000");

        transactionHttp.announce(signedTransaction).toFuture().get();
    }
}
