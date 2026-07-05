package repository

import enums.TaskStatus
import io.circe.*
import io.circe.generic.auto.*
import io.circe.parser.*
import io.circe.syntax.*
import model.Task
import repository.JsonFileTaskRepository.jsonFilePath

import java.nio.file.{Files, Path, Paths}

class JsonFileTaskRepository extends TaskRepository:
  private def ensureFileCreated(): Unit = {
    if Files.exists(jsonFilePath) then ()
    else {
      Files.createFile(jsonFilePath)
      Files.writeString(jsonFilePath, "[]")
    }
  }

  override def findAll(statusFilter: Option[TaskStatus] = None): Array[Task] = {
    ensureFileCreated()

    val fileContent = Files.readString(jsonFilePath)
    decode[List[Task]](fileContent) match {
      case Right(value) => value.toArray
      case Left(error) => throw error
    }
  }

  override def findByID(id: Int): Option[Task] =
    findAll().find(_.id == id)

  private def save(tasks: Array[Task]): Unit =
    Files.writeString(jsonFilePath, tasks.asJson.noSpaces)

  override def add(task: Task): Unit =
    save(findAll() :+ task)

  override def update(task: Task): Unit =
    val tasks = findAll()
    val idx = tasks.indexWhere(_.id == task.id)
    if idx == -1 then throw new IndexOutOfBoundsException
    else tasks(idx) = task
    save(tasks)

  override def delete(task: Task): Unit =
    val tasks = findAll()
    val idx = tasks.indexWhere(_.id == task.id)
    if idx == -1 then throw new IndexOutOfBoundsException
    else save(tasks.filterNot(_.id == task.id))


object JsonFileTaskRepository:
  private val jsonFilePath: Path = Paths.get(System.getProperty("user.home"), ".tasks.json").toAbsolutePath