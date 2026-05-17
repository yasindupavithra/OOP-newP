package com.course.system.model;

/**
 * NEW COMPONENT: Instructor is now a proper User subclass.
 * Previously, instructor was just a String field on Course.
 * This deepens the inheritance hierarchy:
 *   User (abstract)
 *     ├── Student
 *     ├── Admin
 *     └── Instructor  ← NEW
 */
public class Instructor extends User {
    private String department;

    public Instructor() {
        super();
        setUserType(UserType.INSTRUCTOR.name());
    }

    public Instructor(String id, String username, String password, String email, String department) {
        super(id, username, password, email, UserType.INSTRUCTOR.name());
        this.department = department;
    }

    public String getDepartment() { return department; }
    public void setDepartment(String department) { this.department = department; }

    @Override
    public String getRoleDescription() {
        return "Instructor: Assigned to courses and can submit grades.";
    }

    @Override
    public String toString() {
        return super.toString() + "|" + (department != null ? department : "General");
    }
}
