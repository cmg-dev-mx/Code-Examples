fun longestUniqueString(s: String): Int {
    if (s.isEmpty()) return 0

    var start = 0
    var longer = 0

    for (i in s.indices) {
        val sub = s.substring(start, i)
        if (sub.toCharArray().distinct().size != sub.length) {
            start++
        } else {
            longer = i - start
        }
    }

    if (start == 0) {
        longer++
    }

    return longer
}