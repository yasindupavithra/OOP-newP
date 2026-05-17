package com.course.system.model;

public class Student extends User {
    private String degreeProgram;

    public Student() {
        super();
        setUserType(UserType.STUDENT.name());
    }

    public Student(String id, String username, String password, String email, String degreeProgram) {
        super(id, username, password, email, UserType.STUDENT.name());
        this.degreeProgram = degreeProgram;
    }

    public String getDegreeProgram() { return degreeProgram; }
    public void setDegreeProgram(String degreeProgram) { this.degreeProgram = degreeProgram; }

    @Override
    public String getRoleDescription() {
        return "Student: Can register for courses and view grades.";
    }

    @Override
    public String toString() {
        return super.toString() + "|" + (degreeProgram != null ? degreeProgram : "General");
    }
}
