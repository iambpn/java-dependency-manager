# java-dependency-manager

## Compile java file

```
javac -cp ".:lib/*" -d bin $(find ./src/* | grep .java)
```

## Build executable Jar file

```
jar --create --file jdm.jar --manifest=MANIFEST.MF -C bin/ .
```
