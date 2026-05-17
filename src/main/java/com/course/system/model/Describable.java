package com.course.system.model;

/**
 * IMPROVEMENT (OOP): Interface-based polymorphism.
 * Any class that can describe its role implements this interface.
 * Separates interface from the inheritance hierarchy.
 */
public interface Describable {
    String getRoleDescription();
}
