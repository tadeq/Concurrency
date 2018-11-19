import scala.collection.mutable.ListBuffer

object Main {
  def placeInFoata(transaction: Char, foata: ListBuffer[ListBuffer[Char]], dependencies: ListBuffer[(String, String)]): Unit = {
    val transactionDependencies = dependencies.filter(dep => {
      dep._1.equals(transaction.toString) || dep._2.equals(transaction.toString)
    })
    var dependentTo = -1
    var classNumber = -1
    for (foataClass <- foata) {
      classNumber += 1
      for (element <- foataClass) {
        for (dep <- transactionDependencies) {
          if (dep._1.equals(element.toString) || dep._2.equals(element.toString)) {
            dependentTo = classNumber
          }
        }
      }
    }
    if (dependentTo == foata.size - 1) {
      foata += ListBuffer.empty[Char]
      foata.last += transaction
    } else {
      foata(dependentTo + 1) += transaction
    }
  }


  def main(args: Array[String]): Unit = {
    val w = "baadcb"
    val alphabet = List("a", "b", "c", "d")
    val transactions = List("x:=x+y", "y:=y+2z", "x:=3x+z", "z:=y-z")
    val parsedTransactions = transactions.map(TransactionParser.parse)
    var dependencies = ListBuffer.empty[(String, String)]
    var independencies = ListBuffer.empty[(String, String)]
    for (t1 <- parsedTransactions) {
      for (t2 <- parsedTransactions) {
        val i1 = alphabet(parsedTransactions.indexOf(t1))
        val i2 = alphabet(parsedTransactions.indexOf(t2))
        if ((t1._2 contains t2._1) || (t2._2 contains t1._1)) {
          dependencies += Tuple2(i1, i2)
        } else {
          independencies += Tuple2(i1, i2)
        }
      }
    }
    for (d <- dependencies)
      System.out.println(d)
    System.out.println()
    for (i <- independencies)
      System.out.println(i)
    System.out.println()
    var foata = ListBuffer.empty[ListBuffer[Char]]
    for (i <- 0 until w.length) {
      placeInFoata(w(i), foata, dependencies)
    }
    for (f <- foata)
      System.out.println(f)
  }
}