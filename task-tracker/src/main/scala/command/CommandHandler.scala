package command

import command.handler.*
import repository.{InMemoryTaskRepository, TaskRepository}

trait CommandHandler[C <: Command](repository: TaskRepository):
  def handle(command: C): Unit

object CommandHandler:
  private val repository: TaskRepository = InMemoryTaskRepository()

  given CommandHandler[AddTaskCommand] = new AddTaskCommandHandler(repository)

  def `for`[C <: Command](command: C)(using handler: CommandHandler[C]): CommandHandler[C] =
    handler