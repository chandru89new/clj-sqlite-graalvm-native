(defproject tt "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "EPL-2.0 OR GPL-2.0-or-later WITH Classpath-exception-2.0"
            :url "https://www.eclipse.org/legal/epl-2.0/"}
  :dependencies [[org.clojure/clojure "1.10.3"]
                 #_[clojure.java-time "0.3.2"]
                 [com.github.seancorfield/next.jdbc "1.2.674"]
                 [org.xerial/sqlite-jdbc "3.34.0"]
                 [ericdallo/sqlite-jni-graal-fix "0.0.3-graalvm-21.1.0"
                  :exclusions [org.graalvm.nativeimage/svm]]
                 [org.graalvm.nativeimage/svm "21.2.0"]]
  :main tt.core
  :target-path "target/%s"
  :profiles {:uberjar {:aot [tt.core]
                       :jvm-opts ["-Dclojure.compiler.direct-linking=true"]}})
