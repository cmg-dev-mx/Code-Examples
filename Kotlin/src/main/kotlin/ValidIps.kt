fun validIps(s: String): List<String>? {
    val validIps = arrayListOf<String>()

    if (s.length < 4 || s.length > 12) {
        return null
    }

    validIps.addAll(findIps(s, 4).orEmpty())

    return validIps
}

private fun findIps(s: String, fieldNumber: Int): List<String>? {
    if (s.length < fieldNumber || (fieldNumber * 3) < s.length) {
        return null
    }

    val ipsFounded = arrayListOf<String>()

    if (fieldNumber == 1) {
        if (s.length > 1 && s[0] == '0') {
            return null
        }
        if (s.toInt() > -1 && s.toInt() < 256) {
            ipsFounded.add(".$s")
        }
    } else {
        for (i in 0 until 3) {
            if ((i+1) < s.length) {
                val field = s.substring(0, i + 1)
                if (field.length > 1 && field[0] == '0') {
                    continue
                }
                if (field.toInt() > -1 && field.toInt() < 256) {
                    val childField = findIps(s.substring(i + 1), fieldNumber - 1)
                    childField?.forEach { child ->
                        var prefix = ""
                        if (fieldNumber < 4) {
                            prefix = "."
                        }
                        ipsFounded.add("$prefix$field$child")
                    }
                }
            }
        }
    }

    return ipsFounded
}