object TransactionParser {
  def parse(transaction: String): (String, String) = {
    val index = transaction.indexOfSlice(":=")
    val left = transaction.substring(0, index)
    val right = transaction.substring(index + 2)
    (left, right)
  }
}
