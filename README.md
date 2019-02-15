# treasure-hunt

## Compatibilities
This program is available to Java 11 or above

## Compile process
```bash
git clone https://github.com/aaudelin/treasure-hunt.git`
cd treasure-hunt
mvn install
```

## Execution process
Go to the generated JAR file :
`java -jar treasure-hunt-{version}.jar {absolute_file_path}`

The output file is available in `absolute_file_path` with `-output` suffix 

## Example
Instruction file :
```bash
C - 3 - 4
M - 1 - 0
M - 2 - 1
T - 0 - 3 - 2
T - 1 - 3 - 3
A - Lara - 1 - 1 - S - AADADAGGA
```

Standard output result :
```bash
java -jar treasure-hunt-0.0.1-SNAPSHOT.jar /tmp/instructions-file
---- RESULT : Output Madre de Dios final position ---
C - 3 - 4
M - 1 - 0
M - 2 - 1
T - 1 - 3 - 2
A - Lara - 0 - 3 - S - 3
```