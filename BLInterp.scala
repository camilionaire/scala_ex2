// BoolLang Interpreter
//
// Usage: linux> scala BLInterp <source file>
//
//
////////////////////////////////////////////////////////////////////////////////
// Name: Camilo Schaser-Hughes
// Class: CS558, Prog Languages
// Date: Oct 9, 2022
////////////////////////////////////////////////////////////////////////////////
import BoolLang._

object BLInterp {
  def interp(e: Expr): Boolean = e match {
    case True => true
    case False => false
    case Not(e) => ! interp(e)
    case And(l,r) => interp(l) && interp(r)
    case Or(l,r)  => interp(l) || interp(r)
    // added in the case xor, either one and not the other or
    // the other and not the one.
    case Xor(l,r) => (interp(l) && ! interp(r)) || 
      (! interp(l) && interp(r))  // ... need code ...
  }

  def apply(s:String, debug:Int = 0): Boolean = {
    if (debug > 0) println("Input:  " + s)
    val e = BLParse(s)
    if (debug > 0) println("AST:    " + e)
    interp(e)
  }

  // Test driver
  import scala.io.Source
  def main(argv: Array[String]) = {
    try {
      val s = Source.fromFile(argv(0)).getLines.mkString("\n")
      val d = if (argv.length > 1) argv(1).toInt else 0
      val r = apply(s,d)
      println(r)
    } catch {
      case ex: ParseException => println("Parser Error: " + ex.string)
    }
  }
}
//
