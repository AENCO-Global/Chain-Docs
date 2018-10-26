########
Overview
########

The AEN Command Line Interface is a unified tool to interact with the AEN blockchain.

This tool will enable you to perform the most common used actions to interact with the blockchain.

**AEN-CLI** is an open source tool built on top of the :doc:`AEN-SDK<../sdk/overview>` Typescript. Use it in your favorite terminal program.

************
Installation
************

AEN-CLI is distributed using the node package manager ``npm``.

To install:

.. code-block:: bash

    $> sudo npm install --global AEN-cli

To update:

.. code-block:: bash

    $> sudo npm update --global AEN-cli

*************
Configuration
*************

To start using AEN-CLI, configure a profile.

A profile holds an account and a node url for a specific network. Profiles are used to set a base url and have an account to sign transactions.

Configure default profile.

.. code-block:: bash

    $> AEN-cli profile create --privatekey your_private_key --network MIJIN_TEST --url http://localhost:3000

AEN-CLI supports named profiles. You can configure additional profiles by using the --profile option.

.. code-block:: bash

    $> AEN-cli profile create --privatekey your_private_key --network MIJIN_TEST --url http://localhost:3000 --profile mijin_test_net_profile

By default, AEN-CLI will always use the default profile. To use a named profile, add the --profile option to the command.

.. code-block:: bash

    $> AEN-cli account info --profile mijin_test_net_profile

If you are going to use named profile for multiple commands, you can use the AEN_PROFILE environment variable at the command line.

.. code-block:: bash

    $> export AEN_PROFILE=mijin_test_net_profile

If you do not have a private key to create a profile you can generate a new account, add a node url and save it as default or named profile.

.. code-block:: bash

    $> AEN-cli account generate --network MIJIN_TEST -s --url http://localhost:3000 --profile mijin_test_net_profile


Continue: :doc:`Commands <commands>`.