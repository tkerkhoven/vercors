package vct.col.ast

import scala.collection.JavaConverters._
import vct.col.util.VisitorHelper

case class ParallelRegion(val contract:Contract, val blocks:List[ParallelBlock]) extends ASTNode with VisitorHelper {
  require(blocks != null, "The list of blocks is null.")
  
  /** Constructs a new parallel region from an array of blocks. */
  def this(contract:Contract, blocks:Array[ParallelBlock]) = this(contract, blocks.toList)
  
  /** Constructs a new parallel region from Java constructs (for Java interoperability). */
  def this(contract:Contract, blocks:java.util.List[ParallelBlock]) = this(contract, blocks.asScala.toList)
  
  /** Provides a Java wrapper (as `java.util.List`) over the list of blocks. */
  def blocksJava = blocks.asJava
  
  override def accept_simple[T,A](m:ASTMapping1[T,A], arg:A) = m.map(this, arg)
  override def accept_simple[T](v:ASTVisitor[T]) = handle_standard(() => v.visit(this))
  override def accept_simple[T](m:ASTMapping[T]) = handle_standard(() => m.map(this))
}
