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
const NetworkType = AENSdk.NetworkType,
    Account = AENSdk.Account;

const account = Account.generateNewAccount(NetworkType.MIJIN_TEST);

console.log('Your new account address is:', account.address.pretty(), 'and its private key', account.privateKey);