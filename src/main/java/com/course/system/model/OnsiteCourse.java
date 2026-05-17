package com.course.system.model;

public class OnsiteCourse extends Course {
    private String roomNumber;
    private String campusLocation;

    public OnsiteCourse() { super(); }

    public OnsiteCourse(String id, String title, String instructorId, String instructorName,
                        int credits, String courseCode, boolean open, int maxCapacity,
                        String roomNumber, String campusLocation) {
        super(id, title, instructorId, instructorName, credits, courseCode, open, maxCapacity);
        this.roomNumber = roomNumber;
        this.campusLocation = campusLocation;
    }

    public String getRoomNumber() { return roomNumber; }
    public void setRoomNumber(String roomNumber) { this.roomNumber = roomNumber; }

    public String getCampusLocation() { return campusLocation; }
    public void setCampusLocation(String campusLocation) { this.campusLocation = campusLocation; }

    @Override
    public String getCourseType() { return CourseType.ONSITE.name(); }

    @Override
    public String toString() {
        return super.toString() + "|" + roomNumber + "|" + campusLocation;
    }
}
