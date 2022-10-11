// BoolLang to StackM0 Compiler
//
// Usage: linux> scala BLComp <source file>
//
//
////////////////////////////////////////////////////////////////////////////////
// Name: Camilo Schaser-Hughes
// Class: CS558, Prog Languages
// Date: Oct 9, 2022
////////////////////////////////////////////////////////////////////////////////
import BoolLang._
import StackM0._

object BLComp {
  def compile(e: Expr): Program = e match {
    case True     => T::Nil
    case False    => F::Nil
    case Not(e)   => compile(e) ::: (NOT::Nil)
    case And(l,r) => compile(l) ::: compile(r) ::: (AND::Nil)
    case Or(l,r)  => compile(l) ::: compile(r) ::: (OR::Nil)
    case Xor(l,r) => {
      val left = compile(l)
      val right =  compile(r)
      left ::: right ::: (NOT::Nil) ::: (AND::Nil) ::: left ::: (NOT::Nil) ::: right ::: (AND::Nil) ::: (OR::Nil)
    }
    // ... need code ... put the code ^^^ there, i guess uses
    // so I don't know if I need to put the XOR in the StackM0.scala file
    // now too. NO, I SHOULD NOT HAVE DONE THAT...
  }

  def apply(s:String, debug:Int = 0): Boolean = {
    if (debug > 0) println("Input:  " + s)
    // here it parses the string input in form of "(and (or (or f f) t) t)"
    // that equals true by the way after interpretting, returns as a bunch
    // of compiles stuff like above. is written in BoolLand.scala
    val e: Expr = BLParse(s)
    if (debug > 0) println("AST:    " + e)
    // compile then returns the function from above.
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
