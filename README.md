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

#### From the `.jar` file:

This is the faster way to run the CLI; options and usage is identical to the
above, but instead of using `lein primes run`, you will use the following:

```
$ java -jar target/preiistmmo-0.1.0-SNAPSHOT-standalone.jar -m preiistmmo.core
```

For example, to view the help:
```
$ java -jar target/preiistmmo-0.1.0-SNAPSHOT-standalone.jar -m preiistmmo.core --help
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
