# preiistmmo

*Pondering scalability via primes ...*

[![Build Status][travis-badge]][travis]
[![Security Scan][security-scan-badge]][travis]
[![Dependencies Status][deps-badge]][travis]

[![Clojars Project][clojars-badge]][clojars]
[![Tag][tag-badge]][tag]

[![Clojure version][clojure-v]](project.clj)

[![][logo]][logo-large]


## Usage

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


## License

Copyright © 2018, Duncan McGreggor

Apache License, Version 2.0





<!-- Named page links below: /-->

[logo]: https://avatars0.githubusercontent.com/u/24504053?s=200&v=4
[logo-large]: https://avatars0.githubusercontent.com/u/24504053?v=4
[travis]: https://travis-ci.org/clojusc/results
[travis-badge]: https://travis-ci.org/clojusc/results.png?branch=master
[deps-badge]: https://img.shields.io/badge/deps%20check-passing-brightgreen.svg
[tag-badge]: https://img.shields.io/github/tag/clojusc/results.svg
[tag]: https://github.com/clojusc/results/tags
[clojure-v]: https://img.shields.io/badge/clojure-1.10.0-blue.svg
[clojars]: https://clojars.org/clojusc/results
[clojars-badge]: https://img.shields.io/clojars/v/clojusc/results.svg
[security-scan-badge]: https://img.shields.io/badge/nvd%2Fsecurity%20scan-passing-brightgreen.svg
