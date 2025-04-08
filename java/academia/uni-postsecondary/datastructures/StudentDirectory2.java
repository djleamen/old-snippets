// Description: A simple student directory that uses a HashMap to store students.

import java.util.HashMap;

public class StudentDirectory2 {
    
    HashMap<Integer, Student> students = new HashMap<Integer, Student>();
    
    public void addStudent(Student s) {
        students.put(s.hashCode(), s);
    }

    public String getEmail(Student s) {
        Student retrievedStudent = students.get(s.hashCode());
        return (retrievedStudent != null) ? retrievedStudent.email : null;
    }

    public int studentCount() {
        return students.size();
    }

    private static class Student {
        String name;
        String email;
        int ID;

        Student(String name, String email, int ID) {
            this.name = name;
            this.email = email;
            this.ID = ID;
        }

        @Override
        public int hashCode() {
            return ID * name.length();
        }

        @Override
        public boolean equals(Object obj) {
            if (obj.getClass() == Student.class) {
                return this.ID == ((Student)obj).ID;
            } else {
                return false;
            }
        }
    }

    public static void main(String[] args) {
        Student a = new Student("Eric", "eric.rapos@ontariotechu.ca", 12345678);
        Student b = new Student("Helen", "helen.smith@gmail.com", 87654321);

        StudentDirectory2 s = new StudentDirectory2();
        s.addStudent(a);
        s.addStudent(b);

        System.out.println(s.studentCount());
        System.out.println(s.getEmail(a));

    }
}