package org.sprints.domain.usecases

import domain.models.Student
import org.sprints.domain.repository.StudentsRepository

class AddNewStudentUseCase(val repository: StudentsRepository) {

     fun isStudentAdded(student: Student): Boolean {
        return repository.addStudent(student)
    }
}