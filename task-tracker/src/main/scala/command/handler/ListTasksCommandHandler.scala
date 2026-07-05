package command.handler

import command.{CommandHandler, ListTasksCommand}
import repository.TaskRepository

class ListTasksCommandHandler(repository: TaskRepository) extends CommandHandler[ListTasksCommand]:
  override def handle(command: ListTasksCommand): Unit =
    repository
      .findAll(command.statusFilter)
      .map(_.toString)
      .foreach(println)