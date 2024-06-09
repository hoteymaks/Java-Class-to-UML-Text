# Java-Class-to-UML-Text
Generate UML class text seamlessly fast


## Description
Transforming your Java classes to a UML class diagram can be frustrating.<br>
You can use my utility to transform your Java code to UML class fields, constructors and methods.

For example,
```java
private String sampleVariable;

public SampleClass() {
}

public SampleClass(String sampleVariable) {
    this.sampleVariable = sampleVariable;
}

public void setSampleVariable(String sampleVariable) {
    this.sampleVariable = sampleVariable;
}

public String getSampleVariable() {
    return sampleVariable;
}
```
will turn to
```lua
- sampleVariable: String
+ SampleClass()
+ SampleClass(sampleVariable: String)
+ setSampleVariable(sampleVariable: String)
+ getSampleVariable(): String
```

## How to use
1. Clone the project to your IDE (e.g. for IntellJ IDEA: "File" - "New" - "Project from Version Control" - "Repository URL" - paste `https://github.com/hoteymaks/Java-Class-to-UML-Text.git` in URL field)
2. Copy everything that is inside your Java class
3. Run my code
4. Paste your code
5. Type "done" to let the utility do the job
6. Copy the output
7. Paste it in your UML class diagram


## How it works
- Utility ignores all the odd lines that contain "this.", "return", "}", empty lines.
- It handles three field modifiers of Java: private (-), public (+), protected (#).
- Lines that couldn't be processed are stored in a separate array. After UML text is printed, utility offers you to print them.

Lines are processed and printed in the input order.


## Compliance with standards
Utility output completely meets the [UML standards](https://darvishdarab.github.io/cs421_f20/docs/readings/uml/) if input code syntax is written in accordance with the [Oracle's Java standards](https://docs.oracle.com/javase/specs/jls/se17/html/index.html).
