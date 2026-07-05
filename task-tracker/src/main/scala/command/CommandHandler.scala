package command

import command.handler.*
import repository.{InMemoryTaskRepository, TaskRepository}

trait CommandHandler[C <: Command]:
  def handle(command: C): Unit

object CommandHandler:
  private val repository: TaskRepository = InMemoryTaskRepository()

  def handle(command: Command): Unit =
    command match {
      case c: AddTaskCommand => new AddTaskCommandHandler(repository).handle(c)
      case c: UpdateTaskCommand => new UpdateTaskCommandHandler(repository).handle(c)
      case c: DeleteTaskCommand => new DeleteTaskCommandHandler(repository).handle(c)
      case c: MarkTaskAsInProgressCommand => new MarkTaskAsInProgressCommandHandler(repository).handle(c)
      case c: MarkTaskAsDoneCommand => new MarkTaskAsDoneCommandHandler(repository).handle(c)
      case c: ListTasksCommand => new ListTasksCommandHandler(repository).handle(c)
    }