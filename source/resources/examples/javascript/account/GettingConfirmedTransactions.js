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

const AccountHttp = AENSdk.AccountHttp,
    NetworkType = AENSdk.NetworkType,
    QueryParams = AENSdk.QueryParams,
    PublicAccount = AENSdk.PublicAccount;

const accountHttp = new AccountHttp('http://localhost:3000');

const publicKey = '7D08373CFFE4154E129E04F0827E5F3D6907587E348757B0F87D2F839BF88246';
const publicAccount =  PublicAccount.createFromPublicKey(publicKey, NetworkType.MIJIN_TEST);

const pageSize = 10; // Page size between 10 and 100, otherwise 10

accountHttp
    .transactions(publicAccount, new QueryParams(pageSize))
    .subscribe(transactions => console.log(transactions), err => console.error(err));