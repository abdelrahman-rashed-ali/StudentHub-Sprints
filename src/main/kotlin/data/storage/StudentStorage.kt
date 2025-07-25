package data.storage

import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import domain.models.Student
import java.io.File

object StudentStorage {
    private val file = File("students.json")

    fun loadStudents(): MutableList<Student> {
        val jsonString = file.readText()
        return if(jsonString.isBlank()) mutableListOf()
        else Json.decodeFromString(jsonString)
    }

    fun saveStudents(students: List<Student>) {
        val jsonString = Json.encodeToString(students)
        file.writeText(jsonString)
    }
}