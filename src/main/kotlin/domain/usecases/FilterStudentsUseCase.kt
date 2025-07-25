package domain.usecases

import domain.models.Student
import org.sprints.domain.repository.StudentsRepository

class FilterStudentsUseCase(val repository: StudentsRepository) {
    fun filterByName(name: String?): List<Student> {
        return repository.filterStudent(name = name, status = null, grade = null)
    }

    fun filterByStatus(status: String?): List<Student> {
        return repository.filterStudent(name = null, status = status, grade = null)
    }

    fun filterByGrade(grade: String?): List<Student> {
        return repository.filterStudent(name = null, status = null, grade = grade)
    }

    fun filterByGPA(minGPA: Double, maxGPA: Double): List<Student> {
        return repository.filterStudentsByGPA(minGPA, maxGPA)
    }

    fun getAverageGPAOfPassedStudents(): Double? {
        val passingStudents = repository.getAllStudents().filter {
            it.gpa != null && it.gpa >= 2.0
        }
        return if (passingStudents.isEmpty()) {
            null
        } else {
            passingStudents.mapNotNull { it.gpa }.average()
        }
    }
}