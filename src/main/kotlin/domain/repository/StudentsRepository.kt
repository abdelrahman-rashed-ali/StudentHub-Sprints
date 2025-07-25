package org.sprints.domain.repository

import domain.models.Student

interface StudentsRepository {
    fun addStudent(student: Student) : Boolean
    fun removeStudent(studentID: Int?) : Boolean
    fun findStudentById(id: Int) : Student?

    fun filterStudent(name: String? , status: String? , grade: String?) : List<Student>
    fun filterStudentsByGPA(minGPA: Double,maxGPA: Double) : List<Student>

    fun updateStudent(student: Student): Boolean
    fun getAllStudents(): List<Student>
}