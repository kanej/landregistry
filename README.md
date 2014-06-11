# landregistry

Some incanter magic working against the land registry's datasets.

## Usage

Firstly pulled down the latest monthly sales file from the land registry. There is a script to do this under the data folder:

```bash
> ./data/download.sh
```

With the data in place, start up a repl session using leiningen:

```bash
> lein repl
```

By default, the user namespace will load in the monthly sales figures from the data folder. This dataset is availabe as the monthly-sales var.

There are currently two functions (both in the landregistry namespace) that operate over the sales data. The first is show-all-town-entries, which lists the town column of the sales data.

```clojure
> (lr/show-all-town-entries months-sales)
```

Note that the landregistry namespace is aliased to lr in the user namespace.

The second function is show-top-twenty-towns. This operates over the town column of all the sales, finding the 20 towns in which the most sales have occurred.

```clojure
> (lr/show-top-twenty-towns months-sales)
```

## Change Log

* Version 0.1.0-SNAPSHOT - first pass at finding the towns with the most sales.

## Copyright and License

Copyright © 2014 John Kane
