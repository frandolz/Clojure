(ns ex1.core
  (:gen-class))
  (defn nombre [nombre]
    (if (re-matches #"Nombre:(\s)([A-Z](\w+))(\s)([A-Z](\w+))(\s)([A-Z](\w+))" nombre) (println "Nombre correcto") (println "Nombre incorrecto")))
  (defn email [email]
    (if (re-matches #"Email:(\s)(\w+)@(\w+).(\w+)" email) (println "Email correcto") (println "Email incorrecto")))
  (defn telefono [telefono]
    (if (re-matches #"Telefono:(\s)([0-9]{9}|[+][0-9]{11})" telefono) (println "Telefono correcto") (println "Telefono incorrecto")))

  (defn readFile [file]
    (with-open [rdr (clojure.java.io/reader file)]
      (doseq [line (line-seq rdr)]
        (cond
          (re-matches #"Email.*" line) (email line)
          (re-matches #"Nombre:.*"line) (nombre line)
          (re-matches #"Telefono:.*" line) (telefono line)
        :else (println "Incorrecto"))
        (println line))))
  ;;(readFile "prueba3.txt")

  (def args *command-line-args*)
  (defn -main [& args]
    (if (empty? args)
      (println "No hay argumentos")
      (doseq [name args]
        (readFile name))))
