fun sherlokHolmesAnagram(evaluation: String): Int {

    var counter = 0

    for (elementSize in 1 until evaluation.length) {
        for (index in 0..evaluation.length - elementSize) {
            val pivot = evaluation.substring(index, index + elementSize)
            val subToEvaluate = evaluation.substring(index + 1)
            if (subToEvaluate.isEmpty()) {
                continue
            }
            if (subToEvaluate.length < pivot.length) {
                continue
            }
            println("[${pivot}, ${subToEvaluate}]")
            counter += hasAnagramContained(pivot, subToEvaluate)
        }
    }

    println("Num of anagrams in string: $counter")

    return counter
}

private fun hasAnagramContained(key: String, evaluation: String): Int {
    var counter = 0
    for (index in 0..evaluation.length - key.length) {
        val toEvaluate = evaluation.substring(index, index + key.length)
        if (isAnagram(key, toEvaluate)) {
            counter++
        }
    }
    return counter
}

private fun isAnagram(key: String, evaluation: String): Boolean {
    val keySorted = key.toCharArray().sorted().joinToString("")
    val evaluationSorted = evaluation.toCharArray().sorted().joinToString("")
    return keySorted == evaluationSorted
}
