package command.exception

class TaskNotFoundException(message: String = "Task with specified ID wasn't found") extends Exception(message)