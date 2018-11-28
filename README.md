# preiistmmo

*Pondering scalability via primes ...*

[![Build Status][travis-badge]][travis]
[![Dependencies Status][deps-badge]][travis]

[![Clojars Project][clojars-badge]][clojars]
[![Tag][tag-badge]][tag]
[![Clojure version][clojure-v]](project.clj)

[![][logo]][logo-large]


#### Contents

* [Usage](#usage-)
  * [CLI](#cli-)
    * From lein
    * From the .jar file
  * [API](#api-)
* [Thoughts on Scaling](#thoughts-on-scaling-)
  * Algorithm implementation
  * Single machine
  * Multiple machines
* [Benchmarks](#benchmarks-)
* [License](#license-)


## Usage [&#x219F;](#contents)


### CLI [&#x219F;](#contents)

There are two entry points for the CLI:
* `primes` - this just does raw computation of prime numbers using an algorithm
  of choice
* `table` - this demos a UI (terminal) utilizing the prime-number generator


#### From `lein`

The following examples are for using the CLI to generate primes.

```
$ lein primes run --help
```
```
Options:

  -c, --count INTEGER   10        The number of primes to return
  -s, --start INTEGER   2         The starting integer (only valid for the 'divisors' algorithm)
  -a, --algorithm NAME  divisors  The prime number generating algorithm to use (one of ["sundaram" "eratosthenes" "divisors"])
  -h, --help                      This usage text
```

Default behaviour:

```
$ lein primes run
```
```
 (2 3 5 7 11 13 17 19 23 29)
```

Getting more primes:

```
$ lein primes run --count 20
```
```
 (2 3 5 7 11 13 17 19 23 29 31 37 41 43 47 53 59 61 67 71)
```

If you're using the default algorithm, you may also set the starting integer:

```
$ lein primes run --start 100 --count 20
```
```
 (101 103 107 109 113 127 131 137 139 149 151 157 163 167 173 179 181 191 193 197)
```

The following examples are for using the CLI to generate prime number multiplication tables.

```
$ lein table run --help
```
```
Options:

  -c, --columns INTEGER   10        The number of primes to to use for columns
  -r, --rows INTEGER      10        The number of primes to to use for rows
  -o, --operation SYMBOL  *         The arithmatic operation to perform (one of ["*" "%" "/" "-" "+"])
  -s, --start INTEGER     2         The starting integer (only valid for the 'divisors' algorithm)
  -a, --algorithm NAME    divisors  The prime number generating algorithm to use (one of ["sundaram" "eratosthenes" "divisors"])
  -h, --help                        This usage text
```

Default behaviour (a 10x10 grid):

```
$ lein table run
```
```
|    |  2 |  3 |   5 |   7 |  11 |  13 |  17 |  19 |  23 |  29 |
|----+----+----+-----+-----+-----+-----+-----+-----+-----+-----|
|  2 |  4 |  6 |  10 |  14 |  22 |  26 |  34 |  38 |  46 |  58 |
|  3 |  6 |  9 |  15 |  21 |  33 |  39 |  51 |  57 |  69 |  87 |
|  5 | 10 | 15 |  25 |  35 |  55 |  65 |  85 |  95 | 115 | 145 |
|  7 | 14 | 21 |  35 |  49 |  77 |  91 | 119 | 133 | 161 | 203 |
| 11 | 22 | 33 |  55 |  77 | 121 | 143 | 187 | 209 | 253 | 319 |
| 13 | 26 | 39 |  65 |  91 | 143 | 169 | 221 | 247 | 299 | 377 |
| 17 | 34 | 51 |  85 | 119 | 187 | 221 | 289 | 323 | 391 | 493 |
| 19 | 38 | 57 |  95 | 133 | 209 | 247 | 323 | 361 | 437 | 551 |
| 23 | 46 | 69 | 115 | 161 | 253 | 299 | 391 | 437 | 529 | 667 |
| 29 | 58 | 87 | 145 | 203 | 319 | 377 | 493 | 551 | 667 | 841 |
```
```
$ lein table run --columns 4 --rows 4
```
```
|   |  2 |  3 |  5 |  7 |
|---+----+----+----+----|
| 2 |  4 |  6 | 10 | 14 |
| 3 |  6 |  9 | 15 | 21 |
| 5 | 10 | 15 | 25 | 35 |
| 7 | 14 | 21 | 35 | 49 |
```
```
$ lein table run --rows 4
```
```
|   |  2 |  3 |  5 |  7 | 11 | 13 |  17 |  19 |  23 |  29 |
|---+----+----+----+----+----+----+-----+-----+-----+-----|
| 2 |  4 |  6 | 10 | 14 | 22 | 26 |  34 |  38 |  46 |  58 |
| 3 |  6 |  9 | 15 | 21 | 33 | 39 |  51 |  57 |  69 |  87 |
| 5 | 10 | 15 | 25 | 35 | 55 | 65 |  85 |  95 | 115 | 145 |
| 7 | 14 | 21 | 35 | 49 | 77 | 91 | 119 | 133 | 161 | 203 |
```
```
$ lein table run --columns 18 --rows 36 --operation %
```
```
|   % | 2 | 3 | 5 | 7 | 11 | 13 | 17 | 19 | 23 | 29 | 31 | 37 | 41 | 43 | 47 | 53 | 59 | 61 |
|-----+---+---+---+---+----+----+----+----+----+----+----+----+----+----+----+----+----+----|
|   2 | 0 | 2 | 2 | 2 |  2 |  2 |  2 |  2 |  2 |  2 |  2 |  2 |  2 |  2 |  2 |  2 |  2 |  2 |
|   3 | 1 | 0 | 3 | 3 |  3 |  3 |  3 |  3 |  3 |  3 |  3 |  3 |  3 |  3 |  3 |  3 |  3 |  3 |
|   5 | 1 | 2 | 0 | 5 |  5 |  5 |  5 |  5 |  5 |  5 |  5 |  5 |  5 |  5 |  5 |  5 |  5 |  5 |
|   7 | 1 | 1 | 2 | 0 |  7 |  7 |  7 |  7 |  7 |  7 |  7 |  7 |  7 |  7 |  7 |  7 |  7 |  7 |
|  11 | 1 | 2 | 1 | 4 |  0 | 11 | 11 | 11 | 11 | 11 | 11 | 11 | 11 | 11 | 11 | 11 | 11 | 11 |
|  13 | 1 | 1 | 3 | 6 |  2 |  0 | 13 | 13 | 13 | 13 | 13 | 13 | 13 | 13 | 13 | 13 | 13 | 13 |
|  17 | 1 | 2 | 2 | 3 |  6 |  4 |  0 | 17 | 17 | 17 | 17 | 17 | 17 | 17 | 17 | 17 | 17 | 17 |
|  19 | 1 | 1 | 4 | 5 |  8 |  6 |  2 |  0 | 19 | 19 | 19 | 19 | 19 | 19 | 19 | 19 | 19 | 19 |
|  23 | 1 | 2 | 3 | 2 |  1 | 10 |  6 |  4 |  0 | 23 | 23 | 23 | 23 | 23 | 23 | 23 | 23 | 23 |
|  29 | 1 | 2 | 4 | 1 |  7 |  3 | 12 | 10 |  6 |  0 | 29 | 29 | 29 | 29 | 29 | 29 | 29 | 29 |
|  31 | 1 | 1 | 1 | 3 |  9 |  5 | 14 | 12 |  8 |  2 |  0 | 31 | 31 | 31 | 31 | 31 | 31 | 31 |
|  37 | 1 | 1 | 2 | 2 |  4 | 11 |  3 | 18 | 14 |  8 |  6 |  0 | 37 | 37 | 37 | 37 | 37 | 37 |
|  41 | 1 | 2 | 1 | 6 |  8 |  2 |  7 |  3 | 18 | 12 | 10 |  4 |  0 | 41 | 41 | 41 | 41 | 41 |
|  43 | 1 | 1 | 3 | 1 | 10 |  4 |  9 |  5 | 20 | 14 | 12 |  6 |  2 |  0 | 43 | 43 | 43 | 43 |
|  47 | 1 | 2 | 2 | 5 |  3 |  8 | 13 |  9 |  1 | 18 | 16 | 10 |  6 |  4 |  0 | 47 | 47 | 47 |
|  53 | 1 | 2 | 3 | 4 |  9 |  1 |  2 | 15 |  7 | 24 | 22 | 16 | 12 | 10 |  6 |  0 | 53 | 53 |
|  59 | 1 | 2 | 4 | 3 |  4 |  7 |  8 |  2 | 13 |  1 | 28 | 22 | 18 | 16 | 12 |  6 |  0 | 59 |
|  61 | 1 | 1 | 1 | 5 |  6 |  9 | 10 |  4 | 15 |  3 | 30 | 24 | 20 | 18 | 14 |  8 |  2 |  0 |
|  67 | 1 | 1 | 2 | 4 |  1 |  2 | 16 | 10 | 21 |  9 |  5 | 30 | 26 | 24 | 20 | 14 |  8 |  6 |
|  71 | 1 | 2 | 1 | 1 |  5 |  6 |  3 | 14 |  2 | 13 |  9 | 34 | 30 | 28 | 24 | 18 | 12 | 10 |
|  73 | 1 | 1 | 3 | 3 |  7 |  8 |  5 | 16 |  4 | 15 | 11 | 36 | 32 | 30 | 26 | 20 | 14 | 12 |
|  79 | 1 | 1 | 4 | 2 |  2 |  1 | 11 |  3 | 10 | 21 | 17 |  5 | 38 | 36 | 32 | 26 | 20 | 18 |
|  83 | 1 | 2 | 3 | 6 |  6 |  5 | 15 |  7 | 14 | 25 | 21 |  9 |  1 | 40 | 36 | 30 | 24 | 22 |
|  89 | 1 | 2 | 4 | 5 |  1 | 11 |  4 | 13 | 20 |  2 | 27 | 15 |  7 |  3 | 42 | 36 | 30 | 28 |
|  97 | 1 | 1 | 2 | 6 |  9 |  6 | 12 |  2 |  5 | 10 |  4 | 23 | 15 | 11 |  3 | 44 | 38 | 36 |
| 101 | 1 | 2 | 1 | 3 |  2 | 10 | 16 |  6 |  9 | 14 |  8 | 27 | 19 | 15 |  7 | 48 | 42 | 40 |
| 103 | 1 | 1 | 3 | 5 |  4 | 12 |  1 |  8 | 11 | 16 | 10 | 29 | 21 | 17 |  9 | 50 | 44 | 42 |
| 107 | 1 | 2 | 2 | 2 |  8 |  3 |  5 | 12 | 15 | 20 | 14 | 33 | 25 | 21 | 13 |  1 | 48 | 46 |
| 109 | 1 | 1 | 4 | 4 | 10 |  5 |  7 | 14 | 17 | 22 | 16 | 35 | 27 | 23 | 15 |  3 | 50 | 48 |
| 113 | 1 | 2 | 3 | 1 |  3 |  9 | 11 | 18 | 21 | 26 | 20 |  2 | 31 | 27 | 19 |  7 | 54 | 52 |
| 127 | 1 | 1 | 2 | 1 |  6 | 10 |  8 | 13 | 12 | 11 |  3 | 16 |  4 | 41 | 33 | 21 |  9 |  5 |
| 131 | 1 | 2 | 1 | 5 | 10 |  1 | 12 | 17 | 16 | 15 |  7 | 20 |  8 |  2 | 37 | 25 | 13 |  9 |
| 137 | 1 | 2 | 2 | 4 |  5 |  7 |  1 |  4 | 22 | 21 | 13 | 26 | 14 |  8 | 43 | 31 | 19 | 15 |
| 139 | 1 | 1 | 4 | 6 |  7 |  9 |  3 |  6 |  1 | 23 | 15 | 28 | 16 | 10 | 45 | 33 | 21 | 17 |
| 149 | 1 | 2 | 4 | 2 |  6 |  6 | 13 | 16 | 11 |  4 | 25 |  1 | 26 | 20 |  8 | 43 | 31 | 27 |
| 151 | 1 | 1 | 1 | 4 |  8 |  8 | 15 | 18 | 13 |  6 | 27 |  3 | 28 | 22 | 10 | 45 | 33 | 29 |
```


#### From the `.jar` file:

This is the faster way to run the CLI; options and usage is identical to the
above, but instead of using `lein primes run`, you will use the following:

```
$ lein uberjar
$ java -jar target/preiistmmo-0.1.0-SNAPSHOT-standalone.jar -m preiistmmo.cli.primes
```

For example, to view the help:
```
$ java -jar target/preiistmmo-0.1.0-SNAPSHOT-standalone.jar -m preiistmmo.cli.primes --help
```

or, for `table`:

```
$ java -jar target/preiistmmo-0.1.0-SNAPSHOT-standalone.jar -m preiistmmo.cli.table --help
```


### API [&#x219F;](#contents)

Generate the first-n primes using a primality trial by divisors implementation:

```clj
(require '[preiistmmo.core :as preiistmmo])
(def algo (preiistmmo/select-algo :divisors))
(preiistmmo/n-primes algo 29)
```
```
(2 3 5 7 11 13 17 19 23 29 31 37 41 43 47 53 59 61 67 71 73 79 83 89 97 101 103 107 109)
```

Note that of the three, the `:divisors` algorithm has the ability to deliver
sequences of prime numbers at any starting point. This makes it very convenient
for spliting work across different compute resources.

Changing the alorithm, the same can be done for the other implementations.

Sieve of Eratosthenes:

```clj
(def algo (preiistmmo/select-algo :eratosthenes))
(preiistmmo/n-primes algo 29)
```
```
(2 3 5 7 11 13 17 19 23 29 31 37 41 43 47 53 59 61 67 71 73 79 83 89 97 101 103 107 109)
```

Sieve of Sundaram:

```clj
(def algo (preiistmmo/select-algo :sundaram))
(preiistmmo/n-primes algo 29)
```
```
(2 3 5 7 11 13 17 19 23 29 31 37 41 43 47 53 59 61 67 71 73 79 83 89 97 101 103 107 109)

```

Support has been added for grids (e.g., in termainal-based demos):

```clj
(require '[clojure.pprint :as pprint])
(pprint (prime-grid algo 5 7))
```
```clj
{:header ("*" 2 3 5 7 11),
 :body
 ({"*" 2, 2 4, 3 6, 5 10, 7 14, 11 22}
  {"*" 3, 2 6, 3 9, 5 15, 7 21, 11 33}
  {"*" 5, 2 10, 3 15, 5 25, 7 35, 11 55}
  {"*" 7, 2 14, 3 21, 5 35, 7 49, 11 77}
  {"*" 11, 2 22, 3 33, 5 55, 7 77, 11 121})}
```
```clj
(preiistmmo/print-prime-grid algo 5 7)
```
```
|  * |  2 |  3 |  5 |  7 |  11 |
|----+----+----+----+----+-----|
|  2 |  4 |  6 | 10 | 14 |  22 |
|  3 |  6 |  9 | 15 | 21 |  33 |
|  5 | 10 | 15 | 25 | 35 |  55 |
|  7 | 14 | 21 | 35 | 49 |  77 |
| 11 | 22 | 33 | 55 | 77 | 121 |
:ok
```


## Thoughts on Scaling [&#x219F;](#contents)

Algorithm implementation:

 * This is the domain of "old-school scaling": optimization of algorithms for
   performance
 * The `:sundaram` implementation is in sad need of this! The use of `set`
   and `intersection` is a performance-killer: it would be better to do
   something like what the copied "Sieve of Eratosthenes" implementation does,
   using a factors caching table, or some such.
 * There are other implementations that could be used that would be faster, etc.

Single machine:

 * Moving out of the code and into hardware for performance, one could take
   better advantage of multiple cores.
 * Possible refactoring of code applies here, in order to take advantage of
   parallelizations, e.g., using `pmap` or `pvalues`.
 * The `:divisors` implementation is nicely primed for this (ugh, sorry about
   the pun), as you can start the prime generation from any point. This allows
   for the possibility of some nice batching.
 * Using infrastructure that can treat vast computational/memory/storage
   resources as a single machine is also an option, e.g., Mesos. It may be
   possible to scale well in such scenarious by simply containerizing a fast
   algorithm and running it in an executor that has access to copious
   distributed resources.

Multiple machines:

 * This is where things get _really_ interesting ;-)
 * Using various messaging and/or streaming mechanisms, prime number generation
   could be batched and then assembled (a la Hadoop/MapReduce). Storm, Flink,
   Kafka, Onyx, etc., offer interesting possibilities here.
 * While the above work quite well for embarassingly parallelizable functions,
   an interesting option becomes available when we think about factorization
   cache tables and distributed hash tables: this opens up a whole new area for
   optimizations where compute notes could perform lookups (and updates) on a
   distributed hash table (lots of implementations to choose from). Depending
   upon operational setup, database clusters (Cassandra, Redis, Datomic) could
   also be used to similar effect. For the copied "Sieve of Eratosthenes"
   implementation, a local in-JVM cache table is already being used, and a move
   to a DHT should be a fairly straight-forward (potentially minor) refactoring
   (the effort and complexity, of course, then moves from code to operations).


## Benchmarks [&#x219F;](#contents)

Of a sort, anyway:

```
$ lein benchmarks run
```
```
Running n-primes for :divisors implementation ...
10 primes: "Elapsed time: 1.41252 msecs"
100 primes: "Elapsed time: 1.922843 msecs"
1000 primes: "Elapsed time: 8.307988 msecs"
10000 primes: "Elapsed time: 233.71016 msecs"
100000 primes: "Elapsed time: 6136.338203 msecs"

Running n-primes for :eratosthenes implementation ...
10 primes: "Elapsed time: 1.661216 msecs"
100 primes: "Elapsed time: 1.822298 msecs"
1000 primes: "Elapsed time: 14.686621 msecs"
10000 primes: "Elapsed time: 240.921639 msecs"
100000 primes: "Elapsed time: 4363.927017 msecs"

Running n-primes for :sundaram implementation ...
10 primes: "Elapsed time: 4.086518 msecs"
100 primes: "Elapsed time: 3.433624 msecs"
1000 primes: "Elapsed time: 25.422443 msecs"
10000 primes: "Elapsed time: 1165.294828 msecs"
100000 primes:"Elapsed time: 166559.860863 msecs"
```


## License [&#x219F;](#contents)

Copyright © 2018, Duncan McGreggor

Apache License, Version 2.0


<!-- Named page links below: /-->

[logo]: https://avatars0.githubusercontent.com/u/24504053?s=200&v=4
[logo-large]: https://avatars0.githubusercontent.com/u/24504053?v=4
[travis]: https://travis-ci.org/calyau/preiistmmo
[travis-badge]: https://travis-ci.org/calyau/preiistmmo.png?branch=master
[deps-badge]: https://img.shields.io/badge/deps%20check-passing-brightgreen.svg
[tag-badge]: https://img.shields.io/github/tag/calyau/preiistmmo.svg
[tag]: https://github.com/calyau/preiistmmo/tags
[clojure-v]: https://img.shields.io/badge/clojure-1.10.0-blue.svg
[clojars]: https://clojars.org/calyau/preiistmmo
[clojars-badge]: https://img.shields.io/clojars/v/calyau/preiistmmo.svg
