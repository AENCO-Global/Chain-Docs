##########################
Extending AEN capabilities
##########################

Aside from creating a heavy and full-featured :doc:`SDK<../sdk/overview>`, we are developing a robust and lightweight one. Thanks to this SDK, different libraries can be developed for each standard, on top of AEN-SDK, allowing higher and faster growth for AEN applications.

Developers could opt to create a standalone library to add more features into AEN. However, to become an accepted library, it should be proposed as a |NIP|.

******************
Official libraries
******************

The reason behind the |NIP| is to ensure that the new library is reviewed, tested and shared among AEN developers.

* Accepted specification
* At least one implementation that successfully passes the code review.
* Compatible with third-party projects that implement/uses the same NIP Library.

*******************
Community libraries
*******************

Do you need to create a NIP to create a library? No, you don’t. In fact, we suggest not submitting a NIP until you build a library that improves the AEN blockchain and the different projects that use it. Since the library can be changed multiple times, developers should spend more time experimenting and learning, creating a specification later on.

.. csv-table::
   :header: "Name", "Description", "Active Developers"
   :delim: ;

   `Apostille library <https://github.com/luxtagofficial/Apostille-library>`_ ; Blockchain notarization and timestamping with transferable, updatable, branded, and conjointly owned notarizations.; `@luxtagofficial <https://github.com/luxtagofficial/>`_
   `Non-fungible asset library <https://github.com/aleixmorgadas/AEN-nonfungible-asset>`_ ; Work with unique and updatable assets.;`@aleixmorgadas <https://github.com/aleixmorgadas/>`_

.. |NIP| raw:: html

   <a href="https://github.com/AENtech/NIP" target="_blank">AEN Improvement Proposal</a>