import command.{Command, CommandHandler}

import scala.io.AnsiColor.*

@main
def main(args: String*): Unit =
  try {
    val parsedCommand = Command.parseFromArgs(args.toArray)
    val handler = CommandHandler.`for`(parsedCommand)
    handler.handle(parsedCommand)
  } catch {
    case ex: IllegalArgumentException =>
      println(s"${RED}Invalid input. Please check your arguments and try again.$RESET")
    case ex: IndexOutOfBoundsException =>
      println(s"${RED}Task with specified ID wasn't found. Maybe it was deleted earlier.$RESET")
  }