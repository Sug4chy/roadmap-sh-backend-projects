package command.handler

import command.exception.TaskNotFoundException
import command.{CommandHandler, MarkTaskAsDoneCommand}
import enums.TaskStatus
import repository.TaskRepository

class MarkTaskAsDoneCommandHandler(repository: TaskRepository) extends CommandHandler[MarkTaskAsDoneCommand]:
  override def handle(command: MarkTaskAsDoneCommand): Unit =
    val taskToMark = repository.findByID(command.id)
    if taskToMark.isEmpty then throw new TaskNotFoundException
    else repository.update(taskToMark.get.copy(status = TaskStatus.Done))