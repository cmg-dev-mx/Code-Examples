fun areAnagrams(s1: String, s2: String): Boolean {
    if (s1.length != s2.length) {
        return false
    }

    val freq1 = hashMapOf<Char, Int>()
    val freq2 = hashMapOf<Char, Int>()

    s1.forEach { ch ->
        freq1[ch] = freq1[ch]?.plus(1) ?: 1
    }
    s2.forEach { ch ->
        freq2[ch] = freq2[ch]?.plus(1) ?: 1
    }

    freq1.keys.forEach { key ->
        if (!freq2.containsKey(key) || freq1[key] != freq2[key]) {
            return false
        }
    }
    return true
}

fun areAnagramsSort(s1: String, s2: String): Boolean {
    if (s1.length != s2.length) {
        return false
    }

    val sorted1 = s1.toCharArray().sorted().joinToString()
    val sorted2 = s2.toCharArray().sorted().joinToString()

    return sorted1 == sorted2
}