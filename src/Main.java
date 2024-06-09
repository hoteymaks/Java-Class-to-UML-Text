import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        ArrayList<String> umlLines = new ArrayList<>();
        ArrayList<String> errorLines = new ArrayList<>();

        while (true) {
            umlLines.clear();
            errorLines.clear();
            System.out.println("Enter class field (variable) in format:\n  modifier type name  [e.g. private String name]");
            System.out.println("Enter class method (function) in format:\n  modifier type name(params)  [e.g. public void setName(String name)]");
            System.out.println("If you're done with entered data, just type \"done\"");

            while (true) {
                String line = s.nextLine();
                if (line.equalsIgnoreCase("done")) break;
                umlLines.add(line);
            }

            if (umlLines.isEmpty()) break;

            for (String line : umlLines) {
                if (line.contains("this.") ||
                    line.contains("return ") ||
                    line.contains("}") ||
                    line.isEmpty()) continue;
                String result;
                try {
                    line = line.replace(";", "").replace("{","").trim();
                    if (line.contains("//")) line = line.split("//")[0];
                    if (line.contains("(")) {
                        boolean isConstructor = false;
                        boolean isSetter = false;
                        if (line.split("\\(")[0].split(" ").length == 2) isConstructor = true;
                        if (line.contains("void")) isSetter = true;
                        String params = getFormattedParams(line);
                        String methodName;
                        try {
                            methodName = line.split("\\(")[0].split(" ")[2];
                        } catch (Exception e) {
                            methodName = line.split("\\(")[0].split(" ")[1];
                        }
                        String modifier = "+ ";
                        if (line.contains("private")) modifier = "- ";
                        else if (line.contains("protected")) modifier = "# ";
                        if (isConstructor || isSetter) {
                            result = modifier + methodName + "(" + params + ")";
                        } else {
                            String returnType = line.split("\\(")[0].split(" ")[1];
                            result = modifier + methodName + "(" + params + "): " + returnType;
                        }
                    } else {
                        String fieldName = line.split(" ")[2];
                        String fieldType = line.split(" ")[1];
                        String modifier = "+ ";
                        if (line.contains("private")) modifier = "- ";
                        else if (line.contains("protected")) modifier = "# ";
                        result = modifier + fieldName + ": " + fieldType;
                    }
                    System.out.println(result);
                } catch (Exception e) {
                    errorLines.add(line);
                }
            }

            if (!errorLines.isEmpty()) {
                System.out.print("\n\nCouldn't process " + errorLines.size() + " lines.\nDo you want to see them? (yes/no):");
                String answer = s.next();
                if (answer.equals("yes")) for (String errorLine : errorLines) System.out.println(errorLine);
            }

            System.out.println("\n\nIf you want to exit, just type \"done\" again");
        }
    }

    private static String getFormattedParams(String line) {
        Pattern pattern = Pattern.compile("\\(([^)]+)\\)");
        Matcher matcher = pattern.matcher(line);
        if (matcher.find()) {
            String paramsString = matcher.group(1);
            String[] paramsArray = paramsString.split(",");
            StringBuilder formattedParams = new StringBuilder();
            for (String param : paramsArray) {
                String[] parts = param.trim().split(" ");
                String type = parts[0];
                String name = parts[1];
                formattedParams.append(name).append(": ").append(type).append(", ");
            }
            if (formattedParams.length() > 0) formattedParams.setLength(formattedParams.length() - 2);
            return formattedParams.toString();
        }
        return "";
    }
}
