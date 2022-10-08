// BoolLang to StackM0 Compiler
//
// Usage: linux> scala BLComp <source file>
//
//
import BoolLang._
import StackM0._

object BLComp {
  def compile(e: Expr): Program = e match {
    case True     => T::Nil
    case False    => F::Nil
    case Not(e)   => compile(e) ::: (NOT::Nil)
    case And(l,r) => compile(l) ::: compile(r) ::: (AND::Nil)
    case Or(l,r)  => compile(l) ::: compile(r) ::: (OR::Nil)
    case Xor(l,r) => compile(l) ::: compile(r) ::: (XOR::Nil)
    // ... need code ... put the code ^^^ there, i guess uses
    // so I don't know if I need to put the XOR in the StackM0.scala file
    // now too.
  }

  def apply(s:String, debug:Int = 0): Boolean = {
    if (debug > 0) println("Input:  " + s)
    val e: Expr = BLParse(s)
    if (debug > 0) println("AST:    " + e)
    val p: Program = compile(e)
    if (debug > 0) println("Target: " + p)
    exec(p,debug)
  }

  // Test driver
  import scala.io.Source
  def main(argv: Array[String]) = {
    try {
      val s = Source.fromFile(argv(0)).getLines.mkString("\n")
      val d = if (argv.length > 1) argv(1).toInt else 0
      val v = apply(s,d)
      println(v)
    } catch {
      case ex: ParseException => println("Parser Error: " + ex.string)
      case ex: ExecException  => println("Exec Error: " + ex.string)
    }
  }
}
//
