package command.handler

import command.{AddTaskCommand, CommandHandler}
import enums.TaskStatus
import model.Task
import repository.TaskRepository

import java.time.Instant

class AddTaskCommandHandler(repository: TaskRepository) extends CommandHandler[AddTaskCommand](repository):
  private def nextID(): Int =
    repository.findAll().length + 1

  override def handle(command: AddTaskCommand): Unit =
    val now = Instant.now()
    val taskToAdd = Task(nextID(), command.description, TaskStatus.ToDo, now, now)
    repository.add(taskToAdd)
    println(s"Task added successfully (ID: ${taskToAdd.id})")