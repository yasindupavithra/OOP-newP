package com.course.system.model;

public class OnlineCourse extends Course {
    private String platform;
    private String meetingLink;

    public OnlineCourse() { super(); }

    public OnlineCourse(String id, String title, String instructor, int credits, String courseCode, boolean open, String platform, String meetingLink) {
        super(id, title, instructor, credits, courseCode, open);
        this.platform = platform;
        this.meetingLink = meetingLink;
    }

    public String getPlatform() { return platform; }
    public void setPlatform(String platform) { this.platform = platform; }

    public String getMeetingLink() { return meetingLink; }
    public void setMeetingLink(String meetingLink) { this.meetingLink = meetingLink; }

    @Override
    public String getCourseType() { return "ONLINE"; }

    @Override
    public String toString() {
        return super.toString() + "|" + platform + "|" + meetingLink;
    }
}
