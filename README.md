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

## JDM Setup

JDM is a dependency manager which tries to mimic NPM like dependency management through json file. This do not includes any kind of build system. JDM only retrieves the jar file from repository and saves it to `<project root>/lib` folder. How to link these lib folder while compiling totally depends on user.

JDM is specially developed to work with vscode java extension. It also includes option to initialize `vscode setting` for java which is handy tool to initialize empty java project in vscode.

Steps to setup JDM:

- [Download]() exec file from release
- extract zip file
- add extracted path to OS env path.
- open terminal and run `jdm -h`

## Usage

Usage: java JDM <command> [<args>]

Commands: help [--help -h] Usage help init <option> Initialize 'jdm.json' or '.vscode/settings.json'. Options: jdm, vscode fetch Fetch dependencies

## Example jdm.json

```json
{
  "group": "",
  "artifact": "",
  "version": "",
  "description": "",
  "packages": [
    {
      "group": "",
      "artifact": "",
      "version": ""
    }
  ]
}
```
