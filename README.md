Time-Based One-Time Password
============================

* https://tools.ietf.org/html/rfc4226
* https://tools.ietf.org/html/rfc6238

# Preparation

~/.onetime/onetime.properties

```
address=xxxx-account-mfa-device@XXXXXXXXXXXX
secret=XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX
```

```
$ chmod 500 ~/.onetime
$ chmod 400 ~/.onetime/onetime.properties
```

# Build and Run

```
$ sbt assembly
$ java -jar target/scala-2.12/jp.pigumer.onetime-assembly-0.0.1-SNAPSHOT.jar
```
