package org.sprints.domain.usecases

import domain.models.Student
import org.sprints.domain.repository.StudentsRepository

class UpdateStudentInfoUseCase(private val repository: StudentsRepository) { // Make repository private
    operator fun invoke(
        id: Int,
        newName: String,
        newGrade: String,
        newStatus: String,
        newGpa: Double?,
        newNotes: String?
    ): Result<Student> {
        if (newName.isBlank() || newGrade.isBlank() || newStatus.isBlank())
        // Provide a more specific error message
            return Result.failure(IllegalArgumentException("Name, grade, and status cannot be blank."))

        val student = repository.findStudentById(id)
            ?: return Result.failure(NoSuchElementException("Student with id $id not found"))

        val updatedStudent = student.copy(
            name = newName,
            grade = newGrade,
            status = newStatus,
            gpa = newGpa,
            notes = newNotes
        )

        // BUG FIX: Pass the new `updatedStudent` to the repository, not the old one.
        val success = repository.updateStudent(updatedStudent)

        return if (success)
            Result.success(updatedStudent)
        else
            Result.failure(Exception("Failed to update student in the repository."))
    }
}