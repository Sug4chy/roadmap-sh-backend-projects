package command.handler

import command.exception.TaskNotFoundException
import command.{CommandHandler, MarkTaskAsInProgressCommand}
import enums.TaskStatus
import repository.TaskRepository

class MarkTaskAsInProgressCommandHandler(repository: TaskRepository) extends CommandHandler[MarkTaskAsInProgressCommand]:
  override def handle(command: MarkTaskAsInProgressCommand): Unit =
    val taskToMark = repository.findByID(command.id)
    if taskToMark.isEmpty then throw new TaskNotFoundException
    else repository.update(taskToMark.get.copy(status = TaskStatus.InProgress))