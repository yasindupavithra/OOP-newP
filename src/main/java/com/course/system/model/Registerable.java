package com.course.system.model;

/**
 * IMPROVEMENT (OOP): Interface-based polymorphism.
 * Any course type that supports registration checking implements this.
 */
public interface Registerable {
    boolean isOpenForRegistration();
    int getMaxCapacity();
    String getCourseType();
}
