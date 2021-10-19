# foreman-guardrail

## Status ##

[![Build Status](https://travis-ci.com/delawr0190/foreman-guardrail.svg?branch=main)](https://travis-ci.com/delawr0190/foreman-guardrail)

## Guardrail ##

Guardrail is an open-source Java application that provides a mechanism to 
set constraints on [Pickaxe](https://github.com/delawr0190/foreman-apps), 
the Foreman miner monitoring and management agent.  This application is 
intended to be used in a miner hosting environment where the facility would 
like to apply local constraints, limiting the IPs that the Foreman agent can 
reach.  This puts the operator fully in control.

A typical use case for this would be:

- A colocation/hosting facility has many miners hosted, each owned by different 
  customers
- The facility would like to allow the customer access to their miners 
  through [Foreman](https://foreman.mn)
- The facility would like to apply local constraints rather than relying 
  fully on [Foreman dashboard permissions](https://foreman.mn/blog/managing-access-to-cryptocurrency-miners/)

## Requirements ##

- JDK version 8 (or higher)
- Linux (this application is currently not compatible with Windows)

## Building ##

To build the entire foreman-guardrail repository, from the top level of the 
repository:

```sh
$ mvn clean install
```

Upon a successful build, you should see something similar to the following:

```sh
[INFO] ------------------------------------------------------------------------
[INFO] BUILD SUCCESS
[INFO] ------------------------------------------------------------------------
[INFO] Total time: 39.070 s
[INFO] Finished at: 2018-07-07T19:54:47-04:00
[INFO] Final Memory: 46M/494M
[INFO] ------------------------------------------------------------------------
```

The application distributions can be found in the `target/` folders.  You'll only need one - pick the extension you prefer.

```sh
$ ls -la **/target | grep -E "\.(zip|tar)"
-rw-r--r--  1 dan dan 9621049 Jul  7 19:54 foreman-guardrail-1.0.0-SNAPSHOT-bin.tar.bz2
-rw-r--r--  1 dan dan 9662936 Jul  7 19:54 foreman-guardrail-1.0.0-SNAPSHOT-bin.tar.gz
-rw-r--r--  1 dan dan 9669478 Jul  7 19:54 foreman-guardrail-1.0.0-SNAPSHOT-bin.zip
$

```

## License ##

Copyright © 2021, [OBM LLC](https://obm.mn/).  Released under the [GPL-3.0 License](LICENSE).