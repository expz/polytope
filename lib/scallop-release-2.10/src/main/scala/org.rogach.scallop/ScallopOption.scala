package org.rogach.scallop

/** A class to hold a reference to not-yet-computed option values.
  *
  * Basically, this is a lazy option - it batches up all operations,
  * and evaluates the value only as the last resort.
  * @param nm Name for the option
  * @param fn source of the actual value
  * @param supplied function, that is capable of testing whether the value was explicitly
  *                 found in argument list.
  */
abstract class ScallopOption[A](nm: String) { opt =>

  private[scallop] var _name = nm

  lazy val fn: String => Option[A] = x => None
  lazy val supplied: Boolean = false

  /** Name for the option */
  def name = _name
  /** Name for the option, which can be presented to human user */
  def humanName = _name.replace("\0", ":")

  /** Retreive the underlying value as an option */
  def get = fn(name)

  /** Retreive the underlying value. Use only if you are completely sure that there is a value. */
  def apply() = get.get

  /** Tests whether the underlying value was explicitly supplied by user. */
  def isSupplied = supplied

  private def mapResult[B](transformer: Option[A] => Option[B]) =
    new ScallopOption[B](name) {
      override lazy val fn = opt.fn andThen transformer
      override lazy val supplied = opt.supplied
    }

  /** Returns ScallopOption, that contains the result of applying ```pf```
    * to the value, if this option is non-empty and pf is defined for that value.
    * Returns empty ScallopOption otherwise.
    *
    * @param pf the partial function
    */
  def collect[B](pf: PartialFunction[A,B]) = mapResult(_.collect(pf))

  /** Returns ScallopOption, that contains the value if applying
    * predicate p to this value returned true. No value otherwise.
    *
    * @param p the predicate used for testing
    */
  def filter(p: A => Boolean) = mapResult(_.filter(p))

  /** Returns ScallopOption, that contains the value if applying
    * predicate p to this value returned false. No value otherwise.
    *
    * @param p the predicate used for testing
    */
  def filterNot(p: A => Boolean) = mapResult(_.filterNot(p))

  def withFilter(p: A => Boolean) = new WithFilter(p)

  class WithFilter(p: A => Boolean) {
    def map[B](f: A => B) = opt filter p map f
    def flatMap[B](f: A => ScallopOption[B]) = opt filter p flatMap f
    def foreach(f: A => Unit) = opt filter p foreach f
    def withFilter(q: A => Boolean) = new WithFilter(x => p(x) && q(x))
  }

  /** Returns ScallopOption, that contains the result of applying
    * ```f``` to this option's value, if this option is non-empty.
    * Returns ScallopOption with no value otherwise.
    *
    * @param f the function to apply
    */
  def map[B](f: A => B) = mapResult(_.map(f))

  /** Apply the given procedure f to the option's value, if it is nonempty.
    */
  def foreach(f: A => Unit) = opt.get.foreach(f)

  /** Returns the result of applying f th this options value if
    * this option is non-empty.
    */
  def flatMap[B](f: A => ScallopOption[B]): ScallopOption[B] =
    mapResult(_.flatMap(x => f(x).get))

  /** Returns ScallopOption with this value if it is non-empty,
    * or with the value of the alternative option. If it is
    * empty too, the resulting ScallopOption will contain None.
    *
    * @param alternative the alternative expression
    */
  def orElse[B >: A](alternative: => Option[B]) = mapResult(_.orElse(alternative))

  /** A convenience method to check whether the underlying option
    * is defined. Just an alias for opt.get.isDefined.
    */
  def isDefined = get.isDefined

  /** A convenience method to check whether the underlying option is
    * empty. Just an alias for !opt.isDefined.
    */
  def isEmpty = !isDefined

  override def toString = opt.get match {
    case Some(x) => "ScallopSome(%s)" format x
    case None => "ScallopNone"
  }
}
