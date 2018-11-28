# preiistmmo

*Pondering scalability via primes ...*

[![Build Status][travis-badge]][travis]
[![Dependencies Status][deps-badge]][travis]

[![Clojars Project][clojars-badge]][clojars]
[![Tag][tag-badge]][tag]
[![Clojure version][clojure-v]](project.clj)

[![][logo]][logo-large]


## Usage


### CLI

There are two entry points for the CLI:
* `primes` - this just does raw computation of prime numbers using an algorithm
  of choice
* `table` - this demos a UI (terminal) utilizing the prime-number generator


#### From `lein`

```
$ lein primes run --help
```
```
Options:

  -c, --count INTEGER   10        The number of primes to return
  -s, --start INTEGER   2         The starting integer (only valid for the 'divisors' algorithm)
  -a, --algorithm NAME  divisors  The prime number generating algorithm to use (one of ["sundaram" "eratosthenes" "divisors"])
  -h, --help                      This usage text.
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

```
$ lein table run --help
```
```
Options:

  -c, --columns INTEGER  10        The number of primes to to use for columns
  -r, --rows INTEGER     10        The number of primes to to use for rows
  -s, --start INTEGER    2         The starting integer (only valid for the 'divisors' algorithm)
  -a, --algorithm NAME   divisors  The prime number generating algorithm to use (one of ["sundaram" "eratosthenes" "divisors"])
  -h, --help
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
$ lein table run --columns 14 --rows 20
```
```
|    |   2 |   3 |   5 |   7 |  11 |  13 |   17 |   19 |   23 |   29 |   31 |   37 |   41 |   43 |
|----+-----+-----+-----+-----+-----+-----+------+------+------+------+------+------+------+------|
|  2 |   4 |   6 |  10 |  14 |  22 |  26 |   34 |   38 |   46 |   58 |   62 |   74 |   82 |   86 |
|  3 |   6 |   9 |  15 |  21 |  33 |  39 |   51 |   57 |   69 |   87 |   93 |  111 |  123 |  129 |
|  5 |  10 |  15 |  25 |  35 |  55 |  65 |   85 |   95 |  115 |  145 |  155 |  185 |  205 |  215 |
|  7 |  14 |  21 |  35 |  49 |  77 |  91 |  119 |  133 |  161 |  203 |  217 |  259 |  287 |  301 |
| 11 |  22 |  33 |  55 |  77 | 121 | 143 |  187 |  209 |  253 |  319 |  341 |  407 |  451 |  473 |
| 13 |  26 |  39 |  65 |  91 | 143 | 169 |  221 |  247 |  299 |  377 |  403 |  481 |  533 |  559 |
| 17 |  34 |  51 |  85 | 119 | 187 | 221 |  289 |  323 |  391 |  493 |  527 |  629 |  697 |  731 |
| 19 |  38 |  57 |  95 | 133 | 209 | 247 |  323 |  361 |  437 |  551 |  589 |  703 |  779 |  817 |
| 23 |  46 |  69 | 115 | 161 | 253 | 299 |  391 |  437 |  529 |  667 |  713 |  851 |  943 |  989 |
| 29 |  58 |  87 | 145 | 203 | 319 | 377 |  493 |  551 |  667 |  841 |  899 | 1073 | 1189 | 1247 |
| 31 |  62 |  93 | 155 | 217 | 341 | 403 |  527 |  589 |  713 |  899 |  961 | 1147 | 1271 | 1333 |
| 37 |  74 | 111 | 185 | 259 | 407 | 481 |  629 |  703 |  851 | 1073 | 1147 | 1369 | 1517 | 1591 |
| 41 |  82 | 123 | 205 | 287 | 451 | 533 |  697 |  779 |  943 | 1189 | 1271 | 1517 | 1681 | 1763 |
| 43 |  86 | 129 | 215 | 301 | 473 | 559 |  731 |  817 |  989 | 1247 | 1333 | 1591 | 1763 | 1849 |
| 47 |  94 | 141 | 235 | 329 | 517 | 611 |  799 |  893 | 1081 | 1363 | 1457 | 1739 | 1927 | 2021 |
| 53 | 106 | 159 | 265 | 371 | 583 | 689 |  901 | 1007 | 1219 | 1537 | 1643 | 1961 | 2173 | 2279 |
| 59 | 118 | 177 | 295 | 413 | 649 | 767 | 1003 | 1121 | 1357 | 1711 | 1829 | 2183 | 2419 | 2537 |
| 61 | 122 | 183 | 305 | 427 | 671 | 793 | 1037 | 1159 | 1403 | 1769 | 1891 | 2257 | 2501 | 2623 |
| 67 | 134 | 201 | 335 | 469 | 737 | 871 | 1139 | 1273 | 1541 | 1943 | 2077 | 2479 | 2747 | 2881 |
| 71 | 142 | 213 | 355 | 497 | 781 | 923 | 1207 | 1349 | 1633 | 2059 | 2201 | 2627 | 2911 | 3053 |
```


#### From the `.jar` file:

This is the faster way to run the CLI; options and usage is identical to the
above, but instead of using `lein primes run`, you will use the following:

```
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


### API

Generate the first-n primes using a Sieve of Sundaram implementation:

```clj
(require '[preiistmmo.core :as preiistmmo])
(def algo (preiistmmo/select-algo :sundaram))
(preiistmmo/n-primes algo 42)
```
```
(2 3 5 7 11 13 17 19 23 29 31 37 41)
```

The same can be done for the Sieve of Eratosthenes:

```clj
(def algo (preiistmmo/select-algo :eratosthenes))
(preiistmmo/n-primes algo 42)
```
```
(2 3 5 7 11 13 17 19 23 29 31 37 41)
```

and a primality trial by divisors implementation:

```clj
(def algo (preiistmmo/select-algo :divisors))
(preiistmmo/n-primes algo 42)
```
```
(2 3 5 7 11 13 17 19 23 29 31 37 41)
```

Note that of the three, the `:divisors` algorithm has the ability to deliver
sequences of prime numbers at any starting point. This makes it very convenient
for spliting work across different compute resources.


## Thoughts on Scaling

TBD


## Benchmarks

Of a sort, anyway:

```
$ lein benchmarks
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


## License

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
