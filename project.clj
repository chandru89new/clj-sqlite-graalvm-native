(defproject tt "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "EPL-2.0 OR GPL-2.0-or-later WITH Classpath-exception-2.0"
            :url "https://www.eclipse.org/legal/epl-2.0/"}
  :dependencies [[org.clojure/clojure "1.10.1"]
                 [clojure.java-time "0.3.2"]
                 [com.github.seancorfield/next.jdbc "1.2.674"]
                 [org.xerial/sqlite-jdbc "3.23.1"]
                 [ericdallo/sqlite-jni-graal-fix "0.0.3-graalvm-21.1.0"]]
  :main ^:skip-aot tt.core
  :aot :all
  :target-path "target/%s"
  :profiles {:uberjar {:aot :all
                       :jvm-opts ["-Dclojure.compiler.direct-linking=true"]}})
