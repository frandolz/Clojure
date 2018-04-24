(ns ex2.core
  (:gen-class))
  (defn calcular [line]
    (let [[num1 op num2]  (re-seq #"[0-9]+.[0-9]+|[0-9]+|[+]|[-]|[*]|[/]|[=]" line )]
    (let [n1 (clojure.edn/read-string num1)]
    (let [n2 (clojure.edn/read-string num2)]
      (cond
        (re-matches #"[+]" op) (println "La suma de " n1 " y " n2 " es" (+ n1 n2))
        (re-matches #"[-]" op) (println "La resta de " n1 " y " n2 " es" (- n1 n2))
        (re-matches #"[*]" op) (println "La multiplicacion de " n1 " y " n2 " es" (* n1 n2))
        (re-matches #"[/]" op) (println "La division de " n1 " y " n2 " es" (/ n1 n2))
      :else (println "Incorrecto"))))))
  (defn readFile [file]
    (with-open [rdr (clojure.java.io/reader file)]
      (doseq [line (line-seq rdr)]
        (cond
          (re-matches #"Calc(\s).*[;]" line) (calcular line)
        :else (println "Incorrecto")))))
  ;;(readFile "prueba3.txt")
  (def args *command-line-args*)
  (defn -main [& args]
    (if (empty? args)
      (println "No hay argumentos")
      (doseq [name args]
        (readFile name))))
