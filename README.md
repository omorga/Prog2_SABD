# Installazione

Installare maven e java 8

Scaricare flink 1.2.0

# Configurazione

Modificare in src/main/java/configuration/Configuration.java il percorso nell'attributo DATASET_FILE specificando il percorso del repository full-game

# Avvio

L'applicazione può essere eseguita in due modalità:
1) tramite IDE (IntelliJ - Eclipse)
2) tramite Flink: creare il JAR del progetto con il comando `mvn install` eseguito da terminale nella directory contenente il file `pom.xml`. Eseguire lo script `start-local.sh` nella directory `bin` di Flink per avviare la UI di Flink.

L'applicazione può essere avviata in due modalità:
1) tramite CLI eseguendo `flink` nella directory `bin` di Flink, tramite il comando `flink run -c core.SoccerApp /path/to/jar/file.jar`
2) tramite UI di Flink,contattabile tramite browser alla porta 8081, nella sezione `Submit new Job` -> Add new -> Upload -> Selezionare il giusto JAR -> Entry Class: core.SoccerApp -> 	Parallelism: X (1-2-3-4) -> Submit
