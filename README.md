# java-dependency-manager

## Docs

- https://docs.oracle.com/javase/tutorial/deployment/jar/downman.html

## Compile java file

```
javac -cp ".:lib/*" -d bin $(find ./src/* | grep .java)
```

## Build executable Jar file

```
jar --create --file jdm.jar --manifest=MANIFEST.MF -C bin/ .
```

# Pack jar file to executable with [packr](https://github.com/libgdx/packr)

```
java -jar packr.jar packr.json
```
