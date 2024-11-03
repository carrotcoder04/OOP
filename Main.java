import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Main {
     public static void main(String[] args)  {
          ClassManager classManager = new ClassManager();
          Scanner scanner = new Scanner(System.in);
          int n = scanner.nextInt();
          int m = scanner.nextInt();
          while(n-- > 0) {
               BaseClass firstClass = classManager.getClass(scanner.next());
               String relationship = scanner.next();
               if(relationship.equals("is-a")) {
                    BaseClass secondClass = classManager.getClass(scanner.next());
                    firstClass.setParent(secondClass);
               }
               else if(relationship.equals("has-a")) {
                    BaseClass property = classManager.getClass(scanner.next());
                    firstClass.addProperty(property);
               }
          }
          for(int i=1;i<=m;i++) {
               BaseClass firstClass = classManager.getClass(scanner.next());
               String relationship = scanner.next();
               if(relationship.equals("is-a")) {
                    BaseClass secondClass = classManager.getClass(scanner.next());
                    if(firstClass.isA(secondClass)) {
                         System.out.println("Query " + i + ": true");
                    }
                    else {
                         System.out.println("Query " + i + ": false");

                    }
               }
               else if(relationship.equals("has-a")) {
                    BaseClass property = classManager.getClass(scanner.next());
                    if(firstClass.hasA(property)) {
                         System.out.println("Query " + i + ": true");
                    }
                    else {
                         System.out.println("Query " + i + ": false");
                    }
               }
          }
     }
}
class ClassManager {
     private final HashMap<String,BaseClass> classMap;
     public ClassManager() {
          classMap = new HashMap<>();
     }
     public BaseClass getClass(String className) {
          if(!classMap.containsKey(className)) {
               BaseClass newClass = new BaseClass(className);
               classMap.put(className,newClass);
               return newClass;
          }
          return classMap.get(className);
     }
}
class BaseClass {
     private final String name;
     private BaseClass parent;
     private final ArrayList<BaseClass> properties;
     public BaseClass(String name) {
          this.name = name;
          properties = new ArrayList<>();
     }
     public boolean isA(BaseClass baseClass) {
          if(this == baseClass) {
               return true;
          }
          if (parent != null) {
               return parent.isA(baseClass);
          }
          return false;
     }
     public void setParent(BaseClass baseClass) {
          parent = baseClass;
     }
     public boolean hasA(BaseClass property) {
          for(BaseClass p : properties) {
               if(p.isA(property)) {
                    return true;
               }
          }
          if(parent != null) {
               return parent.hasA(property);
          }
          else {
               return false;
          }
     }
     public void addProperty(BaseClass property) {
          this.properties.add(property);
     }
}