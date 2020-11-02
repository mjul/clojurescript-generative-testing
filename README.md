# Clojurescript Generative Testing with clojure.spec

Generative testing with clojure.spec in Clojurescript.


Start the Shadow-CLJS server in a Docker container  with

```
        docker-compose up
```

This opens a watcher on the tests. Open the test runner on http://localhost:8021 to see the status.

The `src` and `test` directories are mounted into the Docker container so you edit and see live
updates when the files are saved on the host (no need to access the Docker container or rebuild it).





