package command

import line.Type.SBF
import line.{Body, LineNumber, Lines, Text}

case class Append(n: LineNumber, b: Body) extends ExecutableCommand {
  private val append: SBF = b => Seq(b, this.b)

  override def execute(ls: Lines): Lines = ls.sMap(l => l.sMapIf(n, append))

  override def undo(ls: Lines): Option[UndoCommand] = Some(DeletionUndo((n + 1).asRange))
}

object Append extends ParsableCommand {
  override def parse(s: String, last: Int): Option[Command] = s.split("/").toSeq.map(_.trim) match {
    case Seq(_n, _name, _text) if Seq("append", "a").contains(_name) => for {
      __n <- LineNumber.parse(_n, last)
      __text <- Text.parse(_text)
    } yield Append(__n, __text.asBody)
    case _ => None
  }
}
