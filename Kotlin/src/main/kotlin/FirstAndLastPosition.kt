fun searchRange(arr: Array<Int>, target: Int): Array<Int> {
    if (arr.isEmpty()) return arrayOf(-1, -1)

    for (i in arr.indices) {
        if (arr[i] == target) {
            var end = i
            while (end < arr.size && arr[end] == target) end++
            return arrayOf(i, end - 1)
        }
    }

    return arrayOf(-1, -1)
}

fun searchRangeBinary(arr: Array<Int>, target: Int): Array<Int> {
    if (arr.isEmpty()) return arrayOf(-1, -1)

    val start = findStart(arr, target)
    val end = findEnd(arr, target)
    return arrayOf(start, end)
}

private fun findStart(arr: Array<Int>, target: Int): Int {
    if (arr[0] == target) return 0

    var left = 0
    var right = arr.size - 1
    while (left <= right) {
        val mid = (right + left) / 2
        if (arr[mid] == target && arr[mid - 1] != target) return mid
        if (arr[mid] < target) left = mid + 1
        else right = mid - 1
    }
    return -1
}

private fun findEnd(arr: Array<Int>, target: Int): Int {
    if (arr[arr.size - 1] == target) return arr.size - 1

    var left = 0
    var right = arr.size - 1
    while (left <= right) {
        val mid = (right + left) / 2
        if (arr[mid] == target && arr[mid + 1] != target) return mid
        if (arr[mid] <= target) left = mid + 1
        else right = mid - 1
    }
    return -1
}

