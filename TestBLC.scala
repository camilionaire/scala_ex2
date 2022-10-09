//-------------------------------------------------------------------------
// Name: Camilo Schaser-Hughes
// Class: CS558 Programming Languages, Prof: Jinke Li
// Date: October 8th, 2022
// Description: Test program that is based off of interpreter test
// Tests the methods of the BLComp class.
//
// Supporting code for CS558 Programming Languages. 
// Dept of Computer Science, Portland State University
// J. Li (8/2022) 
//-------------------------------------------------------------------------

// Testing BoolLang compiler
//
import org.scalatest.FunSuite
import BLComp._

// basically just a copy of TestBLI.scala file.
class TestBLC extends FunSuite {

  test("correctly compile simple expressions") {
    assertResult(false)(BLComp("(not (and t (not f)))"))
    assertResult(true)(BLComp("(and (or (or f f) t) t)"))
    assertResult(true)(BLComp("(or (or (and f f) t) f)"))
    assertResult(true)(BLComp("(and (xor t f) (xor f t))"))
    assertResult(true)(BLComp("(xor (and t f) (or f t))"))
  }

}

