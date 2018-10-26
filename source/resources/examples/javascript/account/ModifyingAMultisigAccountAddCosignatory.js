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

const AENSdk = require("AEN-sdk");
const operators = require('rxjs/operators');

const Account = AENSdk.Account,
    MultisigCosignatoryModification = AENSdk.MultisigCosignatoryModification,
    ModifyMultisigAccountTransaction = AENSdk.ModifyMultisigAccountTransaction,
    NetworkType = AENSdk.NetworkType,
    PublicAccount = AENSdk.PublicAccount,
    Deadline = AENSdk.Deadline,
    AENC = AENSdk.AENC,
    AggregateTransaction = AENSdk.AggregateTransaction,
    LockFundsTransaction = AENSdk.LockFundsTransaction,
    TransactionHttp = AENSdk.TransactionHttp,
    Listener = AENSdk.Listener,
    MultisigCosignatoryModificationType = AENSdk.MultisigCosignatoryModificationType,
    UInt64 = AENSdk.UInt64,
    filter = operators.filter,
    mergeMap = operators.mergeMap;

// 01 - Setup
const nodeUrl = 'http://localhost:3000';
const transactionHttp = new TransactionHttp(nodeUrl);
const listener = new Listener(nodeUrl);

const cosignatoryPrivateKey = process.env.COSIGNATORY_1_PRIVATE_KEY;
const cosignatoryAccount = Account.createFromPrivateKey(cosignatoryPrivateKey, NetworkType.MIJIN_TEST);

const multisigAccountPublicKey = '202B3861F34F6141E120742A64BC787D6EBC59C9EFB996F4856AA9CBEE11CD31';
const multisigAccount = PublicAccount.createFromPublicKey(multisigAccountPublicKey, NetworkType.MIJIN_TEST);

const newCosignatoryPublicKey = 'CD4EE677BD0642C93910CB93214954A9D70FBAAE1FFF1FF530B1FB52389568F1';
const newCosignatoryAccount = PublicAccount.createFromPublicKey(newCosignatoryPublicKey, NetworkType.MIJIN_TEST);

const multisigCosignatoryModification = new MultisigCosignatoryModification(MultisigCosignatoryModificationType.Add,newCosignatoryAccount);

// 02 - Create ModifyMultisigAccountTransaction
const modifyMultisigAccountTransaction = ModifyMultisigAccountTransaction.create(
    Deadline.create(),
    0,
    0,
    [multisigCosignatoryModification],
    NetworkType.MIJIN_TEST);

// 03 - Create and sign AggregateTransaction
const aggregateTransaction = AggregateTransaction.createBonded(
    Deadline.create(),
    [modifyMultisigAccountTransaction.toAggregate(multisigAccount)],
    NetworkType.MIJIN_TEST);

const signedTransaction = cosignatoryAccount.sign(aggregateTransaction);

// 04 - Announce transaction
const lockFundsTransaction = LockFundsTransaction.create(
    Deadline.create(),
    AENC.createRelative(10),
    UInt64.fromUint(480),
    signedTransaction,
    NetworkType.MIJIN_TEST);

const lockFundsTransactionSigned = cosignatoryAccount.sign(lockFundsTransaction);

listener.open().then(() => {

    transactionHttp
        .announce(lockFundsTransactionSigned)
        .subscribe(x => console.log(x), err => console.error(err));

    listener
        .confirmed(cosignatoryAccount.address)
        .pipe(
            filter((transaction) => transaction.transactionInfo !== undefined
                && transaction.transactionInfo.hash === lockFundsTransactionSigned.hash),
            mergeMap(ignored => transactionHttp.announceAggregateBonded(signedTransaction))
        )
        .subscribe(announcedAggregateBonded => console.log(announcedAggregateBonded),
            err => console.error(err));
});