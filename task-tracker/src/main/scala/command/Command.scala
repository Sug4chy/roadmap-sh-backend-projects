package command

import enums.TaskStatus

sealed trait Command

case class AddTaskCommand(description: String) extends Command:
  require(description.nonEmpty)

case class UpdateTaskCommand(id: Int, nextDescription: String) extends Command:
  require(id > 0)
  require(nextDescription.nonEmpty)

case class DeleteTaskCommand(id: Int) extends Command:
  require(id > 0)

case class MarkTaskAsInProgressCommand(id: Int) extends Command:
  require(id > 0)

case class MarkTaskAsDoneCommand(id: Int) extends Command:
  require(id > 0)

case class ListTasksCommand(statusFilter: Option[TaskStatus] = None) extends Command

object Command:
  def parseFromArgs(args: Array[String]): Command =
    require(args.nonEmpty)

    args(0) match {
      case "add" => AddTaskCommand(args(1))
      case "update" => UpdateTaskCommand(args(1).toInt, args(2))
      case "delete" => DeleteTaskCommand(args(1).toInt)
      case "mark-in-progress" => MarkTaskAsInProgressCommand(args(1).toInt)
      case "mark-done" => MarkTaskAsDoneCommand(args(1).toInt)
      case "list" => ListTasksCommand(
        if args.length > 1 then Some(TaskStatus.fromString(args(1)))
        else None
      )
    }