# noise-control

A Clojure library designed to control noise in text files.

## Usage

The suggested pipeline is to filter files sentence by sentence to see if they parse as english. If so, then sanitize-string each sentence. Save just those results.

## License

Copyright © 2013 Cicayda LLC

Distributed under the Eclipse Public License, the same as Clojure.
