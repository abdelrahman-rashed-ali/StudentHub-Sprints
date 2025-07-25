package org.sprints.data.repository

import data.storage.StudentStorage
import domain.models.Student
import org.sprints.domain.repository.StudentsRepository

class StudentsRepository : StudentsRepository {
    companion object {
        private val students = StudentStorage.loadStudents()
//        private val students = mutableListOf<Student>(
//            Student(1, "Michael Jackson", "Senior", "Active", 3.25, "Excellent in music."),
//            Student(2, "Emma Watson", "Junior", "Active", 3.80, "Participates in drama club."),
//            Student(3, "Liam Smith", "Sophomore", "Inactive", 2.50, "On academic probation."),
//            Student(4, "Olivia Johnson", "Freshman", "Active", 3.60, "Great at math."),
//            Student(5, "Noah Brown", "Senior", "Graduated", 3.90, "Top of the class."),
//            Student(6, "Ava Davis", "Junior", "Active", 3.10, "Works part-time."),
//            Student(7, "Elijah Miller", "Sophomore", "Active", 2.80, "Needs improvement in science."),
//            Student(8, "Sophia Wilson", "Freshman", "Active", 3.40, "Excellent attendance."),
//            Student(9, "James Moore", "Senior", "Inactive", 2.20, "Absent often."),
//            Student(10, "Isabella Taylor", "Junior", "Active", 3.75, "Volunteers regularly."),
//            Student(11, "William Anderson", "Sophomore", "Active", 3.00, "Team player."),
//            Student(12, "Mia Thomas", "Freshman", "Active", 3.55, "Excels in art."),
//            Student(13, "Benjamin Jackson", "Senior", "Active", 3.65, "Great leadership skills."),
//            Student(14, "Charlotte White", "Junior", "Active", 3.20, "Quiet but consistent."),
//            Student(15, "Lucas Harris", "Sophomore", "Active", 2.95, "Struggling with assignments.")
//        )
    }

    override fun addStudent(student: Student): Boolean {
        if(students.add(student)){
            StudentStorage.saveStudents(students)
            return true
        }else{
            return false
        }
    }

    override fun removeStudent(studentID: Int?): Boolean {
        if(students.removeIf { student ->
                student.id == studentID
            }){
            StudentStorage.saveStudents(students)
            return true
        }else{
            return false
        }
    }

    override fun findStudentById(id: Int): Student? {
        return students.find { student -> student.id == id }
    }
    // to apply multiple params filtering ...
    override fun filterStudent(name: String?, status: String?, grade: String?): List<Student> {
        return students.filter { student ->
            (name?.let { student.name.contains(it, ignoreCase = true) } ?: true) &&
                    (status?.let { student.status.equals(it, ignoreCase = true) } ?: true) &&
                    (grade?.let { student.grade.equals(it, ignoreCase = true) } ?: true)
        }
    }

    override fun filterStudentsByGPA(minGPA: Double, maxGPA: Double): List<Student> {
        return students.filter { student -> (student.gpa!! >= minGPA && student.gpa <= maxGPA) }
    }

    override fun updateStudent(student: Student): Boolean {
        val index = students.indexOfFirst { it -> it.id == student.id }
        if (index <= -1) return false
        students[index] = student
        StudentStorage.saveStudents(students)
        return true
    }

    override fun getAllStudents(): List<Student> {
        return students
    }
}