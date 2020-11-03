# Clojurescript Generative Testing with clojure.spec

Generative testing with clojure.spec in Clojurescript.


Start the Shadow-CLJS server in a Docker container  with

```
        docker-compose up
```

This opens a watcher on the tests. Open the test runner on http://localhost:8021 to see the status.

The `src` and `test` directories are mounted into the Docker container so you edit and see live
updates when the files are saved on the host (no need to access the Docker container or rebuild it).



# Common Errors with Spec on ClojureScript

The ClojureScript macro system is quite fickle compared to the Clojure implementation.
The `check` macro suffers from this.


Here are some common errors.

## Namespace aliasing does not work
Yes, that is right.
See the [core_test.cljs](./test/cljs/generative/core_test.cljs) file for details.


## Require clojure.test.check and clojure.test.check.properties

I have not been able to find the root cause of this error:

```
        Error: Require clojure.test.check and clojure.test.check.properties before calling check.
```

I cannot reproduce it in small, new, toy projects, however, it does appear in some bigger projects I am working on.

If you have any ideas, please let know how to fix it.


