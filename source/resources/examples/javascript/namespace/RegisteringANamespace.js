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
const Account = AENSdk.Account,
    Deadline = AENSdk.Deadline,
    NetworkType = AENSdk.NetworkType,
    RegisterNamespaceTransaction = AENSdk.RegisterNamespaceTransaction,
    TransactionHttp = AENSdk.TransactionHttp,
    UInt64 = AENSdk.UInt64;

const transactionHttp = new TransactionHttp('http://localhost:3000');

const privateKey = process.env.PRIVATE_KEY;
const account = Account.createFromPrivateKey(privateKey, NetworkType.MIJIN_TEST);

const namespaceName = "foo"; //Replace with an unique namespace name

const registerNamespaceTransaction = RegisterNamespaceTransaction.createRootNamespace(
    Deadline.create(),
    namespaceName,
    UInt64.fromUint(1000),
    NetworkType.MIJIN_TEST);

const signedTransaction = account.sign(registerNamespaceTransaction);

transactionHttp
    .announce(signedTransaction)
    .subscribe(x => console.log(x), err => console.error(err));