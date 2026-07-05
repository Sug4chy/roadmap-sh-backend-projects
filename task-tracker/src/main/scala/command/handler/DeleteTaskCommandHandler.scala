package command.handler

import command.exception.TaskNotFoundException
import command.{CommandHandler, DeleteTaskCommand}
import repository.TaskRepository

class DeleteTaskCommandHandler(repository: TaskRepository) extends CommandHandler[DeleteTaskCommand]:
  override def handle(command: DeleteTaskCommand): Unit =
    val taskToDelete = repository.findByID(command.id)
    if taskToDelete.isEmpty then throw new TaskNotFoundException
    else repository.delete(taskToDelete.get)
  