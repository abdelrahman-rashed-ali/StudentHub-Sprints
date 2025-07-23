package org.sprints.domain.usecases

import org.sprints.domain.models.Student
import org.sprints.domain.repository.StudentsRepository

class GetAllStudentsUseCase(private val repository: StudentsRepository) {
    fun getAllStudents(): List<Student> {
        return repository.getAllStudents()
    }
}