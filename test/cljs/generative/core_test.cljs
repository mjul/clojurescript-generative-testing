(ns generative.core-test
  (:require
   [generative.core :as c]
   [clojure.test :refer-macros [deftest testing is are]]
   [clojure.test.check]
   [clojure.test.check.properties]
   [cljs.spec.alpha :as s]
   [cljs.spec.test.alpha :as stest :refer-macros [instrument check]]))


(deftest clojure-spec-tests
  (testing "We can call enumerate-namespace"
    (is (stest/enumerate-namespace 'generative.core)))
  (testing "check (a macro)"
    (testing "We can call check on a function"
      (is (= [] (check 'c/foo))))
    (testing "We can call check on a namespace (no aliasing)"
      (is (check (cljs.spec.test.alpha/enumerate-namespace 'generative.core))))
    ;; The following fails to compile, indicating a problem with the check macro:
    ;;   Encountered error when macroexpanding cljs.spec.test.alpha/check.
    ;;   Error in phase :compile-syntax-check
    ;;   RuntimeException: No such namespace: stest
    #_(testing "We can call check on a namespace (using the alias)"
        (is (check (stest/enumerate-namespace 'generative.core))))
    (testing "We can combine enumerate and check with ->"
      (is (= [] (-> (cljs.spec.test.alpha/enumerate-namespace 'generative.core) check))))))
               

;; So now we know how to write it without namespace aliasing:

(deftest all-functions-must-conform-to-specs-test
  (let [results (-> (cljs.spec.test.alpha/enumerate-namespace 'generative.core)
                    check)
        failures (filter :failure results)
        abbrev (map stest/abbrev-result failures)]
    (testing "Summary"
         (is (empty? abbrev)))
    (testing "Details"
      (is (empty? failures)))))



