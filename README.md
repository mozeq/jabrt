abrt-java
=================

ABRT API Java bindings

Compile the jar file with maven: `mvn package`

Unzip the jar archive created in the `target/` folder to /usr/share/java

Use in your code:

```java
import com.redhat.abrt.ProblemDataAbrt;
import com.redhat.abrt.ProblemDataServer;

public MyClass {

    public static void main(String[] args) {
        ProblemData pd = new ProblemDataAbrt();
        pd.add("BACKTRACE", "backtrace content");
        pd.add("TYPE", "java");
        pd.add("ANALYZER", "java");
        pd.add("PID", "12345");
        pd.add("EXECUTABLE", "/bin/eclipse");
        pd.add("REASON", "tesing java problem data");
        String filename = "/etc/hosts";
        try {
            pd.addFile(filename);
        } catch (FileNotFoundException e) {
            System.err.println("Can't add file: " + filename);
        }

        ProblemDataServer ps = new ProblemDataServer();
        try {
            send(pd);
        } catch (IOException e) {
            System.err.println("Can't send data to ABRT: " + e.getMessage());
        }
    }
}
```