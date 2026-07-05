package command.handler

import command.{CommandHandler, UpdateTaskCommand}
import repository.TaskRepository

import java.time.Instant

class UpdateTaskCommandHandler(repository: TaskRepository) extends CommandHandler[UpdateTaskCommand]:
  override def handle(command: UpdateTaskCommand): Unit =
    val now = Instant.now
    val taskToUpdate = repository.findByID(command.id)
    if taskToUpdate.isEmpty then throw new IndexOutOfBoundsException
    else repository.update(taskToUpdate.get.copy(description = command.nextDescription, updatedAt = now))
    println(s"Task updated successfully (ID: ${command.id})")