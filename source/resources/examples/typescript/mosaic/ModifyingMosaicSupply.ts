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

import {
    Account,
    Deadline,
    MosaicId,
    MosaicSupplyChangeTransaction,
    MosaicSupplyType,
    NetworkType,
    TransactionHttp,
    UInt64
} from 'AEN-sdk';

const transactionHttp = new TransactionHttp('http://localhost:3000');

const privateKey = process.env.PRIVATE_KEY as string;
const account = Account.createFromPrivateKey(privateKey, NetworkType.MIJIN_TEST);

const mosaicID = new MosaicId('foo:token');

const mosaicSupplyChangeTransaction = MosaicSupplyChangeTransaction.create(
    Deadline.create(),
    mosaicID,
    MosaicSupplyType.Increase,
    UInt64.fromUint(2000000),
    NetworkType.MIJIN_TEST);

const signedTransaction = account.sign(mosaicSupplyChangeTransaction);

transactionHttp
    .announce(signedTransaction)
    .subscribe(x=> console.log(x), err => console.error(err));