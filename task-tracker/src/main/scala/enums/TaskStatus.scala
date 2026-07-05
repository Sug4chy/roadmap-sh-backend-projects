package enums

enum TaskStatus(val s: String) {
  case ToDo extends TaskStatus("to-do")
  case InProgress extends TaskStatus("in-progress")
  case Done extends TaskStatus("done")
}

object TaskStatus {
  def fromString(s: String): TaskStatus =
    TaskStatus.values.find(_.s == s).getOrElse(throw new IllegalArgumentException)
}