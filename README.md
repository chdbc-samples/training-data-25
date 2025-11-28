```markdown
# training-data

This workspace contains several small examples and a Java Maven project `vetclinic`.

Requirements
- Java 21 (LTS)
- Maven 3.6+

Quick start

1. Install or point your JAVA_HOME to a JDK 21 installation.
2. Verify Java and Maven:

```bash
mvn -v
java -version
```

3. Build and run tests in the `vetclinic` module:

```bash
cd vetclinic
mvn clean test
```

If you don't have JDK 21 installed, install it from a trusted distribution (Adoptium, Oracle, or your package manager).

Notes
- The `vetclinic/pom.xml` has been updated to compile with Java 21 using `maven-compiler-plugin` (release 21).
```
# training-data