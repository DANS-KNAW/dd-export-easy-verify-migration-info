dd-export-easy-verify-migration-info
===================

SYNOPSIS
--------

```text   
dd-export-easy-verify-migration-info { server | check  | load-from-fedora | load-from-vault | load-from-dataverse } ...
```

DESCRIPTION
-----------

Database for comparing expected and actual files and datasets after migration to data station.


ARGUMENTS
---------
    
```text
positional arguments:
{server,check,load-from-fedora,load-from-dataverse,load-from-vault} ...
                            available commands

named arguments:      
-h, --help                   show this help message and exit
-v, --version                show the application version and exit

load-from-dataverse [-h] [-d DOI | --csv CSV] [file]

Load actual table(s) with file and/or dataset info from dataverse.

positional arguments:
  file                         application configuration file (default: etc/config.yml)

named arguments:      
  -d DOI, --doi DOI            The DOI for which to load the files,
                               for example: 'doi:10.17026/dans-xtz-qa6j'
                               Use csv with csv and comment column to load multiple DOIs.
  --csv CSV                    CSV file produced by easy-fedora-to-bag
  --mode {BOTH,FILES,DATASETS} files require more writing, dataset require more reading, BOTH=FILES+DATASETS (default: DATASETS)
  --UUIDs UUIDS                .txt file with bag ids
  -h, --help                   show this help message and exit

load-from-vault [-h] (-u UUIDS | -U UUID | -s STORE) [file]
   
Load expected table(s) with info from manifest-sha1.txt of bags in the vault and/or from metadata/dataset.xml.
When mode=INPUT the InputDatasets are loaded instead.

positional arguments:
  file                         application configuration file (default: etc/config.yml)

named arguments:     
  --mode {BOTH,FILES,DATASETS,INPUT}  files require more writing, dataset require more reading, BOTH=FILES+DATASETS (default: DATASETS)
  -u UUIDS, --uuids UUIDS      file with UUIDs of a bag in the vault
  -U UUID, --UUID UUID         UUID of a bag in the vault
  -s STORE, --store STORE      name of a bag store in the vault
  -h, --help                   show this help message and exit

load-from-fedora [-c [FILE]] [-h] csv [csv ...]

Load expected table(s) with info from easy_files in fs-rdb and transformation rules.
When mode=INPUT the InputDatasets are loaded instead.

positional arguments:
  csv                          CSV file produced by easy-fedora-to-bag

named arguments:
  -c [FILE], --config [FILE]   application configuration file (default: etc/config.yml)
  --mode {BOTH,FILES,DATASETS,INPUT}  files require more writing, dataset require more reading, BOTH=FILES+DATASETS (default: DATASETS)
  -h, --help                   show this help message and exit
```


EXAMPLES
--------

```text
dd-export-easy-verify-migration-info load-from-dataverse
dd-export-easy-verify-migration-info load-from-vault -u uuids.txt
dd-export-easy-verify-migration-info load-from-fedora easy-fedora-to-bag-log.csv
```

INSTALLATION AND CONFIGURATION
------------------------------
Currently, this project is built as an RPM package for RHEL7/CentOS7 and later. The RPM will install the binaries to
`/opt/dans.knaw.nl/dd-export-easy-verify-migration-info` and the configuration files to `/etc/opt/dans.knaw.nl/dd-export-easy-verify-migration-info`. 

For installation on systems that do no support RPM and/or systemd:

1. Build the tarball (see next section).
2. Extract it to some location on your system, for example `/opt/dans.knaw.nl/dd-export-easy-verify-migration-info`.
3. Start the service with the following command
        
        opt/dans.knaw.nl/dd-export-easy-verify-migration-info/bin/dd-export-easy-verify-migration-info server \
         /opt/dans.knaw.nl/dd-export-easy-verify-migration-info/cfg/config.yml 
        

The file `/opt/dans.knaw.nl/dd-export-easy-verify-migration-info/cfg/account-substitutes.csv` will be delivered with just a header line.
The content of the file should equal the substitution file configured for `easy-convert-bag-to-deposit`.
To ignore substitution for testing purposes: add just one line with identical values in both columns.

BUILDING FROM SOURCE
--------------------
Prerequisites:

* Java 11 or higher
* Maven 3.3.3 or higher
* RPM

Steps:
    
```text
git clone https://github.com/DANS-KNAW/dd-export-easy-verify-migration-info.git
cd dd-export-easy-verify-migration-info 
mvn clean install
```    

If the `rpm` executable is found at `/usr/local/bin/rpm`, the build profile that includes the RPM 
packaging will be activated. If `rpm` is available, but at a different path, then activate it by using
Maven's `-P` switch: `mvn -Pprm install`.

Alternatively, to build the tarball execute:

```text
mvn clean install assembly:single
```
