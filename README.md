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

## Namespace aliasing does not work with `check`
Yes, that is right.
See the [core_test.cljs](./test/cljs/generative/core_test.cljs) file for details.

```clojure
(ns generative.core-test
  (:require
   [generative.core :as c]
   [clojure.test :refer-macros [deftest testing is are]]
   [clojure.test.check]
   [clojure.test.check.properties]
   [cljs.spec.alpha :as s]
   [cljs.spec.test.alpha :as stest :refer-macros [instrument check]]))

;; details elided
 
    (testing "We can call check on a namespace (using the alias)"
      (is (check (stest/enumerate-namespace 'generative.core))))
```

Here the `check` macro throws up on the aliased namespace and you will see this:

```

        Encountered error when macroexpanding cljs.spec.test.alpha/check.
        Error in phase :compile-syntax-check
        RuntimeException: No such namespace: stest
```

The workaround is to use fully qualified namespaces instead of aliases.



## Require clojure.test.check and clojure.test.check.properties

I have not been able to find the root cause of this error:

```
        Error: Require clojure.test.check and clojure.test.check.properties before calling check.
```

I cannot reproduce it in small, new, toy projects, however, it does appear in some bigger projects I am working on.

If you have any ideas, please let know how to fix it.


